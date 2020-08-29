package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;

import java.util.List;

public interface CarRepository {
    public List<Car>findAllByCoordinates(Coordinate coordinate, int meter);
}
