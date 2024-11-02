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

    // // Thread
    // protected Thread EntityThread;
    // protected int ThreadDelay;

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
    public int solidAreaDefaultX , solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int spriteNum = 0;


    public Entity(GamePanel gp){
        this.gp = gp;
        imageWidth = gp.titleSize;
        imageHeight = gp.titleSize;
        solidArea = new Rectangle(0,0,imageWidth,imageHeight);
        // startEntityThread();
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

    protected static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(Entity.class.getResourceAsStream("/resources/texture/" + path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract protected void loadAnimation();
    abstract public void setAction();

    public void update(){
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState || gp.gameState == gp.gameOverState) {
            return;
        }

        setAction();

        collisionOn = false;
        gp.cChecker.checkMap(this);

        gp.cChecker.checkObject(this, false);

        gp.cChecker.checkPlayer(this);

        if (!collisionOn) {
            if (direction == "up") {
                worldY -= speed;
            }
            if (direction == "down") {
                worldY += speed;
            }
            if (direction == "left") {
                worldX -= speed;
            }
            if (direction == "right") {
                worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            spriteNum++;
        }

        switch (direction) {
            case "right":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            case "left":
                if (spriteNum >= left.size()) {
                    spriteNum = 0;
                }
                break;
            case "up":
                if (spriteNum >= up.size()) {
                    spriteNum = 0;
                }
                break;
            case "down":
                if (spriteNum >= down.size()) {
                    spriteNum = 0;
                }
                break;
            default:
                break;
        }

    }

    public void draw(Graphics g2) {

        BufferedImage image = null;

        if (direction.equals("up")) {
            if (spriteNum >= up.size()) spriteNum = 0;
            image = up.get(spriteNum);
        } else if (direction.equals("down")) {
            if (spriteNum >= down.size()) spriteNum = 0;
            image = down.get(spriteNum);
        } else if (direction.equals("left")) {
            if (spriteNum >= left.size()) spriteNum = 0;
            image = left.get(spriteNum);
        } else if (direction.equals("right")) {
            if (spriteNum >= right.size()) spriteNum = 0;
            image = right.get(spriteNum);
        }
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.titleSize < gp.player.worldY + gp.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }

    // public void startEntityThread() {
    //     ThreadDelay = 16;
    //     EntityThread = new Thread(this);
    //     EntityThread.start();
    // }

    // @Override
    // public void run() {
    //     while (EntityThread != null) {
    //         update();
    //         try {
    //             Thread.sleep(ThreadDelay); // Approximately 60 FPS
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
    
}
