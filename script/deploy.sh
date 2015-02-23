#!/bin/bash
#
# Purpose: deploy the application on heroku.
#
# Before calling this script, you should:
#  0. register yourself on Heroku
#  1. create a new application on Heroku
#  2. install the "heroku toolbelt" (see instructions on heroku's website)
#  3. execute 'git remote create heroku XXX' where XXX is the url of
#     your Heroku application

git push heroku master
