## Program to calculate fair estimate of a ride based on its data.

# Assumptions
1. There are always at least 2 tuples per id.
2. Data is always sorted
3. Entries are positive
4. No null / partial tuple
5. Sample data file name does not change and its name is always inside the resources folder of the project.
6. Ride fair is calculated at the end of the ride only, there is no intermediate stage

# Installation and Setup
This is a java project so make sure you have Java 8 and Maven installed/configured on your machine.
Run BeatApplication file either through IDE or through terminal::
1. Build jar using command "mvn clean install"
2. Once jar is built run from terminal using below command
3. java -cp target/beat-0.0.1-SNAPSHOT.jar edu.challenge.beat.BeatApplication
4. Once the execution is successful then you should see output in the "output.csv" file under resources folder of the project


# Limitations
1. This program runs using only a single thread
2. All the values are bounded by the programming language constraints
3.

# Features Needed
1. More parallelization to speed up the processing time
2. With more money and more resources we could achieve this using a map-reduce functionality


# Design Approach
1. Scan tuples serially
2. Wait for next to Ride to start
3. Calculate distance, time and speed of each Segment in a Ride
4. Filter the tuple if calculated speed > 100 km/hr
5. Calculate current fare
6. Store the results
7. Repeat step 1
