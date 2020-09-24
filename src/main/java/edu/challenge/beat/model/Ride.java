package edu.challenge.beat.model;

import lombok.Data;

import java.util.List;

@Data
public class Ride {

    private int id;
    private List<Position> positions;
    private double fare;

}
