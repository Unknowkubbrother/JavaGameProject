package core.Entity;

import core.GamePanel;
import core.KeyHandler;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    // Element Enums
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

    // Player State
    class PlayerState {
        private int map = 0;
        private int currentElement = 0;
        private int health = 100;
        private int armor = 20;
        private int mana = 100;
        private boolean isDead = false;
        private boolean isAttacking = false;
    }

    public int getHealth() {
        return player_state.health;
    }

    public void setHealth(int health) {
        if (health > 100) {
            health = 100;
        }
        if (health < 0) {
            player_state.isDead = true;
            health = 0;
        }
        player_state.health = health;
    }

    public int getArmor() {
        return player_state.armor;
    }

    public void setArmor(int armor) {
        player_state.armor = armor;
    }

    public int getMana() {
        return player_state.mana;
    }

    public void setMana(int mana) {
        player_state.mana = mana;
    }

    public boolean isDead() {
        return player_state.isDead;
    }

    public void setDead(boolean isDead) {
        player_state.isDead = isDead;
    }

    public boolean isAttacking() {
        return player_state.isAttacking;
    }

    public void setAttacking(boolean isAttacking) {
        player_state.isAttacking = isAttacking;
    }

    public int getStateMap() {
        return player_state.map;
    }

    public BufferedImage getImageCurrentElement() {
        return ElementEnums.getImageElementId(player_state.currentElement);
    }

    public void setCurrentElement(int id) {
        player_state.currentElement = id;
    }

    public int getCurrentElement() {
        return player_state.currentElement;
    }

    public void setMap(int map) {
        player_state.map = map;
    }

    // Check player state map
    public void checkPlayerStateMap() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState) {
            return;
        }

        if (getEntityCoords().get("x") >= 910
                && getEntityCoords().get("y") >= 340
                && getEntityCoords().get("y") <= 450
                && getStateMap() == 0) {
            setMap(1);
            worldX = gp.titleSize * 3;
            worldY = gp.titleSize * 5;
        }

        if (getEntityCoords().get("x") >= 864
                && getEntityCoords().get("y") >= 250
                && getEntityCoords().get("y") <= 508
                && getStateMap() == 1) {
            setMap(0);
            worldX = gp.titleSize * 3;
            worldY = gp.titleSize * 5;
        }
    }

    public void pickUpObject(int index) {

        if (index != -1) {
            if (gp.objects.get(index).getObjectId() == 3 && gp.objects.get(index).getMapId() == getStateMap()
                    && gp.objects.get(index).isShow()) {
                gp.objects.get(index).setShow(false);
                System.out.println("You picked up a chest! on map: " + getStateMap());
            }
        }

    }

    public void InteractNpc(int npcIdx) {
        if (npcIdx != -1) {
            System.out.println("Interacting with NPC");
        }
    }

    // END Player State

    // Animation
    private ArrayList<BufferedImage> idle = new ArrayList<>();
    private ArrayList<BufferedImage> attack_fire = new ArrayList<>();
    private ArrayList<BufferedImage> attack_water = new ArrayList<>();
    private ArrayList<BufferedImage> attack_wind = new ArrayList<>();

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

    private void setDefaultValues() {
        worldX = gp.titleSize * 2;
        worldY = gp.titleSize * 6;
        speed = 10;
        direction = "idle";
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
        BufferedImage spriteAttackFire = loadSprite("player/Attack_Fire.png");
        BufferedImage spriteAttackWater = loadSprite("player/Attack_Water.png");
        BufferedImage spriteAttackWind = loadSprite("player/Attack_Wind.png");

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

        for (int j = 7; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (i == 2) {
                    attack_fire.add(spriteAttackFire.getSubimage(j * 256, i * 256, 256, 256));
                }
            }
        }

        for (int j = 7; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (i == 2) {
                    attack_water.add(spriteAttackWater.getSubimage(j * 256, i * 256, 256, 256));
                }
            }
        }

        for (int j = 7; j >= 0; j--) {
            for (int i = 3; i >= 0; i--) {
                if (i == 2) {
                    attack_wind.add(spriteAttackWind.getSubimage(j * 256, i * 256, 256, 256));
                }
            }
        }


    }

    public void ActionAttack() {
        spriteCounter++;
        if (spriteCounter > 2) {
            spriteCounter = 0;
            spriteNum++;
        }

    }

    @Override
    public void setAction() {

    }

    @Override
    public void update() {
        if (gp.gameState == gp.pauseState || gp.gameState == gp.menuState) {
            return;
        }

        // Check if player is dead
        // if (isDead()) {
        // gp.gameState = gp.pauseState;
        // }

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
                setAttacking(false);
            }
        }
        if (keyH.down) {
            direction = "down";
            if (!collisionOn) {
                worldY += speed;
                isMoving = true;
                setAttacking(false);
            }
        }
        if (keyH.left) {
            direction = "left";
            if (!collisionOn) {
                worldX -= speed;
                isMoving = true;
                setAttacking(false);
            }
        }
        if (keyH.right) {
            direction = "right";
            if (!collisionOn) {
                worldX += speed;
                isMoving = true;
                setAttacking(false);
            }

        }

        if (!keyH.up && !keyH.down && !keyH.left && !keyH.right && !isAttacking()) {
            isMoving = false;
            if (direction == "up" || direction == "down" || direction == "left" || direction == "right") {
                lastDirection = direction;
            }
            direction = "idle";
        }

        if (isAttacking() && !isMoving) {
            if (getCurrentElement() != 0) {
                if (getCurrentElement() == 1) {
                    direction = "attack_fire";
                } else if (getCurrentElement() == 2) {
                    direction = "attack_water";
                }else if (getCurrentElement() == 3) {
                    direction = "attack_wind";
                }
                isMoving = false;
                ActionAttack();
            } else {
                isMoving = false;
                direction = "idle";
                setAttacking(false);
            }
        }

        if (direction == "idle" || direction == "right" || direction == "left" || direction == "up"
                || direction == "down") {
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteCounter = 0;
                spriteNum++;
            }
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
            case "attack_fire":
                if (spriteNum >= attack_fire.size()) {
                    spriteNum = 0;
                    setAttacking(false);
                }
                break;
            case "attack_water":
                if (spriteNum >= attack_water.size()) {
                    spriteNum = 0;
                    setAttacking(false);
                }
                break;
            case "attack_wind":
                if (spriteNum >= attack_wind.size()) {
                    spriteNum = 0;
                    setAttacking(false);
                }
                break;
            default:
                break;
        }

        //
        checkPlayerStateMap();
    }

    @Override
    public void draw(Graphics g2) {
        BufferedImage image = null;
        int offsetX = 0;
        int offsetWidth = 0;

        switch (direction) {
            case "idle":
                image = idle.get(spriteNum);
                break;
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
            case "attack_fire":
                image = attack_fire.get(spriteNum);
                break;
            case "attack_water":
                image = attack_water.get(spriteNum);
                break;
            case "attack_wind":
                image = attack_wind.get(spriteNum);
                break;
            default:
                break;
        }

        if (lastDirection != null && lastDirection == "left" && direction != "right" && direction != "left") {
            offsetX = getImageWidth();
            offsetWidth = (getImageWidth() * 2) * -1;
        }

        g2.drawImage(image, screenX + offsetX, screenY, getImageWidth() + offsetWidth, getImageHeight(), null);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

}
