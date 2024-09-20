package core.Entity;

import core.GamePanel;
import core.KeyHandler;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    //Default Player
    GamePanel gp;
    KeyHandler keyH;

    //movement
    private ArrayList<BufferedImage> up = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> down = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> left = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> right = new ArrayList<BufferedImage>();
    private boolean isMoving = false;
    //

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        // getPlayerImage();
        loadAnimation();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
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
        for (int i = 1; i <= 3; i++) {
            right.add(loadSprite(String.format("/right/right_%d.png", i)));
        }

        // Left
        for (int i = 1; i <= 3; i++) {
            left.add(loadSprite(String.format("/left/left_%d.png", i)));
        }
    }

    public void update() {
        if (keyH.up) {
            direction = "up";
            y -= speed;
            isMoving = true;
        }
        if (keyH.down) {
            direction = "down";
            y += speed;
            isMoving = true;
        }
        if (keyH.left) {
            direction = "left";
            x -= speed;
            isMoving = true;
        }
        if (keyH.right) {
            direction = "right";
            x += speed;
            isMoving = true;
        }
        if (!keyH.up && !keyH.down && !keyH.left && !keyH.right) {
            isMoving = false;
        }
    }

    private int aniTickRight_Left = 0;
    private int aniSpeedRight_Left = 10;
    private int aniIdxRight_Left = 0;

    private void updateAnimationRight_Left() {
        aniTickRight_Left++;
        if (aniTickRight_Left >= aniSpeedRight_Left) {
            aniTickRight_Left = 0;
            aniIdxRight_Left++;
            switch (direction) {
                case "left":
                    if (aniIdxRight_Left >= left.size()) {
                        aniIdxRight_Left = 0;
                    }
                    break;
                case "right":
                    if (aniIdxRight_Left >= right.size()) {
                        aniIdxRight_Left = 0;
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
            // switch (direction) {
            //     case "up":
            //         image = up.get(0);
            //         break;
            //     case "down":
            //         image = down.get(0);
            //         break;
            //     case "left":
            //         image = left.get(0);
            //         break;
            //     case "right":
            //         image = right.get(0);
            //         break;
            //     default:
            //         break;
            // }
            image = up.get(0);
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


        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
        System.out.println(getEntityCoords());

    }

}
