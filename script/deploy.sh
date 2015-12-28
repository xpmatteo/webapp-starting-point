#!/bin/bash
#
# Purpose: deploy the application on heroku.
#
# Before calling this script, you should:
#  0. register yourself on Heroku
#  1. create a new application on Heroku
#  2. install the "heroku toolbelt" (see instructions on heroku's website)
#  3. write the name of the Main class you want to use in the Procfile
#  4. execute 
#        heroku login
#        heroku git:remote -a <yourappname>

git push heroku master

# Now open http://<yourappname>.herokuapp.com
# If it does not work, execute
#    heroku logs