package hhplus.e_commerce.domain.order.service.repository;

import hhplus.e_commerce.domain.order.entity.OrderSheet;

public interface OrderSheetRepository {
    OrderSheet save (OrderSheet orderSheet);

    OrderSheet getOrderSheet (long customerId);
}
