# Introduction

The Dropwizard example application was developed to, as its name implies, provide examples of some of the features
present in Dropwizard.

# Overview

Included with this application is an example of the optional DB API module. The examples provided illustrate a few of
the features available in [Hibernate](http://hibernate.org/), along with demonstrating how these are used from within
Dropwizard.

This database example is comprised of the following classes:

* The `PersonDAO` illustrates using the Data Access Object pattern with assisting of Hibernate.

* The `Person` illustrates mapping of Java classes to database tables with assisting of JPA annotations.

* All the JPQL statements for use in the `PersonDAO` are located in the `Person` class.

* `migrations.xml` illustrates the usage of `dropwizard-migrations` which can create your database prior to running
your application for the first time.

* The `PersonResource` and `PeopleResource` are the REST resource which use the PersonDAO to retrieve data from the database, note the injection
of the PersonDAO in their constructors.

As with all the modules the db example is wired up in the `initialize` function of the `HelloWorldApplication`.

# Running The Application

To test the example application run the following commands.

* To package the example run.

        mvn package

* To setup the h2 database run.

        java -jar target/dropwizard-example-1.0.0-rc3-SNAPSHOT.jar db migrate example.yml

* To run the server run.

        java -jar target/dropwizard-example-1.0.0-rc3-SNAPSHOT.jar server example.yml

* To hit the Hello World example (hit refresh a few times).

	http://localhost:8080/hello-world

* To post data into the application.

	curl -H "Content-Type: application/json" -X POST -d '{"fullName":"Other Person","jobTitle":"Other Title"}' http://localhost:8080/people

	curl -H "Content-Type: application/json" -X POST -d '{"areaCode":"11111","temperature":"90"}' http://localhost:8080/weather


	open http://localhost:8080/people

web: java $JAVA_OPTS -jar target/dropwizard-example-0.7.0.jar db migrate example.yml && java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/dropwizard-example-0.7.0.jar server example.yml

web: java $JAVA_OPTS -jar target/dropwizard-example-1.0.1-SNAPSHOT.jar db migrate example.yml && java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/dropwizard-example-1.0.1-SNAPSHOT.jar server example.yml
