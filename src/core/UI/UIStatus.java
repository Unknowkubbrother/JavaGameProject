package core.UI;

import core.GamePanel;
import java.awt.Graphics2D;
public class UIStatus {
    GamePanel gp;
    Graphics2D g2;

    public UIStatus(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        drawElement();
    }

    public void drawElement(){
        g2.drawImage(gp.player.getImageCurrentElement(), 25, gp.screenHeight - 175, gp.titleSize*3, gp.titleSize*3, null);
    }
}
