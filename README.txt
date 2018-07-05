SpringBoot - Restful Web Services


Application to create rest end-point which interact with external applications and database to populate right data to the consumer. Application
developed with technology available in current market trends.

Prerequisites
Java 1.7/1.8
NoSQL DB - Cassandra
SpringBoot and Groovy framework
Intellij/Eclipse IDE
Maven

Steps to Run this Application:

1. Checkout the branch
2. Start the DB server from installed path
      for windows:   C:\Program Files\DataStax-DDC\apache-cassandra\bin\cassandra.bat
3. use springboot command to start the server -> mvn spring-boot:run

4. service end-point details:-
    1. /product/{productId} --> GET Method - this service response can be used for put call which below mentioned.
    1. /product/{productId} with request body PUT METHOD