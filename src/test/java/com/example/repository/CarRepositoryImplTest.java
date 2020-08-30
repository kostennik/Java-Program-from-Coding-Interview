package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryImplTest {

    private CarRepository repository;
    private Coordinate targetCoordinate;
    private final int distance = 100;

    @BeforeEach
    void setUp() {
        repository = new CarRepositoryImpl();
        //set target coordinate
        targetCoordinate = new Coordinate(53.9037654770889, 20.887423009119);
    }

    @Test
    void findAllByCoordinates() {
        List<Car> byCoordinates = repository.findAllByCoordinates(targetCoordinate, distance);
        byCoordinates.forEach(System.out::println);
        assertEquals(byCoordinates.size(),3);
    }
}