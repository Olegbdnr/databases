package com.bodnar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teritory {
    private Integer id;
    private String phone;
    private Integer locationId;
}
