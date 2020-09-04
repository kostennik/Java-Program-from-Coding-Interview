package com.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DistanceTest {
    @Autowired
    private Distance d;

    private final Coordinate currentCoordinate = new Coordinate(53.657037, 20.400798);
    private final Coordinate targetCoordinate = new Coordinate(52.232107, 21.006985);
    private final int expectedDistance = 164;  //kilometer


    @BeforeEach
    void setUp() {
    }

    @Test
    void testDistanceMeasurement() {
        float distance = (d.distanceMeasurement(currentCoordinate, targetCoordinate)/1000);  //convert meters to kilometers
        assertEquals(Math.round(distance), expectedDistance);
    }
}