package core;

import java.util.ArrayList;
import java.awt.Graphics;

public class AssetSetter {
    public ArrayList<Objects> objects = new ArrayList<>();
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        Objects chest_1 = Objects.values()[0];
        chest_1.setWorldX(gp.titleSize * 2);
        chest_1.setWorldY(gp.titleSize * 2);
        chest_1.setMapId(0);
        objects.add(chest_1);
    }

    public void draw(Graphics g2) {
        for (int i = 0; i < objects.size(); i++) {
            int screenX = objects.get(i).getWorldX() - gp.player.worldX + gp.player.screenX;
            int screenY = objects.get(i).getWorldY() - gp.player.worldY + gp.player.screenY;

            if (objects.get(i).getWorldX() + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                    objects.get(i).getWorldX() - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                    objects.get(i).getWorldY() + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                    objects.get(i).getWorldY() - gp.titleSize < gp.player.worldY + gp.player.screenY &&
                    gp.player.getStateMap() == objects.get(i).getMapId()) {
                g2.drawImage(objects.get(i).getObject(), screenX, screenY, gp.titleSize, gp.titleSize, null);
            }
        }
    }
}
