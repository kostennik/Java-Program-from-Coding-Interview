package com.example.controller;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getCars/{lat}/{lon}/{meters}")
    public List<Car> getAll(@PathVariable double lat, @PathVariable double lon, @PathVariable int meters){
        return repository.findAllByCoordinates(new Coordinate(lat, lon), meters);
    }
}
