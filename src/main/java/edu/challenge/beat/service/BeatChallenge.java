package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * Class reading the input file, send data for processing and write the required output data
 * into the output.txt file
 */
@RequiredArgsConstructor
public class BeatChallenge {

    private final Converter converter;
    private final PositionAggregator aggregator;
    private final FareCalculator fareCalculator;

    /**
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException
     */
    public void run(Path inputFilePath, Path outputFilePath) throws IOException {
        try (
                BufferedReader reader = Files.newBufferedReader(inputFilePath);
                BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.CREATE)
        ) {

            String record;
            while ((record = reader.readLine()) != null) {
                //ignore empty records
                if (record.trim().isEmpty ()){
                    continue;
                }
                //convert record into an object
                Position position = converter.convert(record);
                processAndWriteRecord ( writer , position );
            }
            //end of input check, that means last ride has been processed
            processAndWriteRecord ( writer , null );
        }
    }

    /**
     * Helper method for writing the processed data into the output file
     * @param writer
     * @param position
     * @throws IOException
     */
    private void processAndWriteRecord ( BufferedWriter writer , Position position ) throws IOException {
        Optional < String > optionalOutput = process ( position );
        if ( optionalOutput.isPresent ( ) ) {
            String outputData = optionalOutput.get ( );
            writer.write ( outputData );
            writer.newLine ( );
        }
    }

    /**
     * Helper Method for aggregating the ride data and calculate fare
     * @param position
     * @return
     */
    private Optional<String> process(Position position) {
        return aggregator.aggregate(position)
                .map(this::calculateFare) //once all the records of a ride are read then proceed with fare calculation
                .map(converter::convert); //ride object to string conversion to write into the output file
    }

    /**
     * Helper Method for calculating the individual ride fare
     * @param ride
     * @return
     */
    private Ride calculateFare(Ride ride) {
        double fare = fareCalculator.calculate(ride);
        ride.setFare(fare);
        return ride;
    }

}
