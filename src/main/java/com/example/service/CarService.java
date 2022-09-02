package com.example.service;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    void loadCsv(MultipartFile file);

    List<Car> findByCoordinatesAndDistance(Coordinate coordinate, int meter);
}
