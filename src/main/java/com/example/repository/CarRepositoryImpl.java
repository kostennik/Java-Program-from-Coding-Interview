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
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";


    @Override
    public List<Car> findAllByCoordinates(Coordinate coordinate, int meter) {
        carList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine(); //skip first line
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cars = line.split(cvsSplitBy);

                if (cars.length > 0 && !cars[2].equals(" ")) {
                    System.out.println(cars[0] + " , lat="
                         +   cars[1]
                                    + ", long="
                                    + cars[2] + "]"
                    );

                    carList.add(
                            new Car(cars[0], new Coordinate(Double.parseDouble(cars[1]), Double.parseDouble(cars[2])))

                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return carList;
    }


}
