package core.MAP;

import core.GamePanel;
import core.Texture;
import java.awt.Graphics;

public abstract class Supermap{

    private GamePanel gp;
    public int MapContenet[][];

    public Supermap (GamePanel gp) { 
        this.gp = gp;
        setSpawnMonster(gp);
    }
    
    public void draw(Graphics g2) {
       for(int i = 0; i < MapContenet.length;i++){
              for(int j = 0; j < MapContenet[i].length;j++){
                int worldX = j * gp.titleSize;
                int worldY = i * gp.titleSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.titleSize < gp.player.worldY + gp.player.screenY)
                {
                    Texture texture = Texture.values()[MapContenet[i][j]];
                    g2.drawImage(texture.getTexture(), screenX, screenY, gp.titleSize, gp.titleSize, null);
                }
              }
       }
    }

    abstract public void setSpawnMonster(GamePanel gp);

    
}
