package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import javax.validation.Valid;
import java.util.List;

public interface JeepSalesService {
    List<Jeep> fetchJeeps(JeepModel model, String trim);

    List<Jeep> fetchAllJeeps();

    Jeep createJeep(@Valid Jeep newJeep);
}