package edu.challenge.beat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeatApplicationTest {

    private final Path output = Paths.get("src/test/resources/outputFile.csv");

    @AfterEach
    void deleteOutput( ) throws IOException {
        Files.deleteIfExists(output);
    }

    @Test
    void mainThrowsNullPointerExceptionTest_01 ( ) throws IOException {

        BeatApplication.main(null);

        List<String> data = Files.readAllLines(output);
        assertEquals( 1, data.size() );
        assertEquals ( "1,10.3", data.get(0) );
    }

}