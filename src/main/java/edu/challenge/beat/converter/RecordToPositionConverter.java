package edu.challenge.beat.converter;

import edu.challenge.beat.model.Position;

public class RecordToPositionConverter {

    public Position convert(String record){
        String[] data = record.split(",");
        int rideId = Integer.parseInt(data[0]);
        double latitude = Double.parseDouble(data[1]);
        double longitude = Double.parseDouble(data[2]);
        long timestamp = Long.parseLong(data[3]);
        return new Position(rideId, latitude, longitude, timestamp);
    }

}
