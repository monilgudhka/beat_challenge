package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {

    final Converter converter = new Converter ();

    @Test
    void convertPositiveScenarioTest_01 ( ) {
        String dummyRecord = "1,37.966195,23.728613,1405595026";
        Position resultPosition = new Position (1,
                                          37.966195,
                                          23.728613,
                                          1405595026);
        assertEquals ( resultPosition , converter.convert ( dummyRecord ));
    }

    @Test
    void covertOverloadedPositiveScenarioTest_02 ( ) {
        Ride ride = new Ride(1);
        ride.setFare ( 3.47 );
        String result = "1,3.47";
        assertEquals ( result, converter.convert ( ride ));
    }
}