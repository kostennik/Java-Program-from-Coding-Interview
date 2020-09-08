package com.example.repository;

import com.example.Memory;
import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.domain.Distance;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CarRepositoryImpl implements CarRepository {
    Distance distance = new Distance();
    private List<Car> carList;
    private List<Car> allCarsList;
    private final String csvFile = "src/main/resources/gps_pos.csv";

    @Override
    public List<Car> findCarsByCoordinatesAndDistance(Coordinate beginCoordinate, int meter) {
        carList = new ArrayList<>();
        float distanceBetween;

        for (int i = 0; i < allCarsList.size(); i++) {
            distanceBetween = distance.distanceMeasurement(beginCoordinate, allCarsList.get(i).getCoordinate());
            if (distanceBetween < meter) {
                carList.add(allCarsList.get(i));
            }
        }
        log.info("{} objects of Car was found", carList.size());
        return carList;
    }

    @Override
    public void loadAllCarsInMemory() {
        Coordinate currentCoordinate;
        Distance distance = new Distance();
        allCarsList = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] cars;
            reader.readNext();  //skip first line
            while ((cars = reader.readNext()) != null) {
                if (!cars[1].isEmpty() && !cars[1].isEmpty() && !cars[2].isEmpty()) {
                    currentCoordinate = new Coordinate(Double.parseDouble(cars[1]), Double.parseDouble(cars[2]));

                    allCarsList.add(new Car(cars[0], currentCoordinate));
                }
            }
            log.info("{} objects of Car was loaded", allCarsList.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
