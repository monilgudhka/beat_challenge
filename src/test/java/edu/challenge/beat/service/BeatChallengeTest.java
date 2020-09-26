package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BeatChallengeTest {

    private final Converter converter = mock(Converter.class);

    private final PositionAggregator positionAggregator = mock(PositionAggregator.class);

    private final FareCalculator fareCalculator = mock(FareCalculator.class);

    private final BeatChallenge beatChallenge = new BeatChallenge(converter, positionAggregator, fareCalculator);

    private final Path input = Paths.get("src/test/resources/input.csv");

    private final Path output = Paths.get("src/test/resources/output.csv");

    @AfterEach
    void deleteInputFile() throws IOException {
        Files.deleteIfExists(input);
    }

//    @AfterEach
//    void deleteOutputFile() throws IOException {
//        Files.deleteIfExists(output);
//    }

    @Test
    void runMethodShouldProcessTest_01() throws IOException {
        Files.write(input, "position".getBytes());

        Ride ride = new Ride(1);
        Position position = new Position(1, 37, 23, 1405594957);

        when(converter.convert("position")).thenReturn(position);
        when(positionAggregator.aggregate(position)).thenReturn(Optional.of(ride));
        when(fareCalculator.calculate(ride)).thenReturn(new Double(10));
        when(converter.convert(ride)).thenReturn("ride");

        beatChallenge.run(input, output);

        assertEquals(10, ride.getFare());
        List<String> data = Files.readAllLines(output);
        assertEquals(1, data.size());
        assertEquals("ride", data.get(0));
    }

    @Test
    void runMethodEmptyRecordTest_02() throws IOException {
        Files.write(input, "position\n   ".getBytes());

        beatChallenge.run(input, output);

        List<String> data = Files.readAllLines(output);
        assertEquals ( 0,data.size () );
    }
}