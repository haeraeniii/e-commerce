package hhplus.e_commerce.domain.customer.service;

import hhplus.e_commerce.domain.customer.entity.Customer;
import hhplus.e_commerce.domain.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Test
    @DisplayName("금액 충전 테스트")
    public void chargeTest() {
        //given
        Customer customer = new Customer();
        customer.setId(0);
        customer.setBalance(3000);

        //when
        customer.chargeAmount(3000);

        //then
        assertThat(customer.getBalance()).isEqualTo(6000);
    }

    @Test
    @DisplayName("잔액 확인 테스트")
    public void checkBalanceTest() {
        //given
        Customer customer = new Customer();
        customer.setId(0);
        customer.setBalance(3000);

        //when
        when(customerService.checkBalance(0)).thenReturn(customer);

        //then
        assertThat(customerService.checkBalance(0).getBalance()).isEqualTo(3000);
    }
}