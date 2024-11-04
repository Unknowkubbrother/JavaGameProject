package core.Entity;

import java.awt.image.BufferedImage;
import core.GamePanel;
import java.awt.Graphics;

public class BringerOfDeath extends Monster {

    public BringerOfDeath(GamePanel gp, int x, int y) {
        super(gp);

        setDefaultValues(x, y);
        loadAnimation();
        startMonsterThread();
    }

    public void setDefaultValues(int x, int y) {
        direction = "idle";
        speed = 1;
        worldX = x;
        worldY = y;
        setImageHeight(93 * 2);
        setImageWidth(140 * 2);
        solidArea.width = 140;
        solidArea.height = 93;
        solidArea.x = (getImageWidth() - (solidArea.width * 2));
        solidArea.y = (getImageHeight()) / 2;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        attackDamage = 50;
        rebound = 10;
        element = 1;
        DestinationActionLockCounter = 120;
        health = 200;
        maxHealth = 200;
    }

    @Override
    protected void loadAnimation() {
        BufferedImage spriteAll = loadSprite("Montser/BringerofDeath/All.png");
        // BufferedImage spriteLeft = loadSprite("Montser/BringerofDeath/left.png");
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (i == 0) {
                    idle.add(spriteAll.getSubimage(j * 140, i * 93, 140, 93));
                } else if (i == 1) {
                    right.add(spriteAll.getSubimage(j * 140, i * 93, 140, 93));
                    left.add(spriteAll.getSubimage(j * 140, i * 93, 140, 93));
                } else if (i == 2) {
                    attack.add(spriteAll.getSubimage(j * 140, i * 93, 140, 93));
                } else if (i == 6) {
                    hit.add(spriteAll.getSubimage(j * 140, i * 93, 140, 93));
                }

            }
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
            } else if (direction.equals("hit")) {
                image = hit.get(spriteNum);
            }

            if (lastDirection != null && lastDirection == "left" && direction != "right") {
                offsetX = getImageWidth()/2;
                offsetWidth = (getImageWidth() * 2) * -1;
            }

            g2.drawImage(image, screenX + offsetX, screenY, getImageWidth() + offsetWidth, getImageHeight(), null);
            // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
            //         solidArea.height);
        }
    }
}
