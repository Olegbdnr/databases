package com.bodnar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Route {
    private Integer id;
    private Integer teritoryId;
    private Integer patrolBotId;
    private String name;
    private BigDecimal length;
}
