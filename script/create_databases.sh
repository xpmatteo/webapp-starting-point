#!/bin/bash
#
# Purpose: create all needed databases for the application,
# loading the schema and the test data

# define key information
src=src/main/sql
project=myproject
dbpassword="secret"

# no customization needed beyond this line
db_development=${project}_development
db_test=${project}_test
dbuser=$project

# Stop at the first error
set -e

# Go to the main project directory
cd "$(dirname $0)/.."

# if we're on Ubuntu
if uname -a | grep -qi ubuntu; then
  # if the postgres user for the current login does not exist
  if ! psql -tAc "select 3 + 4" template1 > /dev/null 2> /dev/null; then
    # then create the postgres user with superuser privileges
    sudo -u postgres createuser --superuser $(whoami)
  fi
fi

# create user
createuser --no-superuser --createdb --no-createrole $dbuser || true

# create databases
for db in $db_development $db_test; do
	echo doing $db

	dropdb --if-exists $db
	createdb $db
	psql -tAc "ALTER USER $dbuser WITH PASSWORD '$dbpassword'" $db

	# load all sql scripts in database
	PASSWORD="$dbpassword" cat $src/???_*.sql $src/seed.sql | psql $db --username=${dbuser}
done

echo "OK"
