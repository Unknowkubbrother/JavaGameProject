package core.MAP.MAP2;

import core.GamePanel;
import core.Entity.BringerOfDeath;
import core.Entity.Mushroom;
import core.MAP.Supermap;

public class M2_ST1 extends Supermap{
    public M2_ST1 (GamePanel gp) { 
        super(gp);

        MapContenet = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,5,3,3,3,3,3,3,3,3,3,3,5,1,1,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,5,1,2,1,0,0},
            {0,5,1,1,1,1,1,1,1,1,1,1,5,1,1,1,0,0},
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
    }

    @Override
    public void setDefaultObjects() {
        gp.aSetterObject.setSpawnObjects(3,gp.titleSize * 7,gp.titleSize * 7,64,64);
    }
    
    @Override
    public void update() {
         if (currentTimeMap == 7){
            gp.monster.add(new Mushroom(gp, 7, 7));
            gp.monster.add(new BringerOfDeath(gp, 5, 7));
        }
        
    }

    
}
