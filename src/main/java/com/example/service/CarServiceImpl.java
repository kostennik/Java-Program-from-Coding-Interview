package com.example.service;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.domain.Distance;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@SessionScope
public class CarServiceImpl implements CarService {
    private List<Car> allCarsList = new ArrayList<>();

    @Override
    public void loadCsv(MultipartFile file) {
        Reader reader = null;
        CSVReader csvReader;

        try {
            reader = new InputStreamReader(file.getInputStream());
            csvReader = new CSVReader(reader);
            csvReader.readNext();  //skipping first line
            String[] rows;

            while ((rows = csvReader.readNext()) != null) {
                if (!isEmpty(rows)) {
                    addCarToList(rows);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("{} objects of Car was loaded", allCarsList.size());
    }

    @Override
    public List<Car> findByCoordinatesAndDistance(Coordinate beginCoordinate, int meter) {
        List<Car> foundCars = new ArrayList<>();
        float distanceBetween;

        for (Car car : allCarsList) {
            distanceBetween = Distance.distanceMeasurement(beginCoordinate, car.getCoordinate());

            if (distanceBetween < meter) {
                foundCars.add(car);
            }
        }
        log.info("{} objects of Car was found", foundCars.size());
        return foundCars;
    }

    private boolean isEmpty(String[] elems) {
        return elems[1].isEmpty() && elems[2].isEmpty();
    }

    private void addCarToList(String[] cars) {
        Coordinate currentCoordinate = new Coordinate(Double.parseDouble(cars[1]), Double.parseDouble(cars[2]));
        allCarsList.add(new Car(cars[0], currentCoordinate));
    }
}
