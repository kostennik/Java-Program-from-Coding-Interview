package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
