package core.MAP.MAP2;

import core.GamePanel;
import core.Entity.BringerOfDeath;
// import core.Entity.BringerOfDeath;
import core.Entity.FringEye;
import core.Entity.Mushroom;
import core.MAP.Supermap;

public class M2_ST2 extends Supermap{

    public M2_ST2 (GamePanel gp) { 
        super(gp);
        
        MapContenet = new int[][]{
            {0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,15,15,15,15,15,7 ,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,7 ,7 ,7 ,7 ,15,15,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,15,7 ,7 ,7 ,7 ,7 ,7 ,15,15,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,15 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,15,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,15 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,7 ,7,7 ,7 ,7 ,7 ,15,15 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,7 ,7,7 ,7 ,7 ,15,15,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,7 ,7 ,7 ,7 ,15,15,7 ,7 ,7 ,15,0},
            {0 ,15,7 ,7 ,7 ,7 ,15,15,15,15,15,15,7 ,7 ,7 ,7 ,15,0},
            {0 ,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,0}
        };

        countMonster = 15;

        gp.UiStatus.setAlert("Watch out for boss monsters!!", 1500);

    }

    @Override
    public void setDefaultObjects() {
        gp.objects.clear();

        //set Tree
        gp.aSetterObject.setSpawnObjects(13,gp.titleSize * 2,gp.titleSize * 3,129,160,0);


        //setVegan Health
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 5,gp.titleSize * 2,64,64,60);
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 14,gp.titleSize * 12,64,64,60);


        //set Vegan Mana
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 2,gp.titleSize * 12,64,64,60);
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 11,gp.titleSize * 4,64,64,60);

        //set BoxWood
        gp.aSetterObject.setSpawnObjects(5,gp.titleSize * 15,gp.titleSize * 9,64,64,60);
        
    }

    @Override
    public void update() {

        // spawn monster
        if (currentMonster < countMonster) {

            if (currentTimeMap == 1){
                gp.monster.add(new Mushroom(gp, 379, 609));
                gp.monster.add(new Mushroom(gp, 329, 109));
                gp.monster.add(new FringEye(gp, 829, 600));
                currentMonster+=3;
                gp.UiStatus.setAlert("Monster spawned!!", 3000);
            }

            if (currentTimeMap == 10){
                gp.monster.add(new FringEye(gp, 130, 350));
                gp.monster.add(new Mushroom(gp, 289, 149));
                gp.monster.add(new Mushroom(gp, 334,469));
                gp.monster.add(new FringEye(gp, 479, 174));
                currentMonster += 4;
                gp.UiStatus.setAlert("Wave 1!!", 1000);
            }

            if (currentTimeMap == 30){
                gp.monster.add(new FringEye(gp, 809, 109));
                gp.monster.add(new Mushroom(gp, 840, 110));
                gp.monster.add(new Mushroom(gp, 799, 339));
                currentMonster += 3;
                gp.UiStatus.setAlert("Wave 2!!", 1000);
            }

            if (currentTimeMap == 40){
                gp.aSetterObject.setSpawnObjects(7, gp.titleSize * 6, gp.titleSize * 6, 40, 40, 0);
                gp.aSetterObject.setSpawnObjects(10, gp.titleSize * 8, gp.titleSize * 6, 40, 40, 0);
                gp.UiStatus.setAlert("Item health and armor drop!!", 1500);
            }

             if (currentTimeMap == 45){
                gp.monster.add(new BringerOfDeath(gp, 464, 369));
                currentMonster += 1;
                gp.UiStatus.setAlert("Boss Spawn!!",2000);
            }

            if (currentTimeMap == 48){
                gp.aSetterObject.setSpawnObjects(12, gp.titleSize * 8, gp.titleSize * 3, 40, 40, 0);
                gp.UiStatus.setAlert("Item speed drop!!", 1000);
            }

            if (currentTimeMap == 55){
                gp.monster.add(new FringEye(gp, 809, 109));
                gp.monster.add(new Mushroom(gp, 464, 134));
                gp.monster.add(new Mushroom(gp, 364,564));
                gp.monster.add(new FringEye(gp, 99, 364));
                currentMonster += 4;
                gp.UiStatus.setAlert("Wave 3!!", 1000);
            }

            if (currentTimeMap == 50){
                gp.UiStatus.setAlert("Boss say I will kill you !!!!", 1000);
            }


        }
    }
}
