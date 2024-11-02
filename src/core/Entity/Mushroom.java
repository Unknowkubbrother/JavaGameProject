package core.Entity;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import core.GamePanel;
import java.util.Random;
import java.awt.Graphics;

public class Mushroom extends Entity implements Runnable {

    // Thread
    protected Thread EntityThread;
    protected int ThreadDelay;

    private ArrayList<BufferedImage> idle = new ArrayList<>();
    private ArrayList<BufferedImage> attack = new ArrayList<>();

    int countState = 0;

    public Mushroom(GamePanel gp, int x, int y) {
        super(gp);

        setDefaultValues(x, y);
        loadAnimation();
        startEntityThread();
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
        BufferedImage spriteidle = loadSprite("Montser/Mushroom/Idle.png");
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
            int n = rand.nextInt(5);
            if (n == 0) {
                direction = "idle";
            }
            if (n == 1) {
                direction = "up";
            }
            if (n == 2) {
                direction = "left";
            }
            if (n == 3) {
                direction = "right";
            }
            if (n == 4) {
                direction = "down";
            }
            if (direction != "idle") {
                lastDirection = direction;
            }
            
            actionLockCounter = 0;
        }

        if (direction != "idle") {
            int playerX = gp.player.worldX;
            int playerY = gp.player.worldY;

            int diffX = playerX - worldX;
            int diffY = playerY - worldY;

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            } else {
                if (diffY > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }

            isMoving = true;
        }
    }

    private int countHit = 0;

    public void AttacktoPlayer() {
        countHit++;
        if (countHit > 12) {
            gp.player.setHealth(gp.player.getHealth() - 12);
            countHit = 0;
            gp.player.worldX -= 10;
            direction = "idle";
        }
    }

    @Override
    public void update() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState) {
            return;
        }

        setAction();

        collisionOn = false;
        gp.cChecker.checkMap(this);

        gp.cChecker.checkObject(this, false);

        if (gp.cChecker.checkPlayer(this)) {
            direction = "attack";
            isMoving = false;
            AttacktoPlayer();
        }

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
        } else {
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
        int offsetX = 0;
        int offsetWidth = 0;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {

            if (direction.equals("idle")) {
                image = idle.get(spriteNum);
            } else if (direction.equals("attack")) {
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

            if (lastDirection != null && lastDirection == "left" && direction != "right" && direction != "left") {
                offsetX = getImageWidth();
                offsetWidth = (getImageWidth()*2)*-1;
            }

            g2.drawImage(image, screenX + offsetX, screenY, getImageWidth() + offsetWidth, getImageHeight(), null);
            // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
            // solidArea.height);
        }
    }

    public void stopEntityThread() {
        EntityThread = null;
    }

    public void startEntityThread() {
        ThreadDelay = 16;
        EntityThread = new Thread(this);
        EntityThread.start();
    }

    @Override
    public void run() {
        while (EntityThread != null) {
            update();
            try {
                Thread.sleep(ThreadDelay); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
