## Program to calculate fare estimate of a ride based on its data.

# Assumptions
1. Data is always sorted
2. Entries are positive
3. No partial tuple
4. Ride fare is calculated at the end of the ride only, there is no intermediate stage
5. Input file records are comma-separated

# Installation and Setup
- This is a Java project so make sure you have 'Java 8' and 'Maven' installed/configured on your machine.
- Input and output file path can be mentioned in the 'config.properties' file

- Run BeatApplication through terminal::
1. Move to the directory where pom.xml exists
2. Build jar using the command "mvn clean install"
3. Once the jar is built run from the terminal using the below command
4. java -cp target/beat-0.0.1-SNAPSHOT.jar edu.challenge.beat.BeatApplication
5. Once the execution is successful then you should see the output in the "output.txt" file under the resources folder of the project


- Run BeatApplication file through IntelliJIDEA::
1. Plugins to Install: Maven Helper, Sonarlint, Lombok, Coverage
2. Setup project JDK to 1.8
3. Run BeatApplication.java file


- Run BeatApplication file through terminal on Linux(CentOS)::
1. yum install java-1.8.0-openjdk
2. yum install maven
3. Follow steps of "Run BeatApplication through terminal"


# Limitations
1. This program runs using only a single thread
2. All the values are bounded by the programming language constraints


# Features Needed
1. More parallelization to speed up the processing time


# Design Approach
1. Scan tuples serially
  - ignore empty tuples
2. Keep fetching until next ride starts
3. Calculate distance, time ,and speed of each segment in a Ride
4. Filter the tuple if calculated speed > 100 km/hr
5. Calculate current fare
6. Store the results
7. Repeat step 1


# Todo
1. Remove if-else and use design pattern as future requirements can change
3. Make this multithreaded
    3.1 either 1 reader thread and multiple consumer threads
    3.2 Lmax disruptor
7. Write detailed approach
8. FareCalculator line 50 within for loop?
9. test cases multiple places are hardcoded?
11. read about haversine distance calculation formula


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