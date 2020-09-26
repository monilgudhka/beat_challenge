package edu.challenge.beat.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeatChallengeTest {

    final BeatChallenge beatChallenge = new BeatChallenge (  new Converter (), new PositionAggregator (), new FareCalculator ());

    @Test
    void runMethodShouldThrowIOExceptionTest_01 ( ) {
        assertThrows( IOException.class, () -> {
            BufferedReader reader = Files.newBufferedReader(Paths.get ( "/emptypath"));
        });
    }

    @Test
    void runMethodShouldThrowIOExceptionTest_02 ( ) {
        assertThrows( IOException.class, () -> {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get ( "/emptypath" ), StandardOpenOption.CREATE);
        });
    }

    @Test
    void runMethodSuccessScenarioTest_03 ( )throws IOException {
        Path inputFilePath = Paths.get("src/test/resources/paths.csv");
        Path outputFilePath = Paths.get("src/test/resources/output.csv");
        BufferedReader reader = Files.newBufferedReader(outputFilePath);
        beatChallenge.run ( inputFilePath,outputFilePath );
        assertEquals ( reader.readLine ( ), "1,10.3" );
    }


}