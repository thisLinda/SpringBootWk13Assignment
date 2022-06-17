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

//    @Override
//    public ResponseEntity<byte[]> retrieveImage(String imageId) {
//        log.debug("Retrieving image with ID={}", imageId);
//        Image image = jeepSalesService.retrieveImage(imageId);
//        HttpHeaders headers = new HttpHeaders();
//
//        // The Content is so that the browser can understand how to render the image
//        headers.add("Content-Type", image.getMimeType().getMimeType());
//        // The Length is to satisfy some "fussy" browsers.
//        headers.add("Content-Length", Integer.toString(image.getData().length));
//
//        return ResponseEntity.ok().headers(headers).body(image.getData());
//    }

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, @Length(max = 30) @Pattern(regexp = "[\\w\\s]*") String trim) {
        log.debug("model={}, trim={}", model, trim);
        return jeepSalesService.fetchJeeps(model, trim);
    }

//    @Override
//    public String uploadImage(MultipartFile image, Long jeepPK) {
//        log.debug("image={}, jeepPK={}", image, jeepPK);
//        String imageId = jeepSalesService.uploadImage(image,jeepPK);
//        String json = "{\"imageID\":\"" + imageId + "\"}";
//        return json;
//    }

}