#!/bin/sh
echo "Starting generate javadoc ..."
pwd
gradle prepareGit
if [ $? -gt 0 ]
  then
    exit $?
fi
git add docs

echo "Press any key for exit ..."
read a
exit $?
