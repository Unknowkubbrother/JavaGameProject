package core.Entity;

import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import core.GamePanel;
import java.awt.Graphics;

public class Mushroom extends Entity implements Runnable {

    private ArrayList<BufferedImage> idle = new ArrayList<>();
    private ArrayList<BufferedImage> attack = new ArrayList<>();
    private ArrayList<BufferedImage> right = new ArrayList<>();
    private ArrayList<BufferedImage> left = new ArrayList<>();

    int countState = 0;
    private Thread gameThread;

    public Mushroom(GamePanel gp, int x, int y) {
        super(gp);

        setDefaultValues(x, y);
        loadAnimation();
        startGameThread();
    }

    public void setDefaultValues(int x, int y) {
        direction = "idle";
        speed = 1;
        worldX = gp.titleSize * x;
        worldY = gp.titleSize * y;
        setImageHeight(150 * 2);
        setImageWidth(150 * 2);
        this.solidAreaDefaultX = (getImageWidth() - this.solidArea.width) / 2;
        this.solidAreaDefaultY = (getImageHeight() - this.solidArea.height + 30) / 2;
    }

    @Override
    protected void loadAnimation() {
        BufferedImage spriteidle = loadSprite("Montser/Mushroom/idle.png");
        BufferedImage spriteRight = loadSprite("Montser/Mushroom/right.png");
        BufferedImage spriteLeft = loadSprite("Montser/Mushroom/left.png");
        BufferedImage spriteAttack = loadSprite("Montser/Mushroom/Attack.png");

        for (int i = 0; i < 4; i++) {
            idle.add(spriteidle.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 8; i++) {
            right.add(spriteRight.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 8; i++) {
            left.add(spriteLeft.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 8; i++) {
            attack.add(spriteAttack.getSubimage(i * 150, 0, 150, 150));
        }

    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random rand = new Random();
            int n = rand.nextInt(6);
            if (n == 0) {
                direction = "idle";
                isMoving = false;
            }
            if (n == 1) {
                direction = "up";
                isMoving = true;
            }
            if (n == 2) {
                direction = "left";
                isMoving = true;
            }
            if (n == 3) {
                direction = "right";
                isMoving = true;
            }
            if (n == 4) {
                direction = "down";
                isMoving = true;
            }
            if (n == 5){
                direction = "attack";
                isMoving = false;
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkMap(this);

        gp.cChecker.checkObject(this, false);

        gp.cChecker.checkPlayer(this);

        if (!collisionOn && isMoving) {
            if (direction == "up") {
                worldY -= speed;
            }
            if (direction == "down") {
                worldY += speed;
            }
            if (direction == "left") {
                worldX -= speed;
            }
            if (direction == "right") {
                worldX += speed;
            }
        }else{
            isMoving = false;
        }

        spriteCounter++;
        if (spriteCounter > 8) {
            spriteCounter = 0;
            spriteNum++;
        }

        switch (direction) {
            case "idle":
                if (spriteNum >= idle.size()) {
                    spriteNum = 0;
                }
                break;
            case "attack":
                if (spriteNum >= attack.size()) {
                    spriteNum = 0;
                }
                break;
            case "right":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            case "left":
                if (spriteNum >= left.size()) {
                    spriteNum = 0;
                }
                break;
            case "up":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            case "down":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {

            if (direction.equals("idle")) {
                image = idle.get(spriteNum);
            }
            else if (direction.equals("attack")) {
                    image = attack.get(spriteNum);
            } else if (direction.equals("right")) {
                image = right.get(spriteNum);
            } else if (direction.equals("left")) {
                image = left.get(spriteNum);
            } else if (direction.equals("up")) {
                image = right.get(spriteNum);
            } else if (direction.equals("down")) {
                image = right.get(spriteNum);
            }

            g2.drawImage(image, screenX, screenY, getImageWidth(), getImageHeight(), null);
            // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            try {
                Thread.sleep(16); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
