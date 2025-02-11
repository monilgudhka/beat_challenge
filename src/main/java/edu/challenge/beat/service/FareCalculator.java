package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import edu.challenge.beat.util.AppConstantsUtil;
import edu.challenge.beat.util.HaversineDistanceUtil;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class calculating fare for each ride considering its speed,
 * time taken for the ride
 */
@RequiredArgsConstructor
public class FareCalculator {

    private static final int SECOND_TO_HOURLY = 3600;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static final Logger logger = LogManager.getLogger( FareCalculator.class.getName());

    /**
     * Method for calculating fare for each ride
     * @param ride
     * @return
     */
    public double calculate(final Ride ride) {
        final List<Position> positions = ride.getPositions();
        if (positions.isEmpty()) {
            return -1;
        }
        double totalFare = AppConstantsUtil.INITIAL_FARE;
        double idleTime = 0;

        //Get the first record as a source point of the ride
        Position source = positions.get(0);

        //Iterate over remaining positions of a ride
        for (int index = 1; index < positions.size(); index++) {
            final Position destination = positions.get(index);

            if(logger.isDebugEnabled ())
                logger.debug ( destination.toString () );

            final double distance = calcDistance(source, destination);
            final double time = calcTimeInHours(source, destination);
            final double speed = calcSpeed(distance, time);

            if(logger.isDebugEnabled ())
                logger.debug("Distance={} Time={} Speed={}",distance,time,speed);

            if (speed <= AppConstantsUtil.MIN_SPEED ) {
                idleTime += time;
                source = destination;
            } else if (speed <= AppConstantsUtil.MAX_SPEED ) {
                final double fare = calcFare(distance, source.getTimestamp());
                totalFare += fare;
                source = destination;
            }
        }
        //calculate total fare once all the positions are processed
        totalFare += (idleTime * AppConstantsUtil.IDLE_TIME_PER_HOURLY_FARE);

        //Return fare with 2 decimal points
        return Double.parseDouble ( decimalFormat.format ( Math.max(totalFare, AppConstantsUtil.MIN_RIDE_FARE ) ));
    }

    /**
     * Based on ride timings decide the fare
     * If ride hour is between 5AM - 12PM then daytimeFare
     * otherwise nighttimeFare
     * @param distance
     * @param timestamp
     * @return
     */
    private double calcFare(final double distance, final long timestamp) {
        final int hour = Instant.ofEpochSecond(timestamp)
                .atOffset(ZoneOffset.UTC)
                .toLocalTime().getHour();

        if(logger.isDebugEnabled ())
            logger.debug ( "hour={} distance={} ts={}",hour,distance,timestamp );
        return (AppConstantsUtil.RIDE_END_HOUR <= hour && hour <= AppConstantsUtil.RIDE_START_HOUR)
                ? AppConstantsUtil.NIGHT_TIME_PER_KM_FARE * distance
                : AppConstantsUtil.DAY_TIME_PER_KM_FARE * distance;
    }

    /**
     * Helper Method for speed calculation
     * @param distance
     * @param time
     * @return
     */
    private double calcSpeed(final double distance, final double time) {
        return distance / time;
    }

    /**
     * Helper Method for time in hours calculation
     * @param source
     * @param destination
     * @return
     */
    private double calcTimeInHours(final Position source, final Position destination) {
        final long startTime = source.getTimestamp();
        final long endTime = destination.getTimestamp();

        final long difference = Math.abs(endTime - startTime);
        return (double) difference / SECOND_TO_HOURLY;
    }

    /**
     * Helper Method for distance calculation
     * @param source
     * @param destination
     * @return
     */
    private double calcDistance(final Position source, final Position destination) {
        return HaversineDistanceUtil.getDistance(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }

}
