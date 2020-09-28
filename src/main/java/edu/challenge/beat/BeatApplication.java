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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class
 */
public class BeatApplication {

    /**
     * Fetch input and output file paths
     */
    private static final Properties PROPERTIES = new Properties();
    private static final String APP_CONFIG_PATH = "config.properties";

    private static final Logger logger = LoggerFactory.getLogger(BeatApplication.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        logger.info("Application execution started..");
        long st = System.currentTimeMillis ();

        try(InputStream inputStream = Thread.currentThread ()
                .getContextClassLoader ().getResourceAsStream ( APP_CONFIG_PATH )) {
            PROPERTIES.load ( inputStream );
        }

        /**
         * Read the file paths
         */
        final Path inputFile = Paths.get( PROPERTIES.getProperty ( "inputFilePath" ));
        final Path outputFile = Paths.get( PROPERTIES.getProperty ( "outputFilePath" ));

        /**
         * Main task logic
         */
        final Converter converter = new Converter();
        final PositionAggregator aggregator = new PositionAggregator();
        final FareCalculator fareCalculator = new FareCalculator();

        final BeatChallenge challenge = new BeatChallenge(converter, aggregator, fareCalculator);
        challenge.run(inputFile, outputFile);

        long et = System.currentTimeMillis ();
        logger.info ("Execution ended! \n Time taken to run the task(in milliseconds)= {} ",(et - st));
    }

}
