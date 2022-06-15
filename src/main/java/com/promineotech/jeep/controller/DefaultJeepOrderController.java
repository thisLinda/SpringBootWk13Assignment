package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.service.JeepOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DefaultJeepOrderController implements JeepOrderController {

    @Autowired
    private JeepOrderService JeepOrderService;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        log.debug("Order={}", orderRequest);
        DefaultJeepOrderController jeepOrderService = null;
        return jeepOrderService.createOrder(orderRequest);
    }

    @Override
    public List<Order> fetchAllOrders() {
        log.debug("In BasicJeepOrderController.fetchAllOrders");
        DefaultJeepOrderController jeepOrderService = null;
        return jeepOrderService.fetchAllOrders();
    }

}