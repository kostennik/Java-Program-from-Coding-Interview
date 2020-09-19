package com.example.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String carId;
    private Coordinate coordinate;
}
