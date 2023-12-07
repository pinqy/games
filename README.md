## How to run (I don't know what I'm doing, but this works)

1. `cd simple_games`
2. If dependencies have been updated, run: `mvn install`
3. To create the target files: `mvn clean package`
4. The run the main function: `mvn compile exec:java -D"exec.mainClass=com.pinqy.games.simple_games.Main"`