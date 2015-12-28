#!/bin/bash
#
# Purpose: undo everything that create_databases.sh does

# Make sure this script works on both Linux and Mac
if id postgres > /dev/null 2> /dev/null; then
  function doit () {
    sudo -u postgres $*
  }
else
  function doit () {
    env $*
  }
fi

doit dropdb myproject_development
doit dropdb myproject_test
doit dropuser myproject

