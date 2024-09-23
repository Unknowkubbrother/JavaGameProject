package core.Entity;

import core.GamePanel;
import core.KeyHandler;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Entity {
    //Default Player
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    //movement
    private ArrayList<BufferedImage> up = new ArrayList<>();
    private ArrayList<BufferedImage> down = new ArrayList<>();
    private ArrayList<BufferedImage> left = new ArrayList<>();
    private ArrayList<BufferedImage> right = new ArrayList<>();
    private boolean isMoving = false;
    public HashMap<String, Integer> player_state = new HashMap<String, Integer>() {{
        put("map", 0);
    }};
    //

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);

        solidArea = new Rectangle();
        // solidArea.x = 32;
        // solidArea.y = 64;
        // solidArea.width = 64;
        // solidArea.height = 64;
        solidArea.x = 45;
        solidArea.y = 77;
        solidArea.width = 51;
        solidArea.height = 51;

        setDefaultValues();
        loadAnimation();
    }

    public void checkPlayerState(){
        System.out.println(getEntityCoords());
        if (getEntityCoords().get("x") >= gp.titleSize * 14 
            && getEntityCoords().get("x") <= gp.titleSize * 15
            && getEntityCoords().get("y") >= gp.titleSize * 5
            && getEntityCoords().get("y") <= gp.titleSize * 6
            && player_state.get("map") == 0){
            player_state.put("map", 1);
            worldX = gp.titleSize * 1;
            worldY = gp.titleSize * 5;
        }
    }

    private void setDefaultValues() {
        worldX = gp.titleSize * 1;
        worldY = gp.titleSize * 1;
        speed = 4;
        direction = "up";
    }

    private BufferedImage loadSprite(String path) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getResource("/resources/texture/player/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sprite;
    }

    private void loadAnimation() {
        // Up
        for (int i = 1; i <= 1; i++) {
            up.add(loadSprite(String.format("/up/up_%d.png", i)));
        }

        // Down
        for (int i = 1; i <= 1; i++) {
            down.add(loadSprite(String.format("/up/up_%d.png", i)));
        }

        // Right
        for (int i = 1; i <= 4; i++) {
            right.add(loadSprite(String.format("/right/right_%d.png", i)));
        }

        // Left
        for (int i = 1; i <= 4; i++) {
            left.add(loadSprite(String.format("/left/left_%d.png", i)));
        }
    }

    public void update() {
        collisionOn = false;
        gp.cChecker.checkMap(this);
        if (keyH.up) {
            direction = "up";
            if (!collisionOn) {
                worldY -= speed;
                isMoving = true;
            }
        }
        if (keyH.down) {
            direction = "down";
            if (!collisionOn) {
                worldY += speed;
                isMoving = true;
            }
        }
        if (keyH.left) {
            direction = "left";
            if (!collisionOn) {
                worldX -= speed;
                isMoving = true;
            }
        }
        if (keyH.right) {
            direction = "right";
            if (!collisionOn) {
                worldX += speed;
                isMoving = true;
            }
            
        }
        if (!keyH.up && !keyH.down && !keyH.left && !keyH.right) {
            isMoving = false;
        }
    }

    private int aniTickRight_Left = 0;
    private int aniSpeedRight_Left = 10;
    private int aniIdxRight_Left = 1;

    private void updateAnimationRight_Left() {
        aniTickRight_Left++;
        if (aniTickRight_Left >= aniSpeedRight_Left) {
            aniTickRight_Left = 0;
            aniIdxRight_Left++;
            switch (direction) {
                case "left":
                    if (aniIdxRight_Left >= left.size()) {
                        aniIdxRight_Left = 1;
                    }
                    break;
                case "right":
                    if (aniIdxRight_Left >= right.size()) {
                        aniIdxRight_Left = 1;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private int aniTickUp_Down = 0;
    private int aniSpeedUp_Down = 10;
    private int aniIdxUp_Down = 0;

    private void updateAnimationUp_Down() {
        aniTickUp_Down++;
        if (aniTickUp_Down >= aniSpeedUp_Down) {
            aniTickUp_Down = 0;
            aniIdxUp_Down++;
            switch (direction) {
                case "up":
                    if (aniIdxUp_Down >= up.size()) {
                        aniIdxUp_Down = 0;
                    }
                    break;
                case "down":
                    if (aniIdxUp_Down >= down.size()) {
                        aniIdxUp_Down = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    }


    public void draw(Graphics g2) {

        BufferedImage image = null;

        if (!isMoving) {
            switch (direction) {
                case "up":
                    image = up.get(0);
                    break;
                case "down":
                    image = down.get(0);
                    break;
                case "left":
                    image = left.get(0);
                    break;
                case "right":
                    image = right.get(0);
                    break;
                default:
                    break;
            }
        }else{
            switch (direction) {
                case "up":
                    image = up.get(aniIdxUp_Down);
                    updateAnimationUp_Down();
                    break;
                case "down":
                    image = up.get(aniIdxUp_Down);
                    updateAnimationUp_Down();
                    break;
                case "left":
                    image = left.get(aniIdxRight_Left);
                    updateAnimationRight_Left();
                    break;
                case "right":
                    image = right.get(aniIdxRight_Left);
                    updateAnimationRight_Left();
                    break;
                default:
                    break;
            }
        }


        g2.drawImage(image, screenX, screenY, gp.titleSize * 2, gp.titleSize * 2, null);

    }

}
