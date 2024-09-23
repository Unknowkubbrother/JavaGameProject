package core;

import java.awt.Graphics;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        Objects spawn = ObjectsEnum.values()[0].copy();
        spawn.setWorldX(gp.titleSize * 2);
        spawn.setWorldY(gp.titleSize * 6);
        spawn.setMapId(0);
        spawn.setShow(true);
        spawn.setObjectWidth(192);
        spawn.setObjectHeight(192);
        gp.objects.add(spawn);
        
        Objects goddess = ObjectsEnum.values()[1].copy();
        goddess.setWorldX(gp.titleSize * 5);
        goddess.setWorldY(gp.titleSize * 2);
        goddess.setMapId(0);
        goddess.setShow(true);
        goddess.setObjectWidth(90);
        goddess.setObjectHeight(192);
        gp.objects.add(goddess);

        Objects pole1 = ObjectsEnum.values()[2].copy();
        pole1.setWorldX(gp.titleSize * 13);
        pole1.setWorldY(gp.titleSize * 8);
        pole1.setMapId(0);
        pole1.setShow(true);
        pole1.setObjectWidth(90);
        pole1.setObjectHeight(192);
        gp.objects.add(pole1);

        Objects pole2 = ObjectsEnum.values()[2].copy();
        pole2.setWorldX(gp.titleSize * 13);
        pole2.setWorldY(gp.titleSize * 3);
        pole2.setMapId(0);
        pole2.setShow(true);
        pole2.setObjectWidth(90);
        pole2.setObjectHeight(192);
        gp.objects.add(pole2);

        Objects chest_1 = ObjectsEnum.values()[3].copy();
        chest_1.setWorldX(gp.titleSize * 2);
        chest_1.setWorldY(gp.titleSize * 2);
        chest_1.setMapId(0);
        chest_1.setShow(true);
        chest_1.setObjectWidth(64);
        chest_1.setObjectHeight(64);
        gp.objects.add(chest_1);

        Objects chest_2 = ObjectsEnum.values()[3].copy();
        chest_2.setWorldX(gp.titleSize * 4);
        chest_2.setWorldY(gp.titleSize * 4);
        chest_2.setMapId(1);
        chest_2.setShow(true);
        chest_2.setObjectWidth(64);
        chest_2.setObjectHeight(64);
        gp.objects.add(chest_2);
    }

    public void draw(Graphics g2) {
        for (int i = 0; i < gp.objects.size(); i++) {
            int screenX = gp.objects.get(i).getWorldX() - gp.player.worldX + gp.player.screenX;
            int screenY = gp.objects.get(i).getWorldY() - gp.player.worldY + gp.player.screenY;

            if (gp.objects.get(i).getWorldX() + (gp.titleSize * 3) > gp.player.worldX - gp.player.screenX &&
                    gp.objects.get(i).getWorldX() - (gp.titleSize * 3) < gp.player.worldX + gp.player.screenX &&
                    gp.objects.get(i).getWorldY() + (gp.titleSize * 3) > gp.player.worldY - gp.player.screenY &&
                    gp.objects.get(i).getWorldY() - (gp.titleSize * 3) < gp.player.worldY + gp.player.screenY &&
                    gp.player.getStateMap() == gp.objects.get(i).getMapId() &&
                    gp.objects.get(i).isShow()
                    ) {
                g2.drawImage(gp.objects.get(i).getObject(), screenX, screenY, gp.objects.get(i).getObjectWidth(), gp.objects.get(i).getObjectHeight(), null);
            }
        }
    }
}
