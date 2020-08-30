package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import com.example.domain.Distance;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarRepositoryImpl implements CarRepository {
    Distance distance;
    private List<Car> carList;
    private String csvFile = "src/main/resources/gps_pos.csv";


    @Override
    public List<Car> findAllByCoordinates(Coordinate beginCoordinate, int meter) {
        Coordinate targetCoordinate = new Coordinate();
        carList = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] cars;
            reader.readNext();  //skip first line
            while ((cars = reader.readNext()) != null) {
                if (!cars[1].isEmpty() && !cars[1].isEmpty() && !cars[2].isEmpty()){
                    targetCoordinate.setLatitude(Double.parseDouble(cars[1]));
                    targetCoordinate.setLongitude(Double.parseDouble(cars[2]));

                    if (Distance.distanceMeasurement(beginCoordinate, targetCoordinate) <= meter){
                        carList.add(new Car(cars[0], targetCoordinate));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

}
