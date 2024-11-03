package core.Entity;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import core.GamePanel;

import java.awt.Graphics;

public abstract class Entity {
    GamePanel gp;

     //movement
    protected ArrayList<BufferedImage> up = new ArrayList<>();
    protected ArrayList<BufferedImage> down = new ArrayList<>();
    protected ArrayList<BufferedImage> left = new ArrayList<>();
    protected ArrayList<BufferedImage> right = new ArrayList<>();
    
    public int worldX, worldY;
    public int speed;
    public String direction;
    public String lastDirection;
    public boolean isMoving = false;
    private int imageWidth;
    private int imageHeight;
    public Rectangle solidArea;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX , solidAreaDefaultY;
    public boolean collisionOn = false;
    public int spriteCounter = 0;
    public int spriteNum = 0;


    public Entity(GamePanel gp){
        this.gp = gp;
        imageWidth = gp.titleSize;
        imageHeight = gp.titleSize;
        solidArea = new Rectangle(0,0,imageWidth,imageHeight);
    }

    public void setImageWidth(int imageWidth){
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight){
        this.imageHeight = imageHeight;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    public int getImageHeight(){
        return imageHeight;
    }


    public HashMap<String , Integer> getEntityCoords(){
        HashMap<String, Integer> position = new HashMap<>();
        position.put("x", worldX);
        position.put("y", worldY);
        return position;
    }

    public static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(Entity.class.getResourceAsStream("/resources/texture/" + path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract protected void loadAnimation();
    abstract public void setAction();
    abstract public void update();
    abstract public void draw(Graphics g2);
    
}
