#!/bin/sh
gradle prepareGit
if [ $? -gt 0 ]
  then
    exit $?
fi
git add docs
exit $?
