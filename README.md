
# A simple webapp template

This template can be used to start a new Java web app project, as well as for coding dojos and various exercises.

Examples of fun exercises to try:

 * Todo list  (see doc directory)
 * Semaphores
 * Lights Out


## Import the project in Eclipse with

    ./gradlew eclipse

## Import the project in Idea with

    ./gradlew idea

## Create the databases with

		script/create_databases.sh

## Run the application with

    ./gradlew assemble
    script/run.sh
    open http://localhost:8080

## Deploy the application to Heroku

Follow the instructions in script/deploy.sh then run it.

