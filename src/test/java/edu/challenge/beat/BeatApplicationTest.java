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

    private final Path output = Paths.get("src/test/resources/outputFile.txt");

    @AfterEach
    void deleteOutput( ) throws IOException {
        Files.deleteIfExists(output);
    }

    @Test
    void mainMethod_IntegrationTest_01 ( ) throws IOException {

        BeatApplication.main(null);

        List<String> data = Files.readAllLines(output);
        assertEquals( 2, data.size() );
        assertEquals ( "1,10.3", data.get(0) );
        assertEquals ( "2,7.96", data.get(1) );
    }

}