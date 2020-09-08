package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;

import java.util.List;

public interface CarRepository {
    public List<Car> findCarsByCoordinatesAndDistance(Coordinate coordinate, int meter);

    public void loadAllCarsInMemory();
}
