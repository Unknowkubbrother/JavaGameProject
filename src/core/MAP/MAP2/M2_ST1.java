package core.MAP.MAP2;

import core.GamePanel;
// import core.Entity.BringerOfDeath;
import core.Entity.FringEye;
import core.Entity.Mushroom;
import core.MAP.Supermap;

public class M2_ST1 extends Supermap{

    public M2_ST1 (GamePanel gp) { 
        super(gp);
        
        MapContenet = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,7,7,7,7,7,7,8,7,7,7,7,10,14,11,7,7,0},
            {0,7,7,8,7,7,7,7,7,7,7,7,10,14,11,7,7,0},
            {0,7,7,9,7,7,7,8,7,7,7,7,9,14,11,7,7,0},
            {0,8,7,7,7,7,7,10,8,7,8,7,7,14,12,7,7,0},
            {0,7,7,7,7,7,7,7,8,7,8,10,8,7,7,7,7,0},
            {0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0},
            {0,7,7,7,10,7,7,7,7,7,7,7,9,11,11,11,11,0},
            {0,7,7,7,7,7,7,7,7,7,10,7,11,11,11,11,11,0},
            {0,7,7,7,7,7,7,7,7,9,7,7,7,7,7,7,7,0},
            {0,7,7,7,8,7,7,7,7,7,7,8,7,7,9,7,7,0},
            {0,7,7,8,7,7,7,7,7,8,7,9,11,14,11,12,12,0},
            {0,7,7,7,7,8,7,7,7,7,7,7,11,14,11,12,11,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        countMonster = 8;

        gp.UiStatus.setAlert("Welcome to the second map", 1000);

    }

    @Override
    public void setDefaultObjects() {
        gp.objects.clear();
        gp.aSetterObject.setSpawnObjects(0,gp.titleSize * 2,gp.titleSize * 6,192,192,0);
        gp.aSetterObject.setSpawnObjects(1,gp.titleSize * 5,gp.titleSize * 2,90,192,
        0);
        gp.aSetterObject.setSpawnObjects(2,gp.titleSize * 13,gp.titleSize * 8,90,192,
        0);
        gp.aSetterObject.setSpawnObjects(2,gp.titleSize * 13,gp.titleSize * 3,90,192,
        0);


        //set Box top
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 14, gp.titleSize *5, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 15, gp.titleSize *5, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 16, gp.titleSize *5, 64, 64, 80);

        //setBox bottom
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 14, gp.titleSize *10, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 15, gp.titleSize *10, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(5, gp.titleSize * 16, gp.titleSize *10, 64, 64, 80);

        //setBoxdoor
        gp.aSetterObject.setSpawnObjects(11, gp.titleSize * 16, gp.titleSize *6, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(11, gp.titleSize * 16, gp.titleSize *7, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(11, gp.titleSize * 16, gp.titleSize *8, 64, 64, 80);
        gp.aSetterObject.setSpawnObjects(11, gp.titleSize * 16, gp.titleSize *9, 64, 64, 80);

        //set Chest
        gp.aSetterObject.setSpawnObjects(3, gp.titleSize * 15, gp.titleSize * 2, 64, 64, 0);

        // set VeganHealth
        gp.aSetterObject.setSpawnObjects(8, gp.titleSize * 3, gp.titleSize * 2, 64, 64, 60);
        gp.aSetterObject.setSpawnObjects(8, gp.titleSize * 6, gp.titleSize * 12, 64, 64, 60);
        // set VeganMana
        gp.aSetterObject.setSpawnObjects(9, gp.titleSize * 7, gp.titleSize * 12, 64, 64, 60);
        
    }

    @Override
    public void update() {

        // spawn item to end game
        if (gp.player.getCountKilled() >= countMonster) {
            gp.aSetterObject.setSpawnObjects(7, gp.titleSize * 14, gp.titleSize * 6, 40, 40, 0);

            gp.aSetterObject.setSpawnObjects(10, gp.titleSize * 14, gp.titleSize * 7, 40, 40, 0);

            gp.aSetterObject.setSpawnObjects(4, gp.titleSize * 14, gp.titleSize * 8, 40, 40, 0);

            gp.player.setCountKilled(0);
        }

        // spawn monster
        if (currentMonster < countMonster) {

            if (currentTimeMap == 2) {
                gp.monster.add(new FringEye(gp, 683, 334));
                gp.monster.add(new Mushroom(gp, 573, 119));
                gp.monster.add(new Mushroom(gp, 153, 189));
                gp.monster.add(new FringEye(gp, 843, 100));
                gp.aSetterObject.setSpawnObjects(7, gp.titleSize * 11, gp.titleSize * 2, 40, 40, 0);
                currentMonster += 4;
                gp.UiStatus.setAlert("Monster Spawned!!", 1000);
            }
            if (currentTimeMap == 4) {
                gp.aSetterObject.setSpawnObjects(4, gp.titleSize * 6, gp.titleSize * 5, 40, 40, 100);
                gp.monster.add(new FringEye(gp, 683, 334));
                currentMonster += 1;
            }

            if (currentTimeMap == 7) {
                gp.UiStatus.setAlert("Select the matching sword element.", 3000);
            }

            if (currentTimeMap == 10) {
                gp.aSetterObject.setSpawnObjects(10, gp.titleSize * 3, gp.titleSize * 5, 40, 40, 100);
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
                gp.monster.add(new Mushroom(gp, 588, 94));
                currentMonster += 1;
            }
            if (currentTimeMap == 15) {
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
                gp.aSetterObject.setSpawnObjects(12, gp.titleSize * 11, gp.titleSize * 3, 40, 40, 0);
                gp.monster.add(new FringEye(gp, 540, 94));
                currentMonster += 1;
            }

            if (currentTimeMap == 40) {
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }

            if (currentTimeMap == 50) {
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }

            if (currentTimeMap == 60) {
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }

            if (currentTimeMap == 20) {
                gp.monster.add(new Mushroom(gp, 555, 120));
                gp.UiStatus.setAlert("Mushroom say I will kill you !!!!", 2000);
                currentMonster += 1;
            }

        }
    }
}
