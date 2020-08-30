package com.example.domain;

public class Distance {
    private double distanceBetween;

    public static double distanceMeasurement(Coordinate beginCoordinate, Coordinate targetCoordinate) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(targetCoordinate.getLatitude() - beginCoordinate.getLatitude());
        double dLng = Math.toRadians(targetCoordinate.getLongitude() - beginCoordinate.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(beginCoordinate.getLatitude())) * Math.cos(Math.toRadians(targetCoordinate.getLatitude())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (earthRadius * c);
    }
}
