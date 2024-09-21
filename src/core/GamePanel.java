package core;
import javax.swing.JPanel;

import core.Entity.Player;
import core.MAP.MAP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int originalTitleSize = 16; // 16 x 16 pixels
    public final int scale = 4; // 3x scale

    public final int titleSize = originalTitleSize * scale ; // 64 x 64 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol; // 1024 pixels
    public final int screenHeight = titleSize * maxScreenRow; // 768 pixels


    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    MAP map = new MAP(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override

    public void run(){

        double drawInterval = 1000000000 / FPS; // 0.0166666666666667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                // UPDATE
                update();

                // DRAW
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
    }

    public void update() {

        player.update();
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;

        map.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
    
}
