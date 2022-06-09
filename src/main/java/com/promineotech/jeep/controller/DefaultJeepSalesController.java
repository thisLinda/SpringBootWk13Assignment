package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

    @Autowired
    private JeepSalesService jeepSalesService;

    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        log.info("model={}, trim={}", model, trim);
        return jeepSalesService.fetch(model, trim);
    }

}