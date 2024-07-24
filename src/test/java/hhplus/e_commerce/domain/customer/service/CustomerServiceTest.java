package hhplus.e_commerce.domain.customer.service;

import hhplus.e_commerce.domain.customer.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
        Customer customer = new Customer("정혜련", 0);

        //when
        customer.charge(30000);

        //then
        assertThat(customer.getBalance()).isEqualTo(30000);
    }

    @Test
    @DisplayName("잔액 확인 테스트")
    public void checkBalanceTest() {
        //given
        Customer customer = new Customer("정혜련", 3000);

        //when
        when(customerService.checkBalance(0)).thenReturn(customer);

        //then
        assertThat(customerService.checkBalance(0).getBalance()).isEqualTo(3000);
    }
}