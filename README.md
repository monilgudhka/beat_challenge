## Program to calculate fair estimate of a ride based on its data.

#Assumptions
1. There are always at least 2 tuples per id.
2. Data is always sorted
3. Entries are positive
4. No null / partial tuple
5. Sample data file name does not change and its name is always inside the resources folder of the project.

#Installation and Setup
This is a java project so make sure you have Java 8 and Maven installed/configured on your machine.
Run BeatApplication file either through IDE or through terminal::
1. Move to the directory where BeatApplication.java resides then on terminal
2. javac BeatApplication.java
3. java BeatApplication
4. Once the execution is sucessful then you should see output in the "output.txt" file under resources folder of the project

#Limitations
1. This program runs using only single thread
2. All the values are bounded by the programming language constraints
3.

#Features Needed
1. More parallelization so as to speed up the processing time
2. With more money and more resources we could achieve this using a map-reduce fuctionality


#Design Approach
1. Scan 2 tuples serially
2. Filter the tuple if calculated speed > 100 km/hr
3. Calculate current fair
4. Store the results
5. Repeat step 1