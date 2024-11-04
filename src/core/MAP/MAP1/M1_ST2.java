package core.MAP.MAP1;

import core.GamePanel;
import core.Entity.*;
import core.MAP.Supermap;

public class M1_ST2 extends Supermap{
    public M1_ST2 (GamePanel gp) { 
        super(gp);

        MapContenet = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0},
            {0,5,14,1,1,1,1,1,1,1,1,1,1,1,14,6,0,0},
            {0,5,1,14,1,1,1,1,1,1,1,1,1,14,1,6,0,0},
            {0,5,1,1,14,1,1,1,1,1,1,1,14,1,1,6,0,0},
            {0,5,1,1,1,14,1,1,1,1,1,14,1,1,1,6,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,6,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,6,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,6,0,0},
            {0,5,1,1,1,14,1,1,1,1,1,14,1,1,1,6,0,0},
            {0,5,1,1,14,1,1,1,1,1,1,1,14,1,1,6,0,0},
            {0,5,1,14,1,1,1,1,1,1,1,1,1,14,1,6,0,0},
            {0,5,14,1,1,1,1,1,1,1,1,1,1,1,14,6,0,0},
            {0,5,4,4,4,4,4,4,4,4,4,4,4,4,4,6,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

        countMonster = 15;
        gp.UiStatus.setAlert("Watch out for boss monsters!!", 1500);
    }

    @Override
    public void setDefaultObjects() {

        //set VeganHealth
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 3,gp.titleSize * 2 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 6,gp.titleSize * 12 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 2,gp.titleSize * 11 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 14,gp.titleSize * 11 ,64,64,60);
        //set VeganMana
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 7,gp.titleSize * 12 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 14,gp.titleSize * 3 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 4,gp.titleSize * 3 ,64,64,60);

        //set Thorn right
        gp.aSetterObject.setSpawnObjects(6,gp.titleSize * 11,gp.titleSize * 7 ,64,64,0);

        //set Thorn left
        gp.aSetterObject.setSpawnObjects(6,gp.titleSize * 5,gp.titleSize * 7 ,64,64,0);

        //set Thorn Custom
        gp.aSetterObject.setSpawnObjects(6,gp.titleSize * 9,gp.titleSize * 11 ,64,64,0);
        gp.aSetterObject.setSpawnObjects(6,gp.titleSize * 7,gp.titleSize * 4 ,64,64,0);
    }
    
    @Override
    public void update() {        

        // spawn monster
        if (currentMonster < countMonster){

            if (currentTimeMap == 2){
                gp.monster.add(new FringEye(gp, 584, 109));
                gp.monster.add(new Mushroom(gp, 464, 134));
                gp.monster.add(new Mushroom(gp, 364,564));
                gp.monster.add(new FringEye(gp, 99, 364));
                currentMonster += 4;
                gp.UiStatus.setAlert("Monster Spawned!!", 1000);
            } 


            if (currentTimeMap == 10){
                gp.monster.add(new FringEye(gp, 439, 354));
                gp.monster.add(new Mushroom(gp, 289, 149));
                gp.monster.add(new Mushroom(gp, 334,469));
                gp.monster.add(new FringEye(gp, 599, 649));
                currentMonster += 4;
                gp.UiStatus.setAlert("Wave 1!!", 1000);
            }

            if (currentTimeMap == 30){
                gp.monster.add(new FringEye(gp, 584, 109));
                gp.monster.add(new Mushroom(gp, 464, 134));
                currentMonster += 2;
                gp.UiStatus.setAlert("Wave 2!!", 1000);
            }

            
            if (currentTimeMap == 40){
                gp.aSetterObject.setSpawnObjects(7, gp.titleSize * 7, gp.titleSize * 7, 40, 40, 0);
                gp.aSetterObject.setSpawnObjects(10, gp.titleSize * 8, gp.titleSize * 6, 40, 40, 0);
                gp.UiStatus.setAlert("Item health and armor drop!!", 1500);
            }

            if (currentTimeMap == 45){
                gp.monster.add(new BringerOfDeath(gp, 404, 364));
                currentMonster += 1;
                gp.UiStatus.setAlert("Boss Spawn!!",2000);
            }


            if (currentTimeMap == 48){
                gp.aSetterObject.setSpawnObjects(12, gp.titleSize * 6, gp.titleSize * 6, 40, 40, 0);
                gp.UiStatus.setAlert("Item speed drop!!", 1000);
            }

            if (currentTimeMap == 55){
                gp.monster.add(new FringEye(gp, 584, 109));
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
