package core;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTitleSize = 16; // 16 x 16 pixels
    final int scale = 3; // 3x scale

    final int titleSize = originalTitleSize * scale; // 48 x 48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol; // 768 pixels
    final int screenHeight = titleSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set Player's initial position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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
    // public void run(){

    //     double drawInterval = 1000000000 / FPS; // 0.0166666666666667 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     while (gameThread != null) {


    //         // UPDATE
    //         update();

    //         // DRAW
    //         repaint();
            

    //         try{
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime / 1000000;

    //             if (remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long)remainingTime);

    //             nextDrawTime += drawInterval;
    //         }catch(Exception e){
    //             e.printStackTrace();
    //         }
    //     }
    // }

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

        if (keyH.up) {
            playerY -= playerSpeed;
        }
        else if (keyH.down) {
            playerY += playerSpeed;
        }
        else if (keyH.left) {
            playerX -= playerSpeed;
        }
        else if (keyH.right) {
            playerX += playerSpeed;
        }
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, titleSize, titleSize);

        g2.dispose();
    }
    
}
