package com.bodnar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompleteSet {
    private Integer id;
    private Integer manufacturerId;
    private String name;
    private BigDecimal batteryReserve;
    private Integer averageSpeed;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal height;
}
