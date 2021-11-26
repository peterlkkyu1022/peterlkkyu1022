package com.app.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoindeskDetail implements Serializable {

    @JsonProperty
    private String code;

    @JsonProperty
    private String symbol;

    @JsonProperty
    private String rate;

    @JsonProperty
    private String description;

    @JsonProperty
    private BigDecimal rate_float;

}
