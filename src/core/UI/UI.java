package core.UI;

import core.GamePanel;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class UI {
    Graphics2D g2;
    Image bgMenu = new ImageIcon(getClass().getResource("/resources/BgMenuStart.png")).getImage();
    JPanel uiPanel = new JPanel();
    public int commandNum = 0;

    GamePanel gp;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.menuState) {
            menuStartGame();
        }
        if (gp.gameState == gp.pauseState) {
            pauseMenu();
        }
    }

    public void pauseMenu() {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void menuStartGame() {
        g2.drawImage(bgMenu, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(new Font("Arial", Font.BOLD, 30));
        String text = "New Game";
        int x = getXforCenteredText(text);
        int y = gp.titleSize * 9;
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 30, y);
        }

        text = "Settings";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 30, y);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 30, y);
        }
    }


    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x =  gp.screenWidth / 2 - length / 2;
        return x;
    }
}