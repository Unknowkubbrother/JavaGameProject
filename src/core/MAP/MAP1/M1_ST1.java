package core.MAP.MAP1;

import core.GamePanel;
// import core.Entity.BringerOfDeath;
import core.Entity.FringEye;
import core.Entity.Mushroom;
import core.MAP.Supermap;

public class M1_ST1 extends Supermap{

    public M1_ST1 (GamePanel gp) { 
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

        parentMap = 0;
        childMap = 0;
        setDefaultObjects();
    }

    @Override
    public void setDefaultObjects() {
        gp.objects.clear();
        gp.aSetterObject.setSpawnObjects(0,gp.titleSize * 2,gp.titleSize * 6,childMap,192,192);
        gp.aSetterObject.setSpawnObjects(1,gp.titleSize * 5,gp.titleSize * 2,childMap,90,192);
        gp.aSetterObject.setSpawnObjects(2,gp.titleSize * 13,gp.titleSize * 8,childMap,90,192);
        gp.aSetterObject.setSpawnObjects(2,gp.titleSize * 13,gp.titleSize * 3,childMap,90,192);
        gp.aSetterObject.setSpawnObjects(3,gp.titleSize * 2,gp.titleSize * 2,childMap,64,64);
    }

    @Override
    public void update() {
        if (currentTimeMap == 5){
            // for(int i=1;i<=3;i++){
                // gp.monster.add(new BringerOfDeath(gp, 7, 7));
                gp.monster.add(new FringEye(gp, 5, 5));
                gp.monster.add(new Mushroom(gp, 5, 7));
            // }
            gp.aSetterObject.setSpawnObjects(
                4,
                738,
                364,
                childMap,
                40,
                40
            );
        }
    }
}