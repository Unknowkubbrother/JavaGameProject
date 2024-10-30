package core.Entity;

import core.GamePanel;
import core.KeyHandler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    enum ElementEnums {
        PUNCH(0),
        FIRE(1),
        WATER(2),
        WIND(3);

        public final int elementId;
        private static final Map<Integer, BufferedImage> dict = new HashMap<>();

        private ElementEnums(int elementId) {
            this.elementId = elementId;
        }

        public static void loadElements() {
            long startTime = System.currentTimeMillis();
            System.err.println("Loading Element...");
            dict.put(0, loadSprite("player/elements/punch.png"));
            dict.put(1, loadSprite("player/elements/fire.png"));
            dict.put(2, loadSprite("player/elements/water.png"));
            dict.put(3, loadSprite("player/elements/wind.png"));
            System.out.println("[Element]: Element loaded! (" + (System.currentTimeMillis() - startTime) + "ms)");

        }

        public int getElementId() {
            return elementId;
        }
    
        public static BufferedImage getImageElementId(int id) {
            return dict.get(id);
        }

    }

    class PlayerState {

        private int map = 0;
        private int currentElement = 0;

        PlayerState(){
        }

        public int getMap() {
            return map;
        }

        public BufferedImage getImageCurrentElement(){
            return ElementEnums.getImageElementId(currentElement);
        }


        public void setCurrentElement(int id){
            this.currentElement = id;
        }

        public void setMap(int map) {
            this.map = map;
        }
    }

    private ArrayList<BufferedImage> idle = new ArrayList<>();

    // Default Player
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public PlayerState player_state = new PlayerState();
    //

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);

        solidArea = new Rectangle();

        setDefaultValues();
        loadAnimation();
        ElementEnums.loadElements();
    }

    public void checkPlayerState() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState) {
            return;
        }

        if (getEntityCoords().get("x") >= 910
                && getEntityCoords().get("y") >= 340
                && getEntityCoords().get("y") <= 450
                && player_state.getMap() == 0) {
            player_state.setMap(1);
            worldX = gp.titleSize * 3;
            worldY = gp.titleSize * 5;
        }

        if (getEntityCoords().get("x") >= 864
                && getEntityCoords().get("y") >= 250
                && getEntityCoords().get("y") <= 508
                && player_state.getMap() == 1) {
            player_state.setMap(0);
            worldX = gp.titleSize * 3;
            worldY = gp.titleSize * 5;
        }
    }

    public int getStateMap() {
        return player_state.getMap();
    }

    public BufferedImage getImageCurrentElement(){
        return player_state.getImageCurrentElement();
    }

    public void setCurrentElement(int id){
        player_state.setCurrentElement(id);
    }

    private void setDefaultValues() {
        worldX = gp.titleSize * 2;
        worldY = gp.titleSize * 6;
        speed = 4;
        direction = "up";
        setImageWidth(256);
        setImageHeight(256);

        solidArea.x = 102;
        solidArea.y = 65;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.solidArea.width = 51;
        this.solidArea.height = 64;
    }

    @Override
    protected void loadAnimation() {
        BufferedImage spriteidle = loadSprite("player/Idle.png");
        BufferedImage spriteRight = loadSprite("player/right.png");
        BufferedImage spriteLeft = loadSprite("player/left.png");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 7 && j < 3) {
                    idle.add(spriteidle.getSubimage(i * 256, j * 256, 256, 256));
                }
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 5; i++) {
                up.add(spriteRight.getSubimage(j * 256, i * 256, 256, 256));
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 5; i++) {
                down.add(spriteRight.getSubimage(j * 256, i * 256, 256, 256));
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 5; i++) {
                right.add(spriteRight.getSubimage(j * 256, i * 256, 256, 256));
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 5; i++) {
                left.add(spriteLeft.getSubimage(j * 256, i * 256, 256, 256));
            }
        }
    }

    public void pickUpObject(int index) {

        if (index != -1) {
            if (gp.objects.get(index).getObjectId() == 3 && gp.objects.get(index).getMapId() == player_state.getMap()
                    && gp.objects.get(index).isShow()) {
                gp.objects.get(index).setShow(false);
                System.out.println("You picked up a chest! on map: " + player_state.getMap());
            }
        }

    }

    @Override
    public void setAction() {
    }

    public void InteractNpc(int npcIdx) {
        if (npcIdx != -1) {
            System.out.println("Interacting with NPC");
        }
    }

    @Override
    public void update() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState) {
            return;
        }
        // Check collision with map
        collisionOn = false;
        gp.cChecker.checkMap(this);

        // System.out.println("Collision: " + collisionOn);

        // Check collision with objects
        int objectIdx = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIdx);

        // CHECK NPC COLLISION
        int npcIdx = gp.cChecker.checkEntity(this, gp.npc);
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
            direction = "idle";
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            spriteNum++;
        }

        switch (direction) {
            case "idle":
                if (spriteNum >= idle.size()) {
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
            case "left":
                if (spriteNum >= left.size()) {
                    spriteNum = 0;
                }
                break;
            case "right":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g2) {

        BufferedImage image = null;

        if (!isMoving) {
            image = idle.get(spriteNum);
        } else {
            switch (direction) {
                case "up":
                    image = up.get(spriteNum);
                    break;
                case "down":
                    image = down.get(spriteNum);
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

        g2.drawImage(image, screenX, screenY, getImageWidth(), getImageHeight(), null);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

    @Override
    public void run() {
        while (EntityThread != null) {
            checkPlayerState();
            update();
            try {
                Thread.sleep(ThreadDelay); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
