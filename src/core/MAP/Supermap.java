package core.MAP;

import core.GamePanel;
import core.Texture;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import core.Entity.Mushroom;

public abstract class Supermap implements ActionListener{

    protected GamePanel gp;
    public int MapContenet[][];
    public Timer timerMap;
    public int currentTimeMap = 0;

    public Supermap (GamePanel gp) { 
        this.gp = gp;
        timerMap = new Timer(1000, this);
        timerMap.start();
        setDefaultSpawnMonster();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        currentTimeMap++;
        System.out.println(currentTimeMap);
        update();
    }


    abstract public void update();

    public void setDefaultSpawnMonster() {
        for(int i=0;i<gp.npc.size();i++){
            if (gp.npc.get(i) instanceof Mushroom){
                ((Mushroom)gp.npc.get(i)).stopEntityThread();
                gp.npc.remove(i);
            }
        }
        
    }

    
}
