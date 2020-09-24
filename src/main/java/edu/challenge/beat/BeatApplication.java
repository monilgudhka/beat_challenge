package edu.challenge.beat;

import edu.challenge.beat.model.Position;
import edu.challenge.beat.service.Converter;
import edu.challenge.beat.service.PositionAggregator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeatApplication {

    private static final Converter converter = new Converter();
    private static final PositionAggregator aggregator = new PositionAggregator();

    public static void main(String[] args) throws IOException {
        Path input = Paths.get("src/main/resources/paths.csv");
        Path output = Paths.get("src/main/resources/output.csv");

        // TODO: Replace code with bulk read instead of all read
        List<String> inputData = Files.readAllLines(input, StandardCharsets.UTF_8);
        List<String> outputData = new ArrayList<>();

        for (String record : inputData) {
            Position position = converter.convert(record);
            process(position).ifPresent(outputData::add);
        }
        process(null).ifPresent(outputData::add);

        try (BufferedWriter writer = Files.newBufferedWriter(output, StandardOpenOption.CREATE)) {
            for (String data : outputData) {
                writer.write(data);
                writer.newLine();
            }
            writer.flush();
        }
    }

    private static Optional<String> process(Position position) {
        return aggregator.aggregate(position)
                .map(converter::convert);
    }

}
