package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FareCalculatorTest {

    final FareCalculator fareCalculator = new FareCalculator ();

    @Test
    void calculateMethodEmptyPositionTest_01 ( ) {
        Ride ride = new Ride(1);
        assertEquals(-1,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethodMinSpeedTest_02 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1405594966 );
        Position position2 = new Position ( 1,37.966660,23.728308,1405594957 );
        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(3.47,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethodAvgSpeedTest_03 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1405594966 );
        Position position2 = new Position ( 1,37.935597,23.625688,1405596212 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(8.43,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethodAvgSpeedNightTimeFareCalculationTest_04 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1601092635 );
        Position position2 = new Position ( 1,37.935597,23.625688,1601094315 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(13.82,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethodMaxSpeedTest_05 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1405594966 );
        //invalid entry
        Position position2 = new Position ( 1,30.935597,20.728308,1405596212 );
        Position position3 = new Position ( 1,37.935597,23.625688,1405596212 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);
        positionList.add(position3);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(8.43,fareCalculator.calculate ( ride ));
    }
}