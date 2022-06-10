package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

    @Autowired
    private JeepSalesService jeepSalesService;

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, @Length(max = 30) @Pattern(regexp = "[\\w\\s]*") String trim) {
        log.info("model={}, trim={}", model, trim);
        return jeepSalesService.fetchJeeps(model, trim);
    }

}