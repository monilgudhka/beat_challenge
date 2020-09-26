package edu.challenge.beat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeatApplicationTest {

    private final Path output = Paths.get("src/test/resources/outputFile.csv");

    @AfterEach
    void deleteOutput( ) throws IOException {
        Files.deleteIfExists(output);
    }

    @Test
    void mainMethodIntegrationTest_01 ( ) throws IOException {

        BeatApplication.main(null);

        List<String> data = Files.readAllLines(output);
        assertEquals( 2, data.size() );
        assertEquals ( "1 , 10.3", data.get(0) );
    }

}