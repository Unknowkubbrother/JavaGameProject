package core.MAP.MAP1;

import core.GamePanel;
import core.Entity.*;
import core.MAP.Supermap;

public class M1_ST1 extends Supermap{
    public M1_ST1 (GamePanel gp) { 
        super(gp);

        MapContenet = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,5,3,3,3,3,3,3,3,3,3,3,5,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,2,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,4,4,4,4,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,5,3,3,3,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,5,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,5,1,2,1,0,0},
            {0,5,4,4,4,4,4,4,4,4,4,4,5,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };

        countMonster = 8;
        gp.UiStatus.setAlert("Welcome to the first map", 1000);
    }

    @Override
    public void setDefaultObjects() {
        // set cheat
        gp.aSetterObject.setSpawnObjects(3,gp.titleSize * 14,gp.titleSize * 2,64,64,100);
        //set boxwood
        gp.aSetterObject.setSpawnObjects(5,gp.titleSize * 12,gp.titleSize * 2,64,64,100);
        gp.aSetterObject.setSpawnObjects(5,gp.titleSize * 12,gp.titleSize * 3,64,64,100);

        //set boxdoor
        for(int i=4;i<=8;i++){
            gp.aSetterObject.setSpawnObjects(11,gp.titleSize * 15,gp.titleSize + (i * 64),64,64,80);
        }

        //set VeganHealth
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 3,gp.titleSize * 2 ,64,64,60);
        gp.aSetterObject.setSpawnObjects(8,gp.titleSize * 6,gp.titleSize * 12 ,64,64,60);
        //set VeganMana
        gp.aSetterObject.setSpawnObjects(9,gp.titleSize * 7,gp.titleSize * 12 ,64,64,60);
    }
    
    @Override
    public void update() {
        int x = gp.player.getEntityCoords().get("x");
        int y = gp.player.getEntityCoords().get("y");
        
        // spawn monster
        if (currentMonster < countMonster){

            if (currentTimeMap == 2){
                gp.monster.add(new FringEye(gp, 683, 334));
                gp.monster.add(new Mushroom(gp, 573, 119));
                gp.monster.add(new Mushroom(gp, 153, 189));
                gp.aSetterObject.setSpawnObjects(7,gp.titleSize * 11,gp.titleSize * 2 ,40,40,0);
                currentMonster += 3;
                gp.UiStatus.setAlert("Monster Spawned!!", 1000);
            } 
            if (currentTimeMap == 4){
                gp.aSetterObject.setSpawnObjects(4,gp.titleSize * 6,gp.titleSize * 5 ,40,40,100);
                gp.monster.add(new FringEye(gp, 683, 334));
                currentMonster+=1;
            } 

            if (currentTimeMap == 7){
                gp.UiStatus.setAlert("Select the matching sword element.", 3000);
            }

            if (currentTimeMap == 10){
                gp.aSetterObject.setSpawnObjects(10,gp.titleSize * 3,gp.titleSize * 5 ,40,40,100);
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
                gp.monster.add(new Mushroom(gp, 588,94));
                currentMonster+=1;
            }
            if (currentTimeMap == 15){
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
                gp.monster.add(new FringEye(gp, 540, 94));
                currentMonster+=1;
            }

            if (currentTimeMap == 40){
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }

            if (currentTimeMap == 50){
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }

            if (currentTimeMap == 60){
                gp.UiStatus.setAlert("Find to Cheat!!", 1000);
            }
         
            if (currentTimeMap == 20){
                gp.monster.add(new Mushroom(gp, 555,120));
                gp.UiStatus.setAlert("Mushroom say I will kill you !!!!", 2000);
                currentMonster+=1;
            }

            // span monster in cheat
            if (x > 723 && x <= 868 && y > 0 && y <= 124){
                gp.monster.add(new Mushroom(gp, 803, 50));
                currentMonster+=1;
            }


        }
        
    }

    
}
