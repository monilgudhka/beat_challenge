package edu.challenge.beat.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Position {

    private final long rideId;
    private final double latitude;
    private final double longitude;
    private final long timestamp;

}
