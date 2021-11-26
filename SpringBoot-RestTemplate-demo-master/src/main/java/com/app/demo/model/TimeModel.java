package com.app.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TimeModel implements Serializable {

    private String updated;

    private String updatedISO;

    private String updateduk;

}
