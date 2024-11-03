package core.MAP;

import core.GamePanel;
import core.Entity.BringerOfDeath;
import core.Entity.FringEye;
import core.Entity.Mushroom;

public class LOBBY extends Supermap{

    public LOBBY (GamePanel gp) { 
        super(gp);
        
        MapContenet = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,7,7,7,7,7,7,8,7,7,7,7,10,11,11,7,7,0},
            {0,7,7,8,7,7,7,7,7,7,7,7,10,11,11,7,7,0},
            {0,7,7,9,7,7,7,8,7,7,7,7,9,12,11,7,7,0},
            {0,8,7,7,7,7,7,10,8,7,8,7,7,11,12,7,7,0},
            {0,7,7,7,7,7,7,7,8,7,8,10,8,7,7,7,7,0},
            {0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0},
            {0,7,7,7,10,7,7,7,7,7,7,7,9,11,11,11,11,0},
            {0,7,7,7,7,7,7,7,7,7,10,7,11,11,11,11,11,0},
            {0,7,7,7,7,7,7,7,7,9,7,7,7,7,7,7,7,0},
            {0,7,7,7,8,7,7,7,7,7,7,8,7,7,9,7,7,0},
            {0,7,7,8,7,7,7,7,7,8,7,9,11,11,11,12,12,0},
            {0,7,7,7,7,8,7,7,7,7,7,7,11,12,11,12,11,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
    }

    @Override
    public void update() {
        if (currentTimeMap == 5){
            // for(int i=1;i<=3;i++){
                gp.monster.add(new BringerOfDeath(gp, 7, 7));
                gp.monster.add(new FringEye(gp, 5, 5));
                gp.monster.add(new Mushroom(gp, 5, 7));
            // }
            gp.aSetterObject.setSpawnObjects(
                4,
                738,
                364,
                0,
                40,
                40
            );
        }
    }
}
