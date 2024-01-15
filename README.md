## How to run (I don't know what I'm doing, but this works)

### Mac or Linux
- `sh ./run_simple.sh`

### Windows
- `.\run_simple.ps1`


## Step by step if above options don't work

### First time grabbing the code, build games_common
1. `cd games_common`
2. `mvn install`
3. `mvn clean package`

### Build and run simple_games
1. `cd simple_games`
2. If dependencies have been updated, run: `mvn install`
3. To create the target files: `mvn clean package`
4. The run the main function: `mvn compile exec:java -D"exec.mainClass=com.pinqy.games.simple_games.Main"`