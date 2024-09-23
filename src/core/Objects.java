package core;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public enum Objects {
    CHEST(0);

    public final int objectId;
    private static Map<Integer, ObjectLoader> dict = new HashMap<>();


    private Objects(int objectId) {
        this.objectId = objectId;
    }

    public static void loadObjects(){
        long startTime = System.currentTimeMillis();
        System.err.println("Loading objects...");
        try{
            dict.put(0, new ObjectLoader("/resources/texture/objects/chest.png", true));

        }catch(IOException e){
            System.out.println("[Object]: Failed to load Object!");
            e.printStackTrace();
        }
        System.out.println("[Object]: Objects loaded! (" + (System.currentTimeMillis() - startTime) + "ms)");
    }

    public int getObjectId() {
        return objectId;
    }

    public BufferedImage getObject() {
        return dict.get(objectId).getImage();
    }

    public int getObjectWidth() {
        return dict.get(objectId).getObjectWidth();
    }

    public int getObjectHeight() {
        return dict.get(objectId).getObjectHeight();
    }

    public boolean isCollision() {
        return dict.get(objectId).isCollision();
    }

    public void setCollision(boolean collision) {
        dict.get(objectId).setCollision(collision);
    }

    public int getWorldX() {
        return dict.get(objectId).getWorldX();
    }

    public int getWorldY() {
        return dict.get(objectId).getWorldY();
    }

    public void setWorldX(int worldX) {
        dict.get(objectId).setWorldX(worldX);
    }

    public void setWorldY(int worldY) {
        dict.get(objectId).setWorldY(worldY);
    }

    public int getMapId() {
        return dict.get(objectId).getMapId();
    }

    public void setMapId(int mapId) {
        dict.get(objectId).setMapId(mapId);
    }

    public boolean isShow() {
        return dict.get(objectId).isShow();
    }

    public void setShow(boolean show) {
        dict.get(objectId).setShow(show);
    }

    public void setSolidAreaX(int x) {
        dict.get(objectId).setSolidAreaX(x);
    }

    public void setSolidAreaY(int y) {
        dict.get(objectId).setSolidAreaY(y);
    }

    public void setSolidAreaWidth(int width) {
        dict.get(objectId).setSolidAreaWidth(width);
    }

    public void setSolidAreaHeight(int height) {
        dict.get(objectId).setSolidAreaHeight(height);
    }

    public void setSolidArea(int x, int y, int width, int height) {
        dict.get(objectId).setSolidArea(x, y, width, height);
    }

    public Rectangle getSolidArea() {
        return dict.get(objectId).getSolidArea();
    }

    public int getSolidAreaDefaultX() {
        return dict.get(objectId).getSolidAreaDefaultX();
    }

    public int getSolidAreaDefaultY() {
        return dict.get(objectId).getSolidAreaDefaultY();
    }

}

class ObjectLoader {
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;
    private boolean collision;
    private int worldX;
    private int worldY;
    private int mapId;
    private boolean show;
    private Rectangle solidArea; 
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;

    public ObjectLoader(String path,boolean collision) throws IOException {
        this.image = ImageIO.read(getClass().getResourceAsStream(path));
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.collision = collision;
        this.worldX = 0;
        this.worldY = 0;
        this.mapId = 0;
        this.show = true;
        this.solidArea = new Rectangle(0,0,64,64);
    }

    public int getObjectWidth() {
        return imageWidth;
    }

    public int getObjectHeight() {
        return imageHeight;
    }

    public boolean isCollision() {
        if (show){
            return collision;
        }else{
            return false;
        }
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }


    public void setSolidAreaX(int x) {
        this.solidArea.x = x;
    }

    public void setSolidAreaY(int y) {
        this.solidArea.x = y;
    }

    public void setSolidAreaWidth(int width) {
        this.solidArea.width = width;
    }

    public void setSolidAreaHeight(int height) {
        this.solidArea.height = height;
    }

    public void setSolidArea(int x, int y, int width, int height) {
        this.solidArea.x = x;
        this.solidArea.y = y;
        this.solidArea.width = width;
        this.solidArea.height = height;
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

    public BufferedImage getImage() {
        return image;
    }

}
