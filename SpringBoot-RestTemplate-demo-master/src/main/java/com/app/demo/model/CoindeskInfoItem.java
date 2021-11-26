package com.app.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CoindeskInfoItem implements Serializable {

    @JsonProperty
    private String cur;

    @JsonProperty
    private String curName;

    @JsonProperty
    private String exchangerate;
    
}
