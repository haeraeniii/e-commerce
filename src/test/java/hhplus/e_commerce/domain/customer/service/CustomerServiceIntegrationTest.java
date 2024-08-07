package hhplus.e_commerce.domain.customer.service;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.command.CustomerCommand;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import hhplus.e_commerce.support.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
//    @Transactional
//    @Rollback(false)
    public void chargeSynchronicityTest() throws CustomException, InterruptedException {
        //given
        Customer customer = customerService.registerCustomer("김종협");

        System.out.println("repository10 " + customerRepository.findByIdWithPessimisticWriteLock(1).getCustomerName());

        // 동시성 이슈 테스트
        int threadCount = 3;

        // thread 사용할 수 있는 서비스 선언, 몇 개의 스레드 사용할건지 지정
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CountDownLatch latch = new CountDownLatch (threadCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        log.info("Before for loop");
        //when
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {

                        log.info("starting iteration:" + finalI);
                        customerService.charge(new CustomerCommand.Create(customer.getId(), 3000));
                        successCount.getAndIncrement();
                        log.info("Successful iteration:" + finalI);

                } catch (CustomException e) {
                    failCount.getAndIncrement();
                    log.info("Exception in thread " + finalI + ": " + e.getMessage());
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();
        log.info("결과 : " + customerRepository.findByIdWithPessimisticWriteLock(customer.getId()));
        log.info("successCount" + successCount);
        log.info("failCount" + failCount);

        //when
//        CompletableFuture.allOf(
//            CompletableFuture.runAsync(() -> {
//                try {
//                    Customer customer1 = customerService.charge(new CustomerCommand.Create(1, 3000));
//                    System.out.println("thread1 " +customer1.getName());
//                } catch (CustomException e) {
//                    throw new RuntimeException(e);
//                }
//            }),
//            CompletableFuture.runAsync(() -> {
//                try {
//                    customerService.charge(new CustomerCommand.Create(1, 3000));
//                } catch (CustomException e) {
//                    throw new RuntimeException(e);
//                }
//            }),
//            CompletableFuture.runAsync(() -> {
//                try {
//                    customerService.charge(new CustomerCommand.Create(1, 3000));
//                } catch (CustomException e) {
//                    throw new RuntimeException(e);
//                }
//            })
//        ).join();
//
//         Thread.sleep(3000);

        //then
    }
}
