package com.example.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Car {
    private String carId;
    private Coordinate coordinate;
    private float distanceToTargetPosition;

    public Car(String carId, Coordinate coordinate, float distanceToTargetPosition) {
        this.carId = carId;
        this.coordinate = coordinate;
        this.distanceToTargetPosition = distanceToTargetPosition;
    }

    public Car(String carId) {
        this.carId = carId;
    }

    public Car() {

    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public float getDistanceToTargetPosition() {
        return distanceToTargetPosition;
    }

    public void setDistanceToTargetPosition(float distanceToTargetPosition) {
        this.distanceToTargetPosition = distanceToTargetPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId.equals(car.carId) &&
                coordinate.equals(car.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, coordinate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id='" + carId + '\'' +
                ", coordinate=" + coordinate +
                ", distance=" + distanceToTargetPosition +
                '}' + "\n";
    }
}
