package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.domain.Distance;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CarRepositoryImpl implements CarRepository {
    private List<Car> allCarsList = new ArrayList<>();
    private final String CSV_FILE = "src/main/resources/gps_pos.csv";

    @Override
    public List<Car> findCarsByCoordinatesAndDistance(Coordinate beginCoordinate, int meter) {
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

    @Override
    public void readFromDiskAndLoadAllCarsInMemory() {
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(CSV_FILE));
            String[] cars;
            reader.readNext();  //skip first line

            while ((cars = reader.readNext()) != null) {
                if (!isEmpty(cars)) {
                    addCarToList(cars);
                }
            }
            log.info("{} objects of Car was loaded", allCarsList.size());

        } catch (IOException e) {
            log.error("File {} doesnt exist", CSV_FILE);
        }
    }

    private boolean isEmpty(String[] cars){
        return cars[1].isEmpty() && cars[2].isEmpty();

    }

    private void addCarToList(String[] cars) {
        Coordinate currentCoordinate = new Coordinate(Double.parseDouble(cars[1]), Double.parseDouble(cars[2]));
        allCarsList.add(new Car(cars[0], currentCoordinate));
    }
}
