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
 * Class reading the input file and write the required output data
 * into the output.csv file
 */
@RequiredArgsConstructor
public class BeatChallenge {

    private final Converter converter;
    private final PositionAggregator aggregator;
    private final FareCalculator fareCalculator;

    /**
     * Method for reading the input file and writing the result to the output file
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException
     */
    public void run(Path inputFilePath, Path outputFilePath) throws IOException {
        try (
                BufferedReader reader = Files.newBufferedReader(inputFilePath);
                BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.CREATE);
        ) {

            String record;
            while ((record = reader.readLine()) != null) {
                if (record.isEmpty ()){
                    continue;
                }
                //convert tuple into an object
                Position position = converter.convert(record);
                Optional<String> optionalOutput = process(position);
                if (optionalOutput.isPresent()) {
                    String outputData = optionalOutput.get();
                    writer.write(outputData);
                    writer.newLine();
                }
            }
            //end of input check
            Optional<String> optionalOutput = process(null);

            if (optionalOutput.isPresent()) {
                String outputData = optionalOutput.get();
                writer.write(outputData);
                writer.newLine();
            }
        }
    }

    /**
     * Helper Method for aggregating the ride data and calculate fare
     * @param position
     * @return
     */
    private Optional<String> process(Position position) {
        return aggregator.aggregate(position)
                .map(this::calculateFare)
                .map(converter::convert);
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
