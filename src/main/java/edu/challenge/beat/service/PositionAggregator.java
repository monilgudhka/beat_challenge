package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * Class aggregating the individual ride positions
 */
@RequiredArgsConstructor
public class PositionAggregator {
    /**
     * Ride instance used for creating individual ride objects
     */
    private Ride ride;

    /**
     * Method for creating Ride and List of Positions of the ride
     * @param position
     * @return
     */
    public Optional<Ride> aggregate(final Position position) {
        // End of input, last ride processed
        if (Objects.isNull ( position )) {
            final Ride currentRide = ride;
            ride = null;
            return Optional.of(currentRide);
        }
        // For first ride
        if (Objects.isNull ( ride )) {
            ride = new Ride(position.getRideId());
        }
        //If ride is still on
        if (ride.getId() == position.getRideId()) {
            ride.getPositions().add(position);

            //As ride has not completed so returning empty optional
            return Optional.empty();
        } else {
            final Ride newRide = ride;
            ride = new Ride(position.getRideId());
            ride.getPositions().add(position);
            //Ride completed signal
            return Optional.of(newRide);
        }
    }
}
