package core;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.Entity.Entity;
import core.Entity.Player;
import core.MAP.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


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
    int FrameRate = 0;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // COLLISION
    public CollisionChecker cChecker = new CollisionChecker(this);

    // ENTITIES
    public Player player = new Player(this, keyH);
    public ArrayList<Entity> npc = new ArrayList<Entity>();

    // MAP
    Supermap map = new LOBBY(this);

    // OBJECTS
    public ArrayList<Objects> objects = new ArrayList<>();
    AssetSetterObject aSetterObject = new AssetSetterObject(this);

    // BACKGROUND
    BufferedImage bgGame;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(16, 16, 16));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setBackgroundGame();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setBackgroundGame(){
        try {
            bgGame = ImageIO.read(getClass().getResourceAsStream("/resources/bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                // System.out.println("FPS: " + drawCount);
                FrameRate = drawCount;
                drawCount = 0;
                timer = 0;
            }
            
        }
    }

    public void setupGame() {
        aSetterObject.setObjects();
    }

    public void update() {
        player.update();
        player.checkPlayerState();
    }

    public void DrawFPS(Graphics g2){
        g2.setColor(Color.WHITE);
        g2.drawString("FPS: " + FrameRate, 20, 20);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;

        // backgroundGame
        g2.drawImage(bgGame, 0, 0, screenWidth, screenHeight, null);

        // Draw map
        if (player.getStateMap() == 1 && !(map instanceof STAGE_1)) {
            System.out.println("Change map to STAGE_1");
            map = new STAGE_1(this);
        }else if (player.getStateMap() == 0 && !(map instanceof LOBBY)){
            System.out.println("Change map to LOBBY");
            map = new LOBBY(this);
        }
        
        map.draw(g2);

        // Draw objects
        aSetterObject.draw(g2);

        // Draw player
        player.draw(g2);

        // Draw monster
        for(int i = 0; i < npc.size(); i++){
            npc.get(i).draw(g2);
        }

        // Draw FPS
        DrawFPS(g2);

        g2.dispose();
    }
    
}
