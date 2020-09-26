package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.util.Objects;
import java.util.Optional;

/**
 * Class aggregating the individual ride positions
 */
public class PositionAggregator {

    private Ride ride;

    /**
     * @param position
     * @return
     */
    public Optional<Ride> aggregate(Position position) {
        if (Objects.isNull ( position )) {
            Ride currentRide = ride;
            ride = null;
            return Optional.of(currentRide);
        }
        if (Objects.isNull ( ride )) {
            ride = new Ride(position.getRideId());
        }
        if (ride.getId() == position.getRideId()) {
            ride.getPositions().add(position);
            return Optional.empty();
        } else {
            Ride newRide = ride;
            ride = new Ride(position.getRideId());
            ride.getPositions().add(position);
            return Optional.of(newRide);
        }
    }

}
