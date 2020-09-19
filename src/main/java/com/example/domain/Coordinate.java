package com.example.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    private double latitude;
    private double longitude;
}
