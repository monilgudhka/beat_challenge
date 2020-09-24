package edu.challenge.beat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private int rideId;
    private double latitude;
    private double longitude;
    private long timestamp;

}
