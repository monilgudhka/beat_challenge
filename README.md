## Program to calculate fare estimate of a ride based on its data.

# Assumptions
1. Data is always sorted
 - within ride sorting >> sort positions based on ts
 - aggregator logic >> identify whether ride is ended >> some ride end flag
2. Entries are positive
 - assumption no negative >> otherwise as per business requirement (ignore)
 - multiple format >> converter with multiple input format support
3. No partial tuple
 - according to business requirement (ignore)
4. Ride fare is calculated at the end of the ride only, there is no intermediate stage
 - might have duplicate values in output file per segment
5. Input file records are comma-separated
 - file format changes converter
6. Input is from single file and output needs to be in single file
 - per file BeatChallenge run method call
 - multithreading per file
7. Ideal time calcuation is considerd with minutes as well
 - if 59min then consider 1hr

# Installation and Setup
- This is a Java project so make sure you have 'Java 8' and 'Maven' installed/configured on your machine.
- Input and output file path can be mentioned in the 'config.properties' file

- Run BeatApplication through terminal::
1. Move to the directory where pom.xml exists
2. Build jar using the command "mvn clean install"
3. Once the jar is built, run from the terminal using the below command:
4. java -cp target/beat-0.0.1-SNAPSHOT-jar-with-dependencies.jar edu.challenge.beat.BeatApplication
5. Once the execution is successful then you should see the output in the "output.txt" file under the resources folder of the project


- Run BeatApplication file through IntelliJIDEA::
1. Plugins to Install: Maven Helper, Sonarlint, Lombok, Coverage
2. Setup project JDK to 1.8
3. Run BeatApplication.java file
4. If this does not work , then mark the 'main' folder as 'Sources Root' and 'test' folder as 'Test Source Root'...then try to run


- Run BeatApplication file through terminal on Linux(CentOS)::
1. yum install java-1.8.0-openjdk
2. yum install maven
3. Follow steps of "Run BeatApplication through terminal"


# Limitations
1. This program runs using only a single user thread and a java main thread
2. All the values are bounded by the programming language constraints


# Features Needed
1. More parallelization to speed up the processing time
 - trial and error based parallelization
2. BlockingQueue approach or distruptor approach would have been possible with more time.
3. More verbose unit testing
4. strategy or template pattern for fare calc???
5. two different types of converters >> input and output
6. separate logic/classes for distance,speed and time 
7. multi time zone
8. logging

# Design Approach
1. Scan tuples serially
  - ignore empty tuples
2. Keep fetching until next ride starts
3. Calculate distance, time ,and speed of each segment in a Ride
4. Filter the tuple if calculated speed > 100 km/hr
5. Calculate current fare
6. Store the results
- Please see 'Ride Fare Estimation.png'


# Benchmark Results
- Single-Threaded Program
- Machine details:
  - t3.medium

- Results:
 1) Input file size 1.34GB
 2) Time taken 33 seconds


# CodeCoverage Report
- CodeCoverage report can be found under the 'codecoverage/index.html' folder
- Current coverage percentage: 90%