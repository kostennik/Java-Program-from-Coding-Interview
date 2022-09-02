package com.example.controller;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/loadCsv")
    public void loadCsv(@RequestParam("file") MultipartFile file) {
        carService.loadCsv(file);
    }

    @GetMapping("/getCars/{lat}/{lon}/{meters}")
    public Iterable<Car> getCarsByCoordinateAndDistance(@PathVariable double lat, @PathVariable double lon, @PathVariable int meters) {
        return carService.findByCoordinatesAndDistance(new Coordinate(lat, lon), meters);
    }
}
