package core.MAP;

import core.GamePanel;
import core.Texture;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import core.Entity.Monster;

public abstract class Supermap implements ActionListener {

    protected GamePanel gp;
    public int MapContenet[][];
    public Timer timerMap;
    public int currentTimeMap = 0;
    public int countMonster = 0;
    public int currentMonster = 0;

    public Supermap(GamePanel gp) {
        this.gp = gp;
        timerMap = new Timer(1000, this);
        timerMap.start();
        setDefaultSpawnEntityAndObjects();
        setDefaultObjects();
        resetCountKilled();
    }

    public void draw(Graphics g2) {
        for (int i = 0; i < MapContenet.length; i++) {
            for (int j = 0; j < MapContenet[i].length; j++) {
                int worldX = j * gp.titleSize;
                int worldY = i * gp.titleSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
                    Texture texture = Texture.values()[MapContenet[i][j]];
                    g2.drawImage(texture.getTexture(), screenX, screenY, gp.titleSize, gp.titleSize, null);
                }
            }
        }
    }

    public void removeBoxDoor() {
        int i = 0;
        while (i < gp.objects.size()) {
            if (gp.objects.get(i).getObjectId() == 11) {
                gp.objects.remove(i);
            } else {
                i++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gp.gameState != gp.playerState) {
            return;
        }

        currentTimeMap++;
        update();
    }

    abstract public void update();

    abstract public void setDefaultObjects();

    public int getMonsterCount() {
        return countMonster;
    }

    public void resetCountKilled() {
        gp.player.setCountKilled(0);
    }

    public void setDefaultSpawnEntityAndObjects() {
        for (int i = 0; i < gp.monster.size(); i++) {
            if (gp.monster.get(i) instanceof Monster) {
                ((Monster) gp.monster.get(i)).stopMonsterThread();
            }
        }
        gp.monster.clear();
        gp.objects.clear();

    }

}
