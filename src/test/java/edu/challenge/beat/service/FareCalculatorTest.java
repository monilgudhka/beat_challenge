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
    void calculateMethod_EmptyPosition_Test_01 ( ) {
        Ride ride = new Ride(1);
        assertEquals(-1,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethod_MinSpeed_Test_02 ( ) {
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
    void calculateMethod_AvgSpeed_Test_03 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1405594966 );
        Position position2 = new Position ( 1,37.935597,23.625688,1405596212 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(8.43,fareCalculator.calculate ( ride ));
    }
    /*
    * manual calculation
    * dist = 9.633043980754872
    * time = 0.46
    * speed = 20+
    * fare = 13.82
    */
    @Test
    void calculateMethod_AvgSpeedNightTimeFareCalculation_Test_04 ( ) {
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
    void calculateMethod_AvgSpeedPMToAMTimeFareCalculation_Test_05 ( ) {
        //11PM - 3AM ride
        Position position1 = new Position ( 1,37.966627,23.728263,1601162660 );
        Position position2 = new Position ( 1,37.961403,23.721222,1601164767 );
        Position position3 = new Position ( 1,37.913403,23.721222,1601165427 );
        Position position4 = new Position ( 1,37.66,23.732,1601169860 );
        Position position5 = new Position ( 1,37.45,23.12,1601173460 );
        Position position6 = new Position ( 1,37.12,23.45,1601177060 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);
        positionList.add(position3);
        positionList.add(position4);
        positionList.add(position5);
        positionList.add(position6);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(179.28,fareCalculator.calculate ( ride ));
    }

    @Test
    void calculateMethod_MaxSpeed_Test_06 ( ) {
        Position position1 = new Position ( 1,37.966627,23.728263,1405594966 );
        //invalid entry, U > 100kmph
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

    @Test
    void calculateMethod_AvgSpeedAMToAMTimeFareCalculation_Test_07 ( ) {
        //12AM - 6AM ride
        Position position1 = new Position ( 1,37.913403,23.721222,1601165427 );
        Position position2 = new Position ( 1,37.66,23.732,1601169860 );
        Position position3 = new Position ( 1,37.45,23.12,1601173460 );
        Position position4 = new Position ( 1,37.12,23.45,1601177060 );
        Position position5 = new Position ( 1,37.89,23.45,1601180580 );
        Position position6 = new Position ( 1,37.92,23.45,1601184120 );

        List <Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);
        positionList.add(position3);
        positionList.add(position4);
        positionList.add(position5);
        positionList.add(position6);

        Ride ride = Mockito.mock ( Ride.class );
        when(ride.getPositions ()).thenReturn(positionList);

        assertEquals(286.64,fareCalculator.calculate ( ride ));
    }
}