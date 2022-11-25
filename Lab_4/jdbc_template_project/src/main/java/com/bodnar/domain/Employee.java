package com.bodnar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private Integer id;
    private String name;
    private String surname;
    private String gender;
    private Date birthday;
    private Integer teritoryId;
}
