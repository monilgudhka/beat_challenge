package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class converting the string record(from input file) into object
 */
@RequiredArgsConstructor
public class Converter {

    private static Logger logger = LoggerFactory.getLogger(Converter.class);

    /**
     * Method to convert string record data into Position object
     * @param record
     * @return
     */
    public Position convert(final String record) {
        final String[] data = record.split(",");
        final int rideId = Integer.parseInt(data[0]);
        final double latitude = Double.parseDouble(data[1]);
        final double longitude = Double.parseDouble(data[2]);
        final long timestamp = Long.parseLong(data[3]);
        return new Position(rideId, latitude, longitude, timestamp);
    }

    /**
     * Method to return the output in required format
     * @param ride
     * @return
     */
    public String convert(final Ride ride) {
        return ride.getId() + "," + ride.getFare ();
    }

}
