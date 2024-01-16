package com.pinqy.games.simple_games;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<SimpleGamePage> games = new ArrayList<SimpleGamePage>();
        games.add(new Snake());

        Menu menu = new Menu(games);
        menu.show();
    }
}