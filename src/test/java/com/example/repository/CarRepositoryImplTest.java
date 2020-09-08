package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CarRepositoryImplTest {

    @Autowired
    private CarRepository repository;
    private Coordinate targetCoordinate = new Coordinate(53.9037654770889, 20.887423009119); //set target coordinate
    private final int distance = 100;
    private final int expectedCarQuantity = 3;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllByCoordinates() {
        List<Car> byCoordinates = repository.findCarsByCoordinatesAndDistance(targetCoordinate, distance);
        byCoordinates.forEach(System.out::println);
        assertEquals(byCoordinates.size(), expectedCarQuantity);
    }
}