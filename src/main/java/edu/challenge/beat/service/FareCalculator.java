package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import edu.challenge.beat.util.AppConstants;
import edu.challenge.beat.util.HaversineDistance;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

/**
 * Class calculating fare for each ride considering its speed,
 * time taken for the ride
 */
public class FareCalculator {

    private static final int SecondToHourly = 3600;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     * Method for calculating fare for each ride
     * @param ride
     * @return
     */
    public double calculate(Ride ride) {
        List<Position> positions = ride.getPositions();
        if (positions.isEmpty()) {
            return -1;
        }
        double totalFare = AppConstants.INITIAL_FARE;
        double idleTime = 0;
        Position source = positions.get(0);
        for (int index = 1; index < positions.size(); index++) {
            Position destination = positions.get(index);

            double distance = calcDistance(source, destination);
            double time = calcTimeInHours(source, destination);
            double speed = calcSpeed(distance, time);

            if (speed < AppConstants.MIN_SPEED ) {
                idleTime += time;
                source = destination;
            } else if (speed <= AppConstants.MAX_SPEED ) {
                double fare = calcFare(distance, source.getTimestamp());
                totalFare += fare;
                source = destination;
            }
        }
        totalFare += ((int) idleTime * AppConstants.IDLE_TIME_PER_HOURLY_FARE);
        return Double.parseDouble ( decimalFormat.format ( Math.max(totalFare, AppConstants.MIN_RIDE_FARE ) ));
    }

    /**
     * Helper Method for fare calculation
     * @param distance
     * @param timestamp
     * @return
     */
    private double calcFare(double distance, long timestamp) {
        int hour = Instant.ofEpochSecond(timestamp)
                .atOffset(ZoneOffset.UTC)
                .toLocalTime().getHour();
        return (AppConstants.RIDE_END_HOUR <= hour && hour <= AppConstants.RIDE_START_HOUR)
                ? AppConstants.NIGHT_TIME_PER_KM_FARE * distance : AppConstants.DAY_TIME_PER_KM_FARE
                * distance;
    }

    /**
     * Helper Method for speed calculation
     * @param distance
     * @param time
     * @return
     */
    private double calcSpeed(double distance, double time) {
        return distance / time;
    }

    /**
     * Helper Method for time in hours calculation
     * @param source
     * @param destination
     * @return
     */
    private double calcTimeInHours(Position source, Position destination) {
        long startTime = source.getTimestamp();
        long endTime = destination.getTimestamp();

        long difference = endTime - startTime;
        return (double) difference / SecondToHourly;
    }

    /**
     * Helper Method for distance calculation
     * @param source
     * @param destination
     * @return
     */
    private double calcDistance(Position source, Position destination) {
        return HaversineDistance.getDistance(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }

}
