package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;

import java.util.List;

public class FareCalculator {

    public double calculate(Ride ride) {
        List<Position> positions = ride.getPositions();
        if (positions.isEmpty()) {
            return -1;
        }
        double totalFare = 1.30;
        double idleTime = 0;
        Position source = positions.get(0);
        for (int index = 1; index < positions.size(); index++) {
            Position destination = positions.get(index);

            double distance = calcDistance(source, destination);
            double time = calcTimeInHours(source, destination);
            double speed = calcSpeed(distance, time);

            if (speed < 10) {
                idleTime += time;
                source = destination;
            } else if (speed <= 100) {
                double fare = calcFare(distance, source.getTimestamp());
                totalFare += fare;
                source = destination;
            }
        }

        totalFare += ((int) idleTime * 11.90);
        return Math.max(totalFare, 3.47);
    }

    private double calcFare(double distance, long timestamp) {
        int hour = (int) (timestamp) % 24;
        return (0 <= hour && hour <= 5) ? 1.3 * distance : 0.74 * distance;
    }

    private double calcSpeed(double distance, double time) {
        return distance / time;
    }

    private double calcTimeInHours(Position source, Position destination) {
        long startTime = source.getTimestamp();
        long endTime = destination.getTimestamp();

        long difference = endTime - startTime;
        // TODO: verify divisible by 3600
        return (double) difference / 3600;
    }

    private double calcDistance(Position source, Position destination) {
        return HaversineDistance.getDistance(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }

}
