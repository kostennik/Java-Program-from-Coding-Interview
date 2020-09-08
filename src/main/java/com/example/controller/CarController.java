package com.example.controller;

import com.example.Memory;
import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getCars/{lat}/{lon}/{meters}")
    public ResponseEntity<Map<Integer, Car>> getByCoordinate(@PathVariable double lat, @PathVariable double lon, @PathVariable int meters) {
        List<Car> cars = repository.findCarsByCoordinatesAndDistance(new Coordinate(lat, lon), meters);
        Map<Integer, Car> carMap = new HashMap<>();
        for (int i = 0; i < cars.size(); i++) {
            carMap.put(i, cars.get(i));
        }
        return ResponseEntity.ok().body(carMap);
    }
}
