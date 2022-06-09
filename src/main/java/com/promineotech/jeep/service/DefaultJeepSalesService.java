package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        log.info("The fetchJeeps method was called with model={} and trim={}",
                model, trim);
        return null;
    }

    @Override
    public List<Jeep> fetch(String model, String trim) {
        return null;
    }
}