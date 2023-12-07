package com.pinqy.games.games_common;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame {
    private JFrame frame;
    private GamePage page;
    private boolean resizeable;

    public GameFrame(GamePage page, boolean resizeable) {
        this.page = page;
        this.resizeable = resizeable;
    }

    // Functions to create/show Game
    public void show() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGame();
            }
        });
    }

    private void createAndShowGame() {
        frame = new JFrame("");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(resizeable);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing window.");
            }
        });

        frame.add(page);
        frame.pack();
        frame.setVisible(true);
    }

    // functions for game lifecycle
    public void changePage(GamePage newPage) {
        frame.add(newPage);

        page.setVisible(false);
        frame.remove(page);
        frame.repaint();

        page = newPage;
    }
}
