package core.Entity;
import java.util.HashMap;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public String direction;


    public HashMap<String , Integer> getEntityCoords(){
        HashMap<String, Integer> position = new HashMap<>();
        position.put("x", worldX);
        position.put("y", worldY);
        return position;
    }

   

    
}
