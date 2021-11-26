package com.app.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CoindeskInfo implements Serializable {

    @JsonProperty
    private String updateTime;

    @JsonProperty
    List<CoindeskInfoItem> items;
}
