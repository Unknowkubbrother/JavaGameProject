package core.Entity;

import core.GamePanel;
import core.KeyHandler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

class playerState{
    private int map = 0;

    public int getMap(){
        return map;
    }

    public void setMap(int map){
        this.map = map;
    }
}

public class Player extends Entity {

    //Default Player
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public playerState player_state = new playerState();
    //

    public Player(GamePanel gp, KeyHandler keyH) {
        
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 45;
        solidArea.y = 77;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 51;
        solidArea.height = 51;


        setDefaultValues();
        loadAnimation();
    }

    public void checkPlayerState(){
        // System.out.println(getEntityCoords());
        if (getEntityCoords().get("x") >= gp.titleSize * 15
            && getEntityCoords().get("x") <= gp.titleSize * 18
            && getEntityCoords().get("y") >= gp.titleSize * 5
            && getEntityCoords().get("y") <= gp.titleSize * 7
            && player_state.getMap() == 0){
            player_state.setMap(1);
            worldX = gp.titleSize * 3;
            worldY = gp.titleSize * 5;
        }
        if (getEntityCoords().get("x") >= gp.titleSize * 14 
            && getEntityCoords().get("x") <= gp.titleSize * 15
            && getEntityCoords().get("y") >= gp.titleSize * 3
            && getEntityCoords().get("y") <= gp.titleSize * 8
            && player_state.getMap() == 1){
            player_state.setMap(0);
            worldX = gp.titleSize * 1;
            worldY = gp.titleSize * 5;
        }
    }

    public int getStateMap(){
        return player_state.getMap();
    }

    private void setDefaultValues() {
        worldX = gp.titleSize * 2;
        worldY = gp.titleSize * 6;
        speed = 4;
        direction = "up";
        setImageWidth(gp.titleSize * 2);
        setImageHeight(gp.titleSize * 2);
        this.solidArea.width = 51;
        this.solidArea.height = 51;
    }

    @Override
    protected void loadAnimation() {
        // Up
        for (int i = 1; i <= 4; i++) {
            up.add(loadSprite(String.format("player/right/right_%d.png", i)));
        }

        // Down
        for (int i = 1; i <= 4; i++) {
            down.add(loadSprite(String.format("player/right/right_%d.png", i)));
        }

        // Right
        for (int i = 1; i <= 4; i++) {
            right.add(loadSprite(String.format("player/right/right_%d.png", i)));
        }

        // Left
        for (int i = 1; i <= 4; i++) {
            left.add(loadSprite(String.format("player/left/left_%d.png", i)));
        }
    }

    public void pickUpObject(int index){

        if (index != -1){
            if (gp.objects.get(index).getObjectId() == 3 && gp.objects.get(index).getMapId() == player_state.getMap() && gp.objects.get(index).isShow()){
                gp.objects.get(index).setShow(false);
                System.out.println("You picked up a chest! on map: " + player_state.getMap());
            }
        }
        
    }

    @Override
    public void setAction() {
     }

    public void InteractNpc(int npcIdx){
        if (npcIdx != -1){
            System.out.println("Interacting with NPC");
        }
    }


    @Override
    public void update() {
        // Check collision with map
        collisionOn = false;
        gp.cChecker.checkMap(this);

        // System.out.println("Collision: " + collisionOn);

        // Check collision with objects
        int objectIdx = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIdx);

        //CHECK NPC COLLISION
        int npcIdx = gp.cChecker.checkEntity(this,gp.npc);
        InteractNpc(npcIdx);

        if (keyH.up) {
            direction = "up";
            if (!collisionOn) {
                worldY -= speed;
                isMoving = true;
            }
        }
        if (keyH.down) {
            direction = "down";
            if (!collisionOn) {
                worldY += speed;
                isMoving = true;
            }
        }
        if (keyH.left) {
            direction = "left";
            if (!collisionOn) {
                worldX -= speed;
                isMoving = true;
            }
        }
        if (keyH.right) {
            direction = "right";
            if (!collisionOn) {
                worldX += speed;
                isMoving = true;
            }
            
        }
        if (!keyH.up && !keyH.down && !keyH.left && !keyH.right) {
            isMoving = false;
        }

        spriteCounter++;
        if (spriteCounter > 10 && isMoving) {
            spriteCounter = 0;
            spriteNum++;
            switch (direction) {
                case "up":
                    if (spriteNum == up.size()) {
                        spriteNum = 0;
                    }
                    break;
                case "down":
                    if (spriteNum == down.size()) {
                        spriteNum = 0;
                    }
                    break;
                case "left":
                    if (spriteNum == left.size()) {
                        spriteNum = 0;
                    }
                    break;
                case "right":
                    if (spriteNum == right.size()) {
                        spriteNum = 0;
                    }
                    break;
                default:
                    break;
            }

        }
    }


    @Override
    public void draw(Graphics g2) {

        BufferedImage image = null;

        if (!isMoving) {
            switch (direction) {
                case "up":
                    image = up.get(0);
                    break;
                case "down":
                    image = down.get(0);
                    break;
                case "left":
                    image = left.get(0);
                    break;
                case "right":
                    image = right.get(0);
                    break;
                default:
                    break;
            }
        }else{
            switch (direction) {
                case "up":
                    image = up.get(spriteNum);
                    break;
                case "down":
                    image = up.get(spriteNum);
                    break;
                case "left":
                    image = left.get(spriteNum);
                    break;
                case "right":
                    image = right.get(spriteNum);
                    break;
                default:
                    break;
            }
        }


        g2.drawImage(image, screenX, screenY, getImageWidth(), getImageHeight() , null);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

}
