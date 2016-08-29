# Introduction

This is a demonstration of creating a Dropwizard project that is hosted on Heroku.
This app handles a GET request to http://drift-slack-demo.herokuapp.com/weather and returns a hardcoded string.

# Running The Application

To test the example application run the following commands.

* To package the example run.
        mvn package

* To run the server run.
        java -jar target/dropwizard-example-1.0.1-SNAPSHOT.jar server weatherbot.yml

# What is different for Dropwizard on Heroku vs. Dropwizard on Localhost?

- Note the difference in Procfile. For hosting on Heroku, you must use
"web: java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/weatherbot-1.0.1-SNAPSHOT.jar server weatherbot.yml"
$PORT is overridden by Heroku, and $JAVA_OPTS is set dynamically by heroku.
- Note the changes in weatherbot.yml especially under server connector.

