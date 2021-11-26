package com.app.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@Data
public class Coindesk implements Serializable {

    @JsonProperty
    private TimeModel time;
    @JsonProperty
    private String disclaimer;
    @JsonProperty
    private String chartName;
    @JsonProperty
    private Map<String, CoindeskDetail> bpi;


}
