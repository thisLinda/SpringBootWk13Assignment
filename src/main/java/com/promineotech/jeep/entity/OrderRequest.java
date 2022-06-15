package com.promineotech.jeep.entity;

import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class OrderRequest {
    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String customer;

    @NotNull
    private JeepModel model;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String trim;

    @Positive
    @Min(2)
    @Max(4)
    private int doors;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String color;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String engine;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String tire;

    private List<@NotNull @Length(max =30) @Pattern(regexp = "[\\w\\s]*") String> options;
}
