package com.example.controller;

import com.example.Memory;
import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getCars/{lat}/{lon}/{meters}")
    public ResponseEntity<Map<Integer, Car>> getCarsByCoordinateAndDistance(@PathVariable double lat, @PathVariable double lon, @PathVariable int meters) {
        Map<Integer, Car> carsMap = new HashMap<>();

        Thread userThread = new Thread(() -> {
            List<Car> cars = repository.findCarsByCoordinatesAndDistance(new Coordinate(lat, lon), meters);
            for (int i = 0; i < cars.size(); i++) {
                carsMap.put(i, cars.get(i));
            }
        });

        userThread.start();

        try {
            userThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("new thread {} was created", userThread.getName());

        return ResponseEntity.ok().body(carsMap);
    }
}
