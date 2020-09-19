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
public class Coordinate {
    private double latitude;
    private double longitude;
}
