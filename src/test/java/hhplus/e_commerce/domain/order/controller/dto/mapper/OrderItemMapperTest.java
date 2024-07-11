package hhplus.e_commerce.domain.order.controller.dto.mapper;

import hhplus.e_commerce.domain.order.controller.dto.OrderItemDto;
import hhplus.e_commerce.domain.order.entity.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {

    @Test
    @DisplayName("Dto to Entity")
    public void toEntityTest() {
        //given
        final OrderItemDto orderItemDto = OrderItemDto.builder()
                .productOptionId(1)
                .productName("블라우스")
                .color("pink")
                .size("M")
                .price(30000)
                .build();

        //when
        final OrderItem orderItem = OrderItemMapper.INSTANCE.toOrderItem(orderItemDto);

        //then
        assertNotNull(orderItem);
        Assertions.assertThat(orderItem.getId()).isEqualTo(0L);
        Assertions.assertThat(orderItem.getProductOptionId()).isEqualTo(1);
        Assertions.assertThat(orderItem.getProductName()).isEqualTo("블라우스");
        Assertions.assertThat(orderItem.getColor()).isEqualTo("pink");
        Assertions.assertThat(orderItem.getSize()).isEqualTo("M");
        Assertions.assertThat(orderItem.getPrice()).isEqualTo(30000);
    }


}