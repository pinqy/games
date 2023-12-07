package com.pinqy.games.simple_games;

import com.pinqy.games.games_common.*;

public class Main {
    public static void main(String[] args) {
        for(String str: args) {
            System.out.println(str);
        }
        Snake snake_game = new Snake();
        GameFrame game = new GameFrame(snake_game, false);
        game.show();
    }
}