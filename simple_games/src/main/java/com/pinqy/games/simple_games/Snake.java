package com.pinqy.games.simple_games;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Snake extends SimpleGamePage {
    private ArrayList<Integer[]> body = new ArrayList<Integer[]>();
    private Integer[] food_loc = new Integer[2];
    private Integer[] head_loc = new Integer[2];
    private int snake_dir = 0;
    private boolean has_turned = false;
    private boolean snake_alive = true;
    private JButton resetButton;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private class Dir {
        private static final int NONE = 0;
        private static final int UP = 1;
        private static final int RIGHT = 2;
        private static final int DOWN = 3;
        private static final int LEFT = 4;
    }

    public Snake() {
        super("Snake");

        executor.scheduleAtFixedRate(stepGame, 500, 75, TimeUnit.MILLISECONDS);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_UP || keyCode==KeyEvent.VK_W) {
                    turn(Dir.UP);
                } else if(keyCode==KeyEvent.VK_RIGHT || keyCode==KeyEvent.VK_D) {
                    turn(Dir.RIGHT);
                } else if(keyCode==KeyEvent.VK_DOWN || keyCode==KeyEvent.VK_S) {
                    turn(Dir.DOWN);
                } else if(keyCode==KeyEvent.VK_LEFT || keyCode==KeyEvent.VK_A) {
                    turn(Dir.LEFT);
                } else if(keyCode==KeyEvent.VK_ENTER) {
                    resetGame();
                }
            }
        });

        // add back button

        resetButton = new JButton("Play Again");
        resetButton.setBounds(390, 10, 100, 30);
        resetButton.setFocusable(false);
        resetButton.setVisible(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        add(resetButton);

        initializeGame();
    }

    /*
     * Game handling functions
     */
    private void initializeGame() {
        int body_x = ThreadLocalRandom.current().nextInt(1, 47);
        int body_y = ThreadLocalRandom.current().nextInt(1, 47);
        
        head_loc = new Integer[]{body_x, body_y};

        body.add(head_loc);

        placeNewFood();
    }

    private void resetGame() {
        body = new ArrayList<Integer[]>();
        
        snake_alive = true;
        snake_dir = Dir.NONE;

        initializeGame();
        resetButton.setVisible(false);
    }

    private void placeNewFood() {
        int food_x = 0;
        int food_y = 0;

        boolean in_body = true;

        while (in_body) {
            in_body = false;

            food_x = ThreadLocalRandom.current().nextInt(0, 48);
            food_y = ThreadLocalRandom.current().nextInt(0, 48);

            for (Integer[] block : body) {
                if (block[0] == food_x && block[1] == food_y) {
                    in_body = true;
                    break;
                }
            }
        }

        food_loc = new Integer[]{food_x, food_y};

        repaint();
    }
    

    /*
     * Function to move the snake
     * -> At beginning of game can move any direction, otherwise needs to be perpendicular to current direction
     */
    private void turn(int desired_dir) {
        if ((snake_dir == Dir.NONE || body.size() == 1 || (desired_dir % 2) != (snake_dir % 2)) && !has_turned && snake_alive) {
            snake_dir = desired_dir;
            has_turned = true;
        }
    }

    /*
     * Function to update game at each time step
     */
    private Runnable stepGame = new Runnable() {
        public void run() {
            if (snake_alive && snake_dir != Dir.NONE) {
                if (snake_dir == Dir.UP) {head_loc[1] -= 1;}
                else if (snake_dir == Dir.DOWN) {head_loc[1] += 1;}
                else if (snake_dir == Dir.RIGHT) {head_loc[0] += 1;}
                else if (snake_dir == Dir.LEFT) {head_loc[0] -= 1;}

                if (head_loc[0] == food_loc[0] && head_loc[1] == food_loc[1]) {
                    Integer[] tail_loc = body.get(0);
                    for (int i = 0; i < 4; i++) {
                        body.add(0, new Integer[]{tail_loc[0], tail_loc[1]});
                    }
                    body.add(new Integer[]{head_loc[0], head_loc[1]});
                    placeNewFood();
                } else {
                    body.remove(0);
                    for (Integer[] block:body) {
                        if (snake_alive && (block[0] == head_loc[0] && block[1] == head_loc[1])) {
                            handleLoss();
                            break;
                        }
                    }
                    body.add(new Integer[]{head_loc[0], head_loc[1]});
                }

                if (snake_alive && (head_loc[0] > 47 || head_loc[0] < 0 || head_loc[1] > 47 || head_loc[1] < 0)) {
                    handleLoss();
                }

                repaint();
                has_turned = false;
            }
        }
    };

    private void handleLoss() {
        repaint();
        snake_alive = false;
        snake_dir = Dir.NONE;
        resetButton.setVisible(true);
    }

    @Override
    public void resetPage() {
        resetGame();
    }

    /*
     * JPanel overrides
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 550);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRoundRect(0, 50, 500, 500, 10, 10);

        int offset_x = 10;
        int offset_y = 60;

        g.setColor(Color.BLUE);
        g.fillRect(offset_x, offset_y, 480, 480);

        g.setColor(Color.YELLOW);
        for (Integer[] block:body) {
            g.fillRect(offset_x + block[0]*10 + 1, offset_y + block[1]*10 + 1, 8, 8);
        }

        g.setColor(Color.RED);
        g.fillRect(offset_x + food_loc[0]*10 + 1, offset_y + food_loc[1]*10 + 1, 8, 8);

        if (!snake_alive) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(offset_x + head_loc[0]*10 + 1, offset_y + head_loc[1]*10 + 1, 8, 8);
        }
    }
}
