package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import edu.challenge.beat.util.StringUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class reading the input file, send data for processing
 * and write the required output data
 * into the output.txt file
 */
@RequiredArgsConstructor
public class BeatChallenge {

    private final Converter converter;
    private final PositionAggregator aggregator;
    private final FareCalculator fareCalculator;
    private static Logger logger = LoggerFactory.getLogger(BeatChallenge.class);

    /**
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException
     */
    public void run(final Path inputFilePath, final Path outputFilePath) throws IOException {
        try (
                BufferedReader reader = Files.newBufferedReader(inputFilePath);
                BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.CREATE)
        ) {
            for (AtomicReference < String > record = new AtomicReference <> ( reader.readLine ( ) ); record.get ( ) != null; record.set ( reader.readLine ( ) )) {

                if ( StringUtil.checkTrimEmpty ( record.get ( ) )){
                    continue;
                }
                final Position position = converter.convert( record.get ( ) );
                processAndWriteRecord ( writer , position );

            }
            /**
             * End of input check, that means last ride has been processed
             */
            processAndWriteRecord ( writer , null );
        }
    }

    /**
     * Helper method for writing the processed data into the output file
     * @param writer
     * @param position
     * @throws IOException
     */
    private void processAndWriteRecord ( final BufferedWriter writer , final Position position ) throws IOException {
        final Optional < String > optionalOutput = process ( position );
        if ( optionalOutput.isPresent ( ) ) {
            final String outputData = optionalOutput.get ( );
            writer.write ( outputData );
            writer.newLine ( );
        }
    }

    /**
     * Helper Method for aggregating the ride data and calculate fare
     * once all the records of a ride are read then proceed with fare calculation
     * and finally ride object to string conversion is done
     * for writing into the output file
     * @param position
     * @return
     */
    private Optional<String> process(final Position position) {
        return aggregator.aggregate(position)
                .map(this::calculateFare)
                .map(converter::convert);
    }

    /**
     * Helper Method for calculating the individual ride fare
     * @param ride
     * @return
     */
    private Ride calculateFare(final Ride ride) {
        final double fare = fareCalculator.calculate(ride);
        ride.setFare(fare);
        return ride;
    }

}
