# Introduction

This is a project that demonstrates deploying a DropWizard project on Heroku.
This project, through Dropwizard framework, handles a GET request to http://drift-slack-demo.herokuapp.com/weather by returning a hardcoded string. The resource class that handles this request is under src/main/java/com/example/weatherbot/resources/WeatherResource.java. 


# Run This Application Locally

First, cd into the directory you want to run this project in. Then, run the following commands. 

- Clone the project 
```sh
$ git clone https://github.com/jennakwon06/dropwizard_heroku_weatherbot.git
```
- Build the project
```sh
$ mvn clean install
```
- Run the server locally 
```sh
$ java -jar target/weatherbot-1.0.1-SNAPSHOT.jar server weatherbot.yml
```
- Check http://localhost:8080/weather. port 8080 is specified in weatherbot.yml. 

# Run This Application on Heroku 

You can see that this app is currently running on Heroku at http://drift-slack-demo.herokuapp.com/weather. 
If you would like to run your Dropwizard project on Heroku as well, you must go through the below procedures.

- Sign up for a free account if you don't have one already, and install Heroku Toolbelt. 
- Log into your credentials
```sh
$   heroku login
Enter your Heroku credentials.
Email: example@example.com
Password (typing will be hidden): 
Authentication successful.
```
- cd into your Dropwizard project repo and create a new heroku project 
```sh
$ heroku create mydropwizardapp
Creating example-app… done, stack is cedar
http://mydropwizardapp.herokuapp.com/ | git@heroku.com:mydropwizardapp.git
Git remote heroku added
```
- Allocate dyno resources & scale your web
```sh
$ git push heroku master
$ heroku ps:scale web=1
```
- Check your project at http://mydropwizardapp.herokuapp.com/ or at http://mydropwizardapp.herokuapp.com/*, where * are your Resources with @Path set in /src/main/java/com/pkgname/resources/*.java. You must register your resources under YourAppApplication.java (for example, see run method in /src/main/java/com/example/weatherbot/WeatherBotApplication.java


# Notes for deployment on Heroku

- Procfile: For hosting on Heroku, you must ensure the project has a Procfile entry. Heroku will assume a Java build
after finding pom.xml in the project root and run the commands in the Procfile. The procfile is in the following format:
"web: java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/weatherbot-1.0.1-SNAPSHOT.jar server weatherbot.yml"
Note the usage of $JAVA_OPTS and $PORT. $PORT is overridden by Heroku, and $JAVA_OPTS is set dynamically by heroku.
- weatherbot.yml: Here, database and server configurations are overridden by config variables in Heroku. To create a project with
a database connection, set the DATABASE_URL config var with a "heroku config:set" command. Setting up your server configuration correctly
can be done by making sure it mirrors -Ddw.server.connector.port command in your Procfile.
