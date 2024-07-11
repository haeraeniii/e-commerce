package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderDto;
import hhplus.e_commerce.domain.order.controller.dto.OrderItemDto;
import hhplus.e_commerce.domain.order.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    @Test
    @DisplayName("Dto to Entity")
    public void toEntityTest() {
        //given

        final OrderDto orderDto = OrderDto.builder()
                .customerId(0)
                .orderItemDtoList(new ArrayList<>())
                .build();

        //when
        final Order order = OrderMapper.INSTANCE.toOrder(orderDto);

        //then
        assertNotNull(order);
        Assertions.assertThat(order.getId()).isEqualTo(0L);
        Assertions.assertThat(order.getCustomerId()).isEqualTo(0);
    }

    @Test
    @DisplayName("Entity to DTO")
    public void toOrderDtoTest() {
        //given
        final Order order = new Order();
        order.setCustomerId(0);
        order.setOrderItemList(new ArrayList<>());

        //when
        final OrderDto orderDto = OrderMapper.INSTANCE.toOrderDto(order);

        //then
        assertNotNull(orderDto);
        Assertions.assertThat(orderDto.getCustomerId()).isEqualTo(0);
    }
}