package edu.challenge.beat;

import edu.challenge.beat.service.BeatChallenge;
import edu.challenge.beat.service.Converter;
import edu.challenge.beat.service.FareCalculator;
import edu.challenge.beat.service.PositionAggregator;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Main class
 */
public class BeatApplication {

    final private Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        long st = System.currentTimeMillis ();

        //fetch input and output file names
        Properties properties = new Properties();
        String appConfigPath = "config.properties";
        InputStream inputStream = Thread.currentThread ().getContextClassLoader ().getResourceAsStream ( appConfigPath );
        properties.load(inputStream);

        //read the file paths
        Path inputFile = Paths.get(properties.getProperty ( "inputFilePath" ));
        Path outputFile = Paths.get(properties.getProperty ( "outputFilePath" ));

        //main task logic
        Converter converter = new Converter();
        PositionAggregator aggregator = new PositionAggregator();
        FareCalculator fareCalculator = new FareCalculator();

        BeatChallenge challenge = new BeatChallenge(converter, aggregator, fareCalculator);
        challenge.run(inputFile, outputFile);


        long et = System.currentTimeMillis ();
        System.out.println("Time taken to run the task(in milliseconds)= "+ (et - st));
    }

}
