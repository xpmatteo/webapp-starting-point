
# A simple webapp template

This template can be used to start a new Java web app project, as well as for coding dojos and various exercises.

Examples of fun exercises to try:

 * Todo list  (see doc directory)
 * Semaphores
 * Lights Out


## Import the project in Eclipse with

    mvn eclipse:clean eclipse:eclipse -DdownloadSources -DdownloadJavadocs

## Import the project in Idea with

    mvn idea:idea

## Create the databases with

		script/create_databases.sh

## Run the application with

    mvn clean package
    script/run.sh
    open http://localhost:8080

## Deploy the application to Heroku

Follow the instructions in script/deploy.sh then run it.

