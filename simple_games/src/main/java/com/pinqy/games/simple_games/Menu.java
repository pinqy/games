package com.pinqy.games.simple_games;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

import com.pinqy.games.games_common.GameFrame;
import com.pinqy.games.games_common.GamePage;

public class Menu extends GameFrame {
    private static GamePage menu_page;
    private static ArrayList<SimpleGamePage> games;
    
    public Menu(ArrayList<SimpleGamePage> games_list) {
        super(setupMenuPage(games_list), false);

        linkGamesToMenu();
    }

    private static GamePage setupMenuPage(ArrayList<SimpleGamePage> games_list) {
        games = games_list;
        menu_page = new GamePage();
        menu_page.setGameName("Menu");

        int btn_gap = 30;
        int num_rows = (int) Math.ceil(games.size() / 2.0);
        int btn_height = (int)((500 - (num_rows+1) * btn_gap) / num_rows);
        for (int i = 0; i < games.size(); i++) {
            int row = (int) Math.floor(i / 2) + 1;
            int btn_x = i % 2 == 0 ? 30 : 270;
            int btn_y = 50 + (btn_gap * row + btn_height * (row-1));

            // if last row and on left side, make full width
            int btn_width = (i == games.size()-1 && i % 2 == 0) ? 440 : 200;

            GamePage game = games.get(i);
            JButton gameBtn = new JButton(game.getGameName());
            gameBtn.setBounds(btn_x, btn_y, btn_width, btn_height);
            gameBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changePage(game);
                }
            });
            menu_page.add(gameBtn);
        }

        return menu_page;
    }

    private void linkGamesToMenu() {
        for (SimpleGamePage game : games) {
            game.setMenu(this);
        }
    }

    public void returnToMenu() {
        changePage(menu_page);
    }
}
