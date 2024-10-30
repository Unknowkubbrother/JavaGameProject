package core.MAP;

import core.GamePanel;
import core.Entity.Mushroom;

public class STAGE_1 extends Supermap{

    public STAGE_1 (GamePanel gp) { 
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
    public void setSpawnMonster(GamePanel gp) {
        gp.npc.clear();
        gp.npc.add(new Mushroom(gp, 5, 5));
        gp.npc.add(new Mushroom(gp, 7, 7));
        gp.npc.add(new Mushroom(gp, 9, 9));
        gp.npc.add(new Mushroom(gp, 1, 2));
        gp.npc.add(new Mushroom(gp, 3, 4));
        gp.npc.add(new Mushroom(gp, 5, 6));
        
    }

    
}
