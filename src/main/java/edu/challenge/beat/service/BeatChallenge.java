package edu.challenge.beat.service;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.model.Ride;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BeatChallenge {

    private final Converter converter;
    private final PositionAggregator aggregator;
    private final FareCalculator fareCalculator;

    public void run(Path input, Path output) throws IOException {
        List<String> inputData = readData(input);
        List<String> outputData = new ArrayList<>();

        for (String record : inputData) {
            Position position = converter.convert(record);
            process(position).ifPresent(outputData::add);
        }
        //end of file operation
        process(null).ifPresent(outputData::add);

        writeData(output, outputData);
    }

    private void writeData(Path output, List<String> outputData) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(output, StandardOpenOption.CREATE)) {
            for (String data : outputData) {
                writer.write(data);
                writer.newLine();
            }
            writer.flush();
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

    private List<String> readData(Path input) throws IOException {
        return Files.readAllLines(input, StandardCharsets.UTF_8);
    }


}
