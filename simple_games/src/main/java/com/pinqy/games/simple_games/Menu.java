package com.pinqy.games.simple_games;

import com.pinqy.games.games_common.*;
import java.util.ArrayList;

public class Menu extends GameFrame {
    private static GamePage home_page;
    
    public Menu(ArrayList<GamePage> games) {
        super(setupMenuPage(games), false);
    }

    private static GamePage setupMenuPage(ArrayList<GamePage> games) {
        home_page = new GamePage();



        return home_page;
    }

    private class MenuPage extends GamePage {
        public MenuPage() {
            
        }
    }
}
