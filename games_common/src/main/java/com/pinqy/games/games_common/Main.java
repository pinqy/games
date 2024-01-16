package com.pinqy.games.games_common;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world! This just shows a test frame...");

        GameFrame game = new GameFrame(new GamePage("test"), false);
        game.show();
    }
}