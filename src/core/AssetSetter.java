package core;

import java.awt.Graphics;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        Objects chest_1 = Objects.values()[0];
        chest_1.setWorldX(gp.titleSize * 3);
        chest_1.setWorldY(gp.titleSize * 3);
        chest_1.setMapId(1);
        chest_1.setShow(true);
        gp.objects.add(chest_1);
    }

    public void draw(Graphics g2) {
        for (int i = 0; i < gp.objects.size(); i++) {
            int screenX = gp.objects.get(i).getWorldX() - gp.player.worldX + gp.player.screenX;
            int screenY = gp.objects.get(i).getWorldY() - gp.player.worldY + gp.player.screenY;

            if (gp.objects.get(i).getWorldX() + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                    gp.objects.get(i).getWorldX() - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                    gp.objects.get(i).getWorldY() + gp.titleSize > gp.player.worldY - gp.player.screenY &&
                    gp.objects.get(i).getWorldY() - gp.titleSize < gp.player.worldY + gp.player.screenY &&
                    gp.player.getStateMap() == gp.objects.get(i).getMapId() &&
                    gp.objects.get(i).isShow()
                    ) {
                g2.drawImage(gp.objects.get(i).getObject(), screenX, screenY, gp.titleSize, gp.titleSize, null);
            }
        }
    }
}
