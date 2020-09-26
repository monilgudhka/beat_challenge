package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    @Disabled
    void aggregatePositionRideNotNullTest_03 ( ){
        Ride ride = new Ride(1);
        ride.setFare ( 3.47 );
        assertEquals ( Optional.of ( ride ) , positionAggregator.aggregate ( position ) );
    }
}