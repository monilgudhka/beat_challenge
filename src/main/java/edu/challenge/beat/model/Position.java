package edu.challenge.beat.model;

import lombok.Data;

@Data
public class Position {

    private int rideId;
    private double latitude;
    private double longitude;
    private long timestamp;

}
