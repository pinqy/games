# setup common files
Set-Location ./games_common/
mvn install
mvn clean package

# build and run simple_games
Set-Location ../simple_games/
mvn install
mvn clean package
mvn compile exec:java -D"exec.mainClass=com.pinqy.games.simple_games.Main"

# move back to main directory
Set-Location ../