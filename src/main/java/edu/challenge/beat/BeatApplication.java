package edu.challenge.beat;

import edu.challenge.beat.service.BeatChallenge;
import edu.challenge.beat.service.Converter;
import edu.challenge.beat.service.FareCalculator;
import edu.challenge.beat.service.PositionAggregator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class
 */
public class BeatApplication {

    public static void main(String[] args) throws IOException {
        Path input = Paths.get("src/main/resources/paths.csv");
        Path output = Paths.get("src/main/resources/output.csv");
        //System.out.println("start time= "+System.currentTimeMillis ());
        long st = System.currentTimeMillis ();

        Converter converter = new Converter();
        PositionAggregator aggregator = new PositionAggregator();
        FareCalculator fareCalculator = new FareCalculator();

        BeatChallenge challenge = new BeatChallenge(converter, aggregator, fareCalculator);
        challenge.run(input, output);

        //System.out.println("end time= "+System.currentTimeMillis ());
        long et = System.currentTimeMillis ();
        System.out.println("Time taken in milliseconds= "+ (et - st));
    }

}
