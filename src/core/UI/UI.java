package core.UI;

import core.GamePanel;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import core.MAP.MAP1.*;
import core.MAP.MAP2.*;

public class UI  implements MouseListener{
    Graphics2D g2;
    Image bg = new ImageIcon(getClass().getResource("/resources/bg.png")).getImage();
    Image bgMenu = new ImageIcon(getClass().getResource("/resources/BgMenuStart.png")).getImage();
    Image buttonStart = new ImageIcon(getClass().getResource("/resources/ButtonStart.png")).getImage();
    Image buttonExit = new ImageIcon(getClass().getResource("/resources/ButtonExit.png")).getImage();
    public int commandNum = 0;

    GamePanel gp;

    public UI(GamePanel gp) {
        this.gp = gp;
        gp.addMouseListener(this);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.menuState) {
            menuStartGame();
        }

        if (gp.gameState == gp.pauseState) {
            pauseMenu();
        }

        
        if (gp.gameState == gp.gameWinState) {
            System.out.println("gameWin");
            gameWin();
        }

        if (gp.gameState == gp.gameOverState) {
            gameOver();
        }


        if (gp.gameState == gp.selectMapState) {
            selectMap();
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

    public void gameOver(){
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        String text = "YOU LOSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void gameWin(){
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        String text = "YOU WINNER";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void menuStartGame() {
        g2.drawImage(bgMenu, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(buttonStart, gp.screenWidth / 2 - 100, gp.screenHeight - gp.titleSize*3, gp.titleSize*3, gp.titleSize, null);
        g2.drawImage(buttonExit, gp.screenWidth / 2 - 100, gp.screenHeight - gp.titleSize*2, gp.titleSize*3, gp.titleSize, null);
    }

    public void selectMap(){
        g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(gp.titleSize*3, gp.titleSize*3 , 100, 100, 10, 10);
        g2.setColor(Color.BLACK);
        String text = "MAP 1";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textX = gp.titleSize*3 + (100 - textWidth) / 2;
        int textY = gp.titleSize*3 + (100 + g2.getFontMetrics().getAscent()) / 2;
        g2.drawString(text, textX, textY);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(gp.titleSize*4 + 100, gp.titleSize*3 , 100, 100, 10, 10);
        g2.setColor(Color.BLACK);
        text = "MAP 2";
        textWidth = g2.getFontMetrics().stringWidth(text);
        textX = gp.titleSize*4 + 100 + (100 - textWidth) / 2;
        textY = gp.titleSize*3 + (100 + g2.getFontMetrics().getAscent()) / 2;
        g2.drawString(text, textX, textY);
    }


    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x =  gp.screenWidth / 2 - length / 2;
        return x;
    }


     // MouseListener
     @Override
     public void mouseClicked(MouseEvent e) {
         if (gp.gameState == gp.menuState) {
                if (e.getX() > gp.screenWidth / 2 - 100 && e.getX() < gp.screenWidth / 2 - 100 + gp.titleSize*3) {
                    if (e.getY() > gp.screenHeight - gp.titleSize*3 && e.getY() < gp.screenHeight - gp.titleSize*2) {
                        gp.gameState = gp.selectMapState;
                    } else if (e.getY() > gp.screenHeight - gp.titleSize*2 && e.getY() < gp.screenHeight - gp.titleSize) {
                        System.exit(0);
                    }
                }
         }else if (gp.gameState == gp.selectMapState) {
             if (e.getX() > gp.titleSize*3 && e.getX() < gp.titleSize*3 + 100) {
                 if (e.getY() > gp.titleSize*3 && e.getY() < gp.titleSize*3 + 100) {
                     gp.player.setMap(0, 0);
                     gp.map = new M1_ST1(gp);
                     gp.gameState = gp.playerState;
                 }
             }else if (e.getX() > gp.titleSize*4 + 100 && e.getX() < gp.titleSize*4 + 200) {
                 if (e.getY() > gp.titleSize*3 && e.getY() < gp.titleSize*3 + 100) {
                    gp.player.setMap(1, 0);
                    gp.map = new M2_ST1(gp);
                    gp.gameState = gp.playerState;
                 }
             }
         }
 
     }
 
     @Override
     public void mousePressed(MouseEvent e) {
 
     }
 
     @Override
     public void mouseReleased(MouseEvent e) {
 
     }
 
     @Override
     public void mouseEntered(MouseEvent e) {
 
     }
 
     @Override
     public void mouseExited(MouseEvent e) {
     }
}