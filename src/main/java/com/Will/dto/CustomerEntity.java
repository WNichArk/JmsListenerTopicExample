package com.Will.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    private int id;
    private String name;
    private double lat;
    private double lon;
}
