package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.domain.Distance;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private List<Car> carList;
    private final String csvFile = "src/main/resources/gps_pos.csv";

    @Override
    public List<Car> findAllByCoordinates(Coordinate beginCoordinate, int meter) {
        Coordinate targetCoordinate = new Coordinate();
        Distance distance = new Distance();
        carList = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] cars;
            reader.readNext();  //skip first line
            while ((cars = reader.readNext()) != null) {
                if (!cars[1].isEmpty() && !cars[1].isEmpty() && !cars[2].isEmpty()) {
                    targetCoordinate.setLatitude(Double.parseDouble(cars[1]));
                    targetCoordinate.setLongitude(Double.parseDouble(cars[2]));

                    float distanceBetween = distance.distanceMeasurement(beginCoordinate, targetCoordinate);

                    if (distanceBetween < meter) {
                        carList.add(new Car(cars[0], targetCoordinate, distanceBetween));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

}
