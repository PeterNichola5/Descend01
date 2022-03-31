package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;



public class GameMotor extends JPanel implements Runnable {
    
    Thread gameThread;
    Input input = new Input(this);
    Player player = new Player(input, this);
    TileManager tileM = new TileManager(this);
    CollisionEngine collisionEngine = new CollisionEngine(this);

    int room = 0;
    double delta;

    public CollisionEngine getCollisionEngine() {
        return collisionEngine;
    }

    public double getDelta() {
        return delta;
    }
    



    public GameMotor() {
        //window settings
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //Forces program to only update at 60 FPS
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / Constants.DRAW_INTERVAL;
            lastTime = currentTime;
            if(delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        g2.setColor(Color.white);
        player.draw(g2);
        g2.dispose();
        //System.out.println(input.getLeftTapped());
        System.out.println("hbx = "+collisionEngine.hitBoxLeftCol+" hby = "+collisionEngine.hitBoxBottomRow+" x = "+player.getEntityX()+" y = "+player.getEntityY());

    }
}
