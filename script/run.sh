#!/bin/bash
#
# Purpose: run the application
#
# Before you call this script, you should run 'mvn clean package'
#

set -e
cd "$(dirname $0)/.."

java -cp build/classes/main:"build/dependency/*" it.xpug.todolist.Main
