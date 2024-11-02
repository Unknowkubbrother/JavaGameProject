package core.UI;

import core.GamePanel;
import java.awt.Graphics2D;
import java.awt.Font;
public class UIStatus {
    GamePanel gp;
    Graphics2D g2;

    public UIStatus(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawElement();
        drawBoxArmor();
        drawBoxHealth();
        drawCurrentPlayTime();
    }

    public void drawElement(){
        g2.drawImage(gp.player.getImageCurrentElement(), 25, gp.screenHeight - 175, gp.titleSize*3, gp.titleSize*3, null);
    }

    public void drawBoxHealth(){
        g2.setFont(new Font("Arial", java.awt.Font.BOLD, 12));
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRoundRect(25, 60, 200, 25, 10, 10);
        g2.setColor(java.awt.Color.RED);
        int healthWidth = (int) ((gp.player.getHealth() / 100.0) * 200);
        g2.fillRoundRect(25, 60, healthWidth, 25, 10, 10);
        g2.setColor(java.awt.Color.WHITE);
        g2.drawRoundRect(25, 60, 200, 25, 10, 10);
        g2.drawString("HP", 25+5, 60+(25/2)+5);
        String healthText = gp.player.getHealth() + "/100";
        int healthTextWidth = g2.getFontMetrics().stringWidth(healthText);
        g2.drawString(healthText, 25 + (200 - healthTextWidth) / 2, 60 + (25 / 2) + 5);
    }

    public void drawBoxArmor(){
        g2.setFont(new Font("Arial", java.awt.Font.BOLD, 12));
        g2.setColor(java.awt.Color.BLACK);
        g2.fillRoundRect(25, 30, 200, 25, 10, 10);
        g2.setColor(java.awt.Color.BLUE);
        int armorWidth = (int) ((gp.player.getArmor() / 100.0) * 200);
        g2.fillRoundRect(25, 30, armorWidth, 25, 10, 10);
        g2.setColor(java.awt.Color.WHITE);
        g2.drawRoundRect(25, 30, 200, 25, 10, 10);
        g2.drawString("AP", 25+5, 30+(25/2)+5);
        String armorText = gp.player.getArmor() + "/100";
        int armorTextWidth = g2.getFontMetrics().stringWidth(armorText);
        g2.drawString(armorText, 25 + (200 - armorTextWidth) / 2, 30 + (25 / 2) + 5);
    }

    public void drawCurrentPlayTime(){
        g2.setFont(new Font("Arial", java.awt.Font.BOLD, 15));
        g2.setColor(java.awt.Color.WHITE);
        g2.drawString("TimeLeft: " + gp.currentGameTime, gp.screenWidth - 100, 40);
    }
}
