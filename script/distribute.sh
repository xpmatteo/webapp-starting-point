#!/bin/bash
#
# Purpose: build a zip

set -e
DEST=/tmp/dojo.zip
rm -f $DEST || true
git archive --format=zip --prefix=$(basename $DEST .zip)/ HEAD > $DEST
ls -l $DEST
