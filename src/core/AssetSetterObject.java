package core;

import java.awt.Graphics;

public class AssetSetterObject {
    GamePanel gp;

    public AssetSetterObject(GamePanel gp) {
        this.gp = gp;
    }

    public void setSpawnObjects(int id, int x, int y, int childMap , int width , int height){
        Objects spawn = ObjectsEnum.values()[id].copy();
        spawn.setWorldX(x);
        spawn.setWorldY(y);
        spawn.setMapId(gp.currentParentMap,childMap);
        spawn.setShow(true);
        spawn.setObjectWidth(width);
        spawn.setObjectHeight(height);
        gp.objects.add(spawn);
    }

    public void draw(Graphics g2) {
        for (int i = 0; i < gp.objects.size(); i++) {
            int screenX = gp.objects.get(i).getWorldX() - gp.player.worldX + gp.player.screenX;
            int screenY = gp.objects.get(i).getWorldY() - gp.player.worldY + gp.player.screenY;

            if (gp.objects.get(i).getWorldX() + (gp.titleSize * 3) > gp.player.worldX - gp.player.screenX &&
                    gp.objects.get(i).getWorldX() - (gp.titleSize * 3) < gp.player.worldX + gp.player.screenX &&
                    gp.objects.get(i).getWorldY() + (gp.titleSize * 3) > gp.player.worldY - gp.player.screenY &&
                    gp.objects.get(i).getWorldY() - (gp.titleSize * 3) < gp.player.worldY + gp.player.screenY &&
                    gp.player.getStateMap()[0] == gp.objects.get(i).getMapId()[0] &&
                    gp.player.getStateMap()[1] == gp.objects.get(i).getMapId()[1] &&
                    gp.objects.get(i).isShow()
                    ) {
                g2.drawImage(gp.objects.get(i).getObject(), screenX, screenY, gp.objects.get(i).getObjectWidth(), gp.objects.get(i).getObjectHeight(), null);
            }
        }
    }
}
