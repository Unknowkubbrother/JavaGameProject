package core.MAP;

import core.GamePanel;
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
    public void setSpawnMonster(GamePanel gp) {
        gp.npc.clear();
        gp.npc.add(new Mushroom(gp, 7, 7));
        gp.npc.add(new Mushroom(gp, 9, 9));
        gp.npc.add(new Mushroom(gp, 1, 2));
        gp.npc.add(new Mushroom(gp, 3, 4));
        gp.npc.add(new Mushroom(gp, 5, 6));
    }

}
