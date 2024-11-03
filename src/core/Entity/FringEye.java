package core.Entity;

import java.awt.image.BufferedImage;
import core.GamePanel;
import java.awt.Graphics;

public class FringEye extends Monster{

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
