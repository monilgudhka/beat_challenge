package edu.challenge.beat.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Model class with rideId, latitude, longitude and timestamp for each record
 */
@Data
@RequiredArgsConstructor
public class Position {

    private final long rideId;
    private final double latitude;
    private final double longitude;
    private final long timestamp;

}
