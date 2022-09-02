package com.example.service;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    List<Car> findByCoordinatesAndDistance(Coordinate coordinate, int meter);

    void loadCsv(MultipartFile file);
}
