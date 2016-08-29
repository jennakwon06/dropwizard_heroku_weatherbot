# Introduction

This is a bare bone project for creating a DropWizard project and deploying it on Heroku.
This project handles a GET request to http://drift-slack-demo.herokuapp.com/weather by returning a hardcoded string.
The resource class that handles this request is under src/main/java/com/example/weatherbot/resources/WeatherResource.java


# Running The Application

To test the example application from command line, run the following commands.

* To package the project, run "mvn package"

* To run the server, run "java -jar target/weatherbot-1.0.1-SNAPSHOT.jar server weatherbot.yml"

# Notes for deployment on Heroku

- Procfile: For hosting on Heroku, you must ensure the project has a Procfile entry. Heroku will assume a Java build
after finding pom.xml in the project root and run the commands in the Procfile. The procfile is in the following format:
"web: java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/weatherbot-1.0.1-SNAPSHOT.jar server weatherbot.yml"
Note the usage of $JAVA_OPTS and $PORT. $PORT is overridden by Heroku, and $JAVA_OPTS is set dynamically by heroku.
- weatherbot.yml: Here, database and server configurations are overridden by config variables in Heroku. To create a project with
a database connection, set the DATABASE_URL config var with a "heroku config:set" command. Setting up your server configuration correctly
can be done by making sure it mirrors -Ddw.server.connector.port command in your Procfile.
