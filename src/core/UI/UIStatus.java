package core.UI;

import core.GamePanel;
import core.Entity.BringerOfDeath;
import core.Entity.Entity;
import core.Entity.Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;

public class UIStatus {
    GamePanel gp;
    Graphics2D g2;
    private BufferedImage mana;
    private int cooldownAlert = 0;
    private String alertText = "";

    public UIStatus(GamePanel gp) {
        this.gp = gp;
        mana = Entity.loadSprite("player/mana.png");
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawElement();
        drawBoxArmor();
        drawBoxHealth();
        drawCurrentPlayTime();
        drawMana();
        drawBoxHealthMonster();
        drawCoords();

        if (cooldownAlert > 0) {
            drawAlert();
            cooldownAlert--;
        }
    }

    public void setAlert(String text, int cooldown) {
        alertText = text;
        cooldownAlert = cooldown / 16;
    }

    public void drawElement() {
        g2.drawImage(gp.player.getImageCurrentElement(), 25, gp.screenHeight - 175, gp.titleSize * 3, gp.titleSize * 3,
                null);
    }

    public void drawCoords(){
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.setColor(Color.WHITE);
        g2.drawString("X: " + gp.player.getEntityCoords().get("x") + " Y: " + gp.player.getEntityCoords().get("y"), gp.screenWidth/2, 40);
    }

    public void drawBoxHealthMonster() {
        for (int i = 0; i < gp.monster.size(); i++) {
            Monster monster = (Monster) gp.monster.get(i);
            int x = monster.worldX - gp.player.worldX + gp.player.screenX + gp.titleSize * 2;
            int y = monster.worldY - gp.player.worldY + gp.player.screenY + gp.titleSize + gp.titleSize / 2;
            if (monster instanceof BringerOfDeath) {
                x = monster.worldX - gp.player.worldX + gp.player.screenX + gp.titleSize / 2;
                y = monster.worldY - gp.player.worldY + gp.player.screenY;
            }
            int healthMaxWidth = (int) ((monster.getMaxHealth() / 100.0) * 50);
            g2.setColor(Color.BLACK);
            g2.fillRoundRect(x, y, healthMaxWidth, 5, 2, 2);
            g2.setColor(Color.RED);
            int healthWidth = (int) ((monster.getHealth() / 100.0) * 50);
            g2.fillRoundRect(x, y, healthWidth, 5, 2, 2);
        }
    }

    public void drawBoxHealth() {
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(25, 60, 200, 25, 10, 10);
        g2.setColor(Color.RED);
        int healthWidth = (int) ((gp.player.getHealth() / 100.0) * 200);
        g2.fillRoundRect(25, 60, healthWidth, 25, 10, 10);
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(25, 60, 200, 25, 10, 10);
        g2.drawString("HP", 25 + 5, 60 + (25 / 2) + 5);
        String healthText = gp.player.getHealth() + "/100";
        int healthTextWidth = g2.getFontMetrics().stringWidth(healthText);
        g2.drawString(healthText, 25 + (200 - healthTextWidth) / 2, 60 + (25 / 2) + 5);
    }

    public void drawBoxArmor() {
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(25, 30, 200, 25, 10, 10);
        g2.setColor(Color.GRAY);
        int armorWidth = (int) ((gp.player.getArmor() / 100.0) * 200);
        g2.fillRoundRect(25, 30, armorWidth, 25, 10, 10);
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(25, 30, 200, 25, 10, 10);
        g2.drawString("AP", 25 + 5, 30 + (25 / 2) + 5);
        String armorText = gp.player.getArmor() + "/100";
        int armorTextWidth = g2.getFontMetrics().stringWidth(armorText);
        g2.drawString(armorText, 25 + (200 - armorTextWidth) / 2, 30 + (25 / 2) + 5);
    }

    public void drawCurrentPlayTime() {
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.setColor(Color.WHITE);
        g2.drawString("TimeLeft: " + gp.currentGameTime, gp.screenWidth - 100, 40);
    }

    public void drawMana() {
        g2.drawImage(mana, 235, 30, 25, 25, null);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.WHITE);
        String manaText = "" + gp.player.getMana();
        int manaTextWidth = g2.getFontMetrics().stringWidth(manaText);
        g2.drawString(manaText, 235 + (25 - manaTextWidth) / 2, 30 + 25 + 15);
    }

    public void drawAlert() {
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.WHITE);
        int x = gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(alertText) / 2;
        int y = gp.screenHeight / 2;
        g2.drawString(alertText, x, y);
    }

}
