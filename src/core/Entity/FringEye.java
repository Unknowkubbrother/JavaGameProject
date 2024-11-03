package core.Entity;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import core.GamePanel;
import java.util.Random;
import java.awt.Graphics;

public class FringEye extends Monster{

    private ArrayList<BufferedImage> idle = new ArrayList<>();
    private ArrayList<BufferedImage> attack = new ArrayList<>();
    private ArrayList<BufferedImage> hit = new ArrayList<>();


    public FringEye(GamePanel gp, int x, int y) {
        super(gp);
        
        setDefaultValues(x, y);
        loadAnimation();
        startMonsterThread();
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
        attackDamage = 10;
        rebound = 10;
        element = 3;
    }

    @Override
    protected void loadAnimation() {
        BufferedImage spriteFlight = loadSprite("Montser/Flyingeye/Flight.png");
        BufferedImage spriteLeft = loadSprite("Montser/Flyingeye/left.png");
        BufferedImage spriteAttack = loadSprite("Montser/Flyingeye/Attack.png");
        BufferedImage spriteHit = loadSprite("Montser/Flyingeye/Hit.png");

        for (int i = 0; i < 8; i++) {
            idle.add(spriteFlight.getSubimage(i * 150, 0, 150, 150));
        }


        for (int i = 0; i < 8; i++) {
            right.add(spriteFlight.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 8; i++) {
            left.add(spriteLeft.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 8; i++) {
            attack.add(spriteAttack.getSubimage(i * 150, 0, 150, 150));
        }

        for (int i = 0; i < 4; i++) {
            hit.add(spriteHit.getSubimage(i * 150, 0, 150, 150));
        }

    }

    @Override
    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter == 60) {
            Random rand = new Random();
            int n = rand.nextInt(2);
            if (n == 0) {
                direction = "idle";
            }
            if (n == 1) {
                direction = "walk";
            }

            actionLockCounter = 0;
        }

        if (direction.equals("walk")) {
            int playerX = gp.player.worldX;
            int playerY = gp.player.worldY;
        
            int diffX = playerX - worldX;
            int diffY = playerY - worldY;
        
            double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        
            double directionX = diffX / distance;
            double directionY = diffY / distance;
        
            worldX += directionX * speed;
            worldY += directionY * speed;
        
            if (Math.abs(directionX) > Math.abs(directionY)) {
                if (directionX > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            } else {
                if (directionY > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
        
            lastDirection = direction;
            isMoving = true;
        }
        
    }

    private int countHit = 0;

    @Override
    public void AttacktoPlayer() {
        countHit++;
        if (countHit > 30) {
            gp.player.playerAttacked(attackDamage);
            countHit = 0;
            if (worldX > gp.player.worldX) {
                gp.player.worldX -= rebound;
            } else {
                gp.player.worldX += rebound;
            }
            if (worldY > gp.player.worldY) {
                gp.player.worldY -= rebound;
            } else {
                gp.player.worldY += rebound;
            }
            direction = "idle";
        }
    }

    @Override
    public void update() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState || gp.gameState == gp.gameOverState) {
            return;
        }

        setAction();

        collisionOn = false;
        gp.cChecker.checkMap(this);

        gp.cChecker.checkObject(this, false);

        if (gp.cChecker.checkPlayer(this) && direction != "hit") {
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
            case "hit":
                if (spriteNum >= hit.size()) {
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
            }else if (direction.equals("hit")) {
                image = hit.get(spriteNum);
            }

            if (lastDirection != null && lastDirection == "left" && direction != "right" && direction != "left") {
                offsetX = getImageWidth();
                offsetWidth = (getImageWidth() * 2) * -1;
            }

            g2.drawImage(image, screenX + offsetX, screenY, getImageWidth() + offsetWidth, getImageHeight(), null);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
            solidArea.height);
        }
    }
}
