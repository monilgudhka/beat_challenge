package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionAggregatorTest {

    final PositionAggregator positionAggregator = new PositionAggregator ();
    static Position position;

    @BeforeAll
    static void setup ( ){
        position = new Position ( 1,
                37.966195,
                23.728613,
                1405595001);
    }

    @Test
    @Disabled
    void aggregatePositionNullTest_01 ( ) {
        Position position = null;
        assertNull(positionAggregator.aggregate ( position ));
    }

    @Test
    void aggregatePositionRideNullTest_02 ( ){
        assertEquals( Optional.empty (),positionAggregator.aggregate ( position ) );
    }

    @Test
    void aggregatePositionRideNotNullTest_03 ( ){
        Position positionOfRide2 = new Position(2,
                37.966195,
                23.728613,
                1405595001);
        assertEquals ( Optional.empty() , positionAggregator.aggregate ( position ) );
        Optional<Ride> aggregate = positionAggregator.aggregate(positionOfRide2);
        assertTrue(aggregate.isPresent());
        Ride ride = aggregate.get();
        assertEquals(1, ride.getId());
        assertEquals(1, ride.getPositions().size());
        assertEquals(position, ride.getPositions().get(0));

        aggregate = positionAggregator.aggregate(null);
        assertTrue(aggregate.isPresent());
        ride = aggregate.get();
        assertEquals(2, ride.getId());
        assertEquals(1, ride.getPositions().size());
        assertEquals(positionOfRide2, ride.getPositions().get(0));
    }
}