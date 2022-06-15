package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

import java.util.List;

public interface JeepOrderService {
    Order createOrder(OrderRequest orderRequest);

    List<Order> fetchAllOrders();

}