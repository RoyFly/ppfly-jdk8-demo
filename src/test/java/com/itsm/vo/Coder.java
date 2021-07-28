package com.itsm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ÂëÅ©
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coder {
    private String name;
    private Integer age;
    private double salary;
    private Status status;


    public enum Status {
        BUSY, FREE, VOCATION
    }
}
