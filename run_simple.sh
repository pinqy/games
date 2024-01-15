#!/bin/sh

# setup common files
cd ./games_common/
mvn install
mvn clean package

# build and run simple_games
cd ../simple_games/
mvn install
mvn clean package
mvn compile exec:java -D"exec.mainClass=com.pinqy.games.simple_games.Main"