package com.example.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String carId;
    private Coordinate coordinate;
}
