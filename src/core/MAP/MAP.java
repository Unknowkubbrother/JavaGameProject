package core.MAP;

import core.GamePanel;
import core.Texture;

// import core.KeyHandler;
// import java.util.ArrayList;
import java.awt.Graphics;

public class MAP{

    private GamePanel gp;
    // private Texture mapContent[][];
    private int preMapContenet[][] = {
        {1,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,5,2,1,0,0,0,0,0,0,2},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,3,3,3,3,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,5,0,0,0,0,0},
        {1,3,3,3,3,3,3,3,3,3,3,3,1,0,0,0,0,0,0,0,1,3,3,3,3,3,3,3,3,3,3,3,1,0,0,0,0,0,5,2},
    };

    public MAP (GamePanel gp) { 
        this.gp = gp;
    }
    
    public void draw(Graphics g2) {
       for(int i = 0; i < preMapContenet.length;i++){
              for(int j = 0; j < preMapContenet[i].length;j++){
                int worldX = j * gp.titleSize;
                int worldY = i * gp.titleSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.titleSize < gp.player.worldY + gp.player.screenY)
                {
                    Texture texture = Texture.values()[preMapContenet[i][j]];
                    g2.drawImage(texture.getTexture(), screenX, screenY, gp.titleSize, gp.titleSize, null);
                }
              }
       }
    }

    
}
