package edu.challenge.beat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BeatApplicationTest {

    @Test
    void mainThrowsNullPointerExceptionTest_01 ( ) {
        assertThrows( NullPointerException.class, () -> {
            Properties properties = new Properties();
            String appConfigPath = "app.properties";
            InputStream inputStream = Thread.currentThread ().getContextClassLoader ().getResourceAsStream ( appConfigPath );
            properties.load(inputStream);
        });
    }

    @Test
    void mainThrowsNullPointerExceptionTest_02 ( ) {
        assertThrows( NullPointerException.class, () -> {
            Properties properties = new Properties();
            Path inputFile = Paths.get(properties.getProperty ( "inputFilePath" ));
        });
    }

    @Test
    void mainThrowsIOExceptionTest_03 ( ) {
        assertThrows( NullPointerException.class, () -> {
            Properties properties = new Properties();
            String appConfigPath = "config.properties";
            InputStream inputStream = Thread.currentThread ().getContextClassLoader ().getResourceAsStream ( appConfigPath );
            properties.load(inputStream);

            //read the file paths
            Path inputFile = Paths.get(properties.getProperty ( "invalidFilePath" ));
        });
    }

    @Test
    @Disabled
    void mainMethodPropertiesFileLoadingTest_03 ( ) throws IOException {

    }
}