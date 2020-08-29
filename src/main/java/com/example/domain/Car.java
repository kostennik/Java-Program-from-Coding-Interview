package com.example.domain;

import java.util.Objects;

public class Car {
    private String car_id;
    private Coordinate coordinate;

    public Car(String car_id, Coordinate coordinate) {
        this.car_id = car_id;
        this.coordinate = coordinate;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return car_id.equals(car.car_id) &&
                coordinate.equals(car.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car_id, coordinate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id='" + car_id + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
