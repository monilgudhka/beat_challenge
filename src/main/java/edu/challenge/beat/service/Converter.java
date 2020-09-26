package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;

/**
 * Class converting the string tuple(from input file) into object
 */
public class Converter {

    /**
     * Method to convert string tuple data into Position object
     * @param record
     * @return
     */
    public Position convert(String record) {
        String[] data = record.split(",");
        int rideId = Integer.parseInt(data[0]);
        double latitude = Double.parseDouble(data[1]);
        double longitude = Double.parseDouble(data[2]);
        long timestamp = Long.parseLong(data[3]);
        return new Position(rideId, latitude, longitude, timestamp);
    }

    /**
     * Method to return the output in required format
     * @param ride
     * @return
     */
    public String convert(Ride ride) {
        return ride.getId() + "," + ride.getFare();
    }

}
