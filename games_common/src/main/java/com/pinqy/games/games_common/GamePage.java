package com.pinqy.games.games_common;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.*;

public class GamePage extends JPanel {
    public GamePage() {
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (e.isControlDown()) {
                        closeFrame();
                    }
                }
            }
        });
    }

    private void closeFrame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    // Default overrides
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 550);
    }

    // Common functions for drawing in page
    public void drawCenteredString(Graphics g, int center_x, int center_y, String text) {
        FontMetrics fm = g.getFontMetrics();

        int str_x = center_x - (fm.stringWidth(text) / 2);
        int str_y = center_y - (fm.getHeight() / 2) + fm.getAscent();

        g.drawString(text, str_x, str_y);
    }

    public void drawCenteredHeaderText(Graphics g, String str) {
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 32));
        drawCenteredString(g, this.getWidth()/2, 25, str);
    }
}
