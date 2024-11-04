package core;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

enum ObjectsEnum {
    SPAWN(0),
    GODDESS(1),
    POLE(2),
    chest(3),
    mana(4);


    public final int objectId;
    private static Map<Integer, Objects> dict = new HashMap<>();


    private ObjectsEnum(int objectId) {
        this.objectId = objectId;
    }

    public static void loadObjects(){
        long startTime = System.currentTimeMillis();
        System.err.println("Loading objects...");
        try{
            dict.put(0, new Objects("/resources/texture/objects/spawn.png", false,false, 0));
            dict.put(1, new Objects("/resources/texture/objects/goddess.png", true,false,1));
            dict.put(2, new Objects("/resources/texture/objects/pole.png", true,false,2));
            dict.put(3, new Objects("/resources/texture/objects/chest.png", true,false,3));
            dict.put(4, new Objects("/resources/texture/objects/mana.png", true,true,4));
        }catch(IOException e){
            System.out.println("[Object]: Failed to load Object!");
            e.printStackTrace();
        }
        System.out.println("[Object]: Objects loaded! (" + (System.currentTimeMillis() - startTime) + "ms)");
    }

    public int getObjectId() {
        return objectId;
    }

    public Objects copy() {
        return dict.get(objectId).copy();
    }

}

public class Objects {
    private int objectId;
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;
    private boolean collision;
    private int worldX;
    private int worldY;
    private int[] mapId;
    private Rectangle solidArea; 
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;
    private int health = 100;
    private boolean isAttacking;


    public Objects(String path,boolean collision,boolean isAttacking,int objectId) throws IOException {
        this.image = ImageIO.read(getClass().getResourceAsStream(path));
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.collision = collision;
        this.worldX = 0;
        this.worldY = 0;
        this.mapId = new int[]{0, 0};
        this.solidArea = new Rectangle(0,0,image.getWidth(),image.getHeight());
        this.objectId = objectId;
        this.isAttacking = isAttacking;
    }

    public Objects(BufferedImage img,boolean collision, int objectId) throws IOException {
        this.image = img;
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.collision = collision;
        this.worldX = 0;
        this.worldY = 0;
        this.mapId = new int[]{0, 0};
        this.solidArea = new Rectangle(0,0,image.getWidth(),image.getHeight());
        this.objectId = objectId;
    }

    public static void loadObjects() {
        ObjectsEnum.loadObjects();
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        if (health > 100) {
            health = 100;
        }
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public boolean ObjectisDead() {
        return health <= 0;
    }

    public void AttackedByPlayer(int attackDamage) {
        setHealth(getHealth() - attackDamage);
    }


    public int getObjectId() {
        return objectId;
    }

    public int getObjectWidth() {
        return imageWidth;
    }

    public int getObjectHeight() {
        return imageHeight;
    }

    public void setObjectWidth(int imageWidth) {
        this.imageWidth = imageWidth;
        this.solidArea.width = imageWidth;
    } 

    public void setObjectHeight(int imageHeight) {
        this.imageHeight = imageHeight;
        this.solidArea.height = imageHeight;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int[] getMapId() {
        return mapId;
    }

    public void setMapId(int parent, int child) {
        this.mapId[0] = parent;
        this.mapId[1] = child;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public BufferedImage getObject() {
        return image;
    }

    public Objects copy() {
        Objects copy = null;
        try {
            copy = new Objects(image, collision, objectId);
            copy.setWorldX(worldX);
            copy.setWorldY(worldY);
            copy.setMapId(
                mapId[0],
                mapId[1]
            );
            copy.setCollision(collision);
            copy.setAttacking(isAttacking);
            copy.setObjectWidth(imageWidth);
            copy.setObjectHeight(imageHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copy;
    }

}
