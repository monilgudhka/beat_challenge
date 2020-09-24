package edu.challenge.beat.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Ride model class with id, position and fare
 */
@Data
@RequiredArgsConstructor
public class Ride {

    private final long id;
    private final List<Position> positions = new ArrayList<>();
    private double fare;

}
