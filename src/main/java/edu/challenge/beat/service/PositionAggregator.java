package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;

import java.util.Optional;

public class PositionAggregator {

    private Ride ride;

    public Optional<Ride> aggregate(Position position) {
        if (position == null) {
            Ride tempRide = ride;
            ride = null;
            return Optional.of(tempRide);
        }
        if (ride == null) {
            ride = new Ride(position.getRideId());
        }
        if (ride.getId() == position.getRideId()) {
            ride.getPositions().add(position);
            return Optional.empty();
        } else {
            Ride tempRide = ride;
            ride = new Ride(position.getRideId());
            ride.getPositions().add(position);
            return Optional.of(tempRide);
        }
    }

}
