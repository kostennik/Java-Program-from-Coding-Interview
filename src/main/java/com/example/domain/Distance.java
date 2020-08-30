package com.example.domain;

import org.springframework.stereotype.Component;

@Component
public class Distance {

    /**
     * measures a distance between current position and target position
     * @param beginCoordinate is begin position (current)
     * @param targetCoordinate is target position
     * @return distance in meters
     */
    public float distanceMeasurement(Coordinate beginCoordinate, Coordinate targetCoordinate) {
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
