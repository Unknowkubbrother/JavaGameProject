package core.Entity;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Entity {
    protected int x, y;
    protected int speed;
    protected String direction;


    public HashMap<String , Integer> getEntityCoords(){
        HashMap<String, Integer> position = new HashMap<>();
        position.put("x", x);
        position.put("y", y);
        return position;
    }

   

    
}
