package com.pinqy.games.simple_games;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.event.*;

import com.pinqy.games.games_common.GamePage;

public class SimpleGamePage extends GamePage {
    private Menu menu;
    private boolean hasLinkedMenu;
    
    public SimpleGamePage(String page_name) {
        super(page_name);
    }

    public void setMenu(Menu m) {
        if (!hasLinkedMenu) {
            menu = m;

            JButton backBtn = new JButton("Home");
            backBtn.setBounds(10, 10, 80, 30);
            backBtn.setFocusable(false);
            backBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (hasLinkedMenu) {
                        menu.returnToMenu();
                    }
                }
            });
            add(backBtn);
            repaint();
            
            hasLinkedMenu = true;
        }
    }
}
