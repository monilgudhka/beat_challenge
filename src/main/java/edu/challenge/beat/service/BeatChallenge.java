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
 * BeatChallenge class reading the input file and write the required output data
 * into the output.csv file
 */
@RequiredArgsConstructor
public class BeatChallenge {

    private final Converter converter;
    private final PositionAggregator aggregator;
    private final FareCalculator fareCalculator;

    public void run(Path input, Path output) throws IOException {
        try (
                BufferedReader reader = Files.newBufferedReader(input);
                BufferedWriter writer = Files.newBufferedWriter(output, StandardOpenOption.CREATE)
        ) {

            String record;
            while ((record = reader.readLine()) != null) {
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

    private Optional<String> process(Position position) {
        return aggregator.aggregate(position)
                .map(this::calculateFare)
                .map(converter::convert);
    }

    private Ride calculateFare(Ride ride) {
        double fare = fareCalculator.calculate(ride);
        ride.setFare(fare);
        return ride;
    }

}
