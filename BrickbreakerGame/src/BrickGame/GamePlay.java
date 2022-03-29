package BrickGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX =120;
    private int ballposY = 350;
    private int ballXdir = -5;
    private int ballYdir = -7;

    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        //Drawing the background
        g.setColor(Color.white);
        g.fillRect(1,1,692,592);

        //Drawing the borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //Drawing Paddel
        g.setColor(Color.blue);
        g.fillRect(playerX,550,100,8);

        //Drawing the ball
        g.setColor(Color.green);
        g.fillOval(ballposX,ballposY,20,20);

        //releases the services
        g.dispose();

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            //Checking if ball intersects with paddel
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;

            }


            ballposX += ballXdir;
            ballposY += ballYdir;

            //checking the boundary of X on the left wall and changing directions
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            //checking th boundary of y on the ceiling of game
            if(ballposY < 0){
                ballYdir =-ballYdir;
            }
            //checking the boundary of X on the right wall and changing directions
            if(ballposX > 670){
                ballXdir = -ballXdir;
            }

        }


        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else {
                moveRight();

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10){
                playerX = 10;
            }else {
                moveLeft();
            }
        }

    }
    public void  moveRight(){
        play = true;
        playerX += 30;
    }
    public void  moveLeft(){
        play = true;
        playerX -= 30;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
