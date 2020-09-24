package edu.challenge.beat.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Ride {

    private final int id;
    private final List<Position> positions = new ArrayList<>();
    private double fare;

}
