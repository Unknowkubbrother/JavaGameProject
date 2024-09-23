package core.Entity;
import java.awt.Rectangle;
import java.util.HashMap;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX , solidAreaDefaultY;
    public boolean collisionOn = false;


    public HashMap<String , Integer> getEntityCoords(){
        HashMap<String, Integer> position = new HashMap<>();
        position.put("x", worldX);
        position.put("y", worldY);
        return position;
    }

   

    
}
