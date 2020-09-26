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
     * Method for creating Ride and List of Positions of the ride
     * @param position
     * @return
     */
    public Optional<Ride> aggregate(Position position) {
        //end of input, last ride processed
        if (Objects.isNull ( position )) {
            Ride currentRide = ride;
            ride = null;
            return Optional.of(currentRide);
        }
        //first ride
        if (Objects.isNull ( ride )) {
            ride = new Ride(position.getRideId());
        }
        //ride is still not done
        if (ride.getId() == position.getRideId()) {
            ride.getPositions().add(position);
            //as ride has not completed so returning empty optional
            return Optional.empty();
        } else {
            Ride newRide = ride;
            ride = new Ride(position.getRideId());
            ride.getPositions().add(position);
            //ride completed
            return Optional.of(newRide);
        }
    }

}
