package core.Entity;

import core.GamePanel;
import core.KeyHandler;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import core.MAP.MAP1.*;
import core.Objects;

class Element {
    public int damage;
    public int manaCost;
    BufferedImage image;

    public Element(String path, int damage, int manaCost) {
        this.image = Entity.loadSprite(path);
        this.damage = damage;
        this.manaCost = manaCost;
    }

}

// Element Enums
enum ElementEnums {
    PUNCH(0),
    FIRE(1),
    WATER(2),
    WIND(3);

    public final int elementId;
    private static final Map<Integer, Element> dict = new HashMap<>();

    private ElementEnums(int elementId) {
        this.elementId = elementId;
    }

    public static void loadElements() {
        long startTime = System.currentTimeMillis();
        System.err.println("Loading Element...");
        dict.put(0, new Element("player/elements/punch.png", 0, 0));
        dict.put(1, new Element("player/elements/fire.png", 30, 2));
        dict.put(2, new Element("player/elements/water.png", 30, 2));
        dict.put(3, new Element("player/elements/wind.png", 30, 2));
        System.out.println("[Element]: Element loaded! (" + (System.currentTimeMillis() - startTime) + "ms)");

    }

    public int getElementId() {
        return elementId;
    }

    public static BufferedImage getImageElementId(int id) {
        return dict.get(id).image;
    }

    public static int getDamageElementId(int id) {
        return dict.get(id).damage;
    }

    public static int getManaCostElementId(int id) {
        return dict.get(id).manaCost;
    }

}

// Player State
class PlayerState {
    protected int[] map = { 0, 0 };
    protected int currentElement = 0;
    protected int health = 100;
    protected int armor = 20;
    protected int mana = 100;
    protected boolean isAttacking = false;
}


public class Player extends Entity {

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
        speed = 5;
        direction = "idle";
        setImageWidth(256);
        setImageHeight(256);

        solidArea.x = 102;
        solidArea.y = 65;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.solidArea.width = 51;
        this.solidArea.height = 64;

        attackArea.width = 51;
        attackArea.height = 64;
        attackArea.x = 102;
        attackArea.y = 65;
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

    //Player State
    public int getHealth() {
        return player_state.health;
    }

    public void setHealth(int health) {
        if (health > 100) {
            health = 100;
        }
        if (health < 0) {
            health = 0;
        }
        player_state.health = health;
    }

    public void playerAttacked(int damage) {
        if (getArmor() > 0) {
            setArmor(getArmor() - damage);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    public int getArmor() {
        return player_state.armor;
    }

    public void setArmor(int armor) {
        if (armor > 100) {
            armor = 100;
        }
        if (armor < 0) {
            armor = 0;
        }
        player_state.armor = armor;
    }

    public int getMana() {
        return player_state.mana;
    }

    public void setMana(int mana) {
        if (mana > 100) {
            mana = 100;
        }
        if (mana < 0) {
            mana = 0;
        }
        player_state.mana = mana;
    }

    public int getCurrentManaCost() {
        return ElementEnums.getManaCostElementId(player_state.currentElement);
    }

    public boolean isDead() {
        return player_state.health <= 0;
    }

    public boolean isAttacking() {
        return player_state.isAttacking;
    }

    public void setAttacking(boolean isAttacking) {
        player_state.isAttacking = isAttacking;
    }

    public int[] getStateMap() {
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

    public void setMap(int parent, int child) {
        player_state.map[0] = parent;
        player_state.map[1] = child;
    }

    // Check player state map
    public void checkPlayerStateMap() {
        if (gp.gameState != gp.playerState) {
            return;
        }

        if (getStateMap()[0] == 0){
            if (getStateMap()[1] == 0) {
                if (getEntityCoords().get("x") >= 910
                        && getEntityCoords().get("y") >= 340
                        && getEntityCoords().get("y") <= 450) {
                        gp.map.timerMap.stop();
                        setMap(getStateMap()[0], 1);
                        gp.map = new M1_ST2(gp);
                        worldX = gp.titleSize * 3;
                        worldY = gp.titleSize * 5;
                }
            } 
            else if (getStateMap()[1] == 1) {
                if (getEntityCoords().get("x") >= 857
                        && getEntityCoords().get("y") >= 255
                        && getEntityCoords().get("y") <= 510) {
                        gp.map.timerMap.stop();
                        setMap(getStateMap()[0], 0);
                        gp.map = new M1_ST1(gp);
                        worldX = gp.titleSize * 3;
                        worldY = gp.titleSize * 5;
                }
            }
        }
    }

    public void pickUpObject(int index) {

        if (index != -1 && gp.objects.get(index).getMapId()[0] == getStateMap()[0]
                && gp.objects.get(index).getMapId()[1] == getStateMap()[1]
        ) {


            if (gp.objects.get(index).getObjectId() == 3) {
                speed += 10;
                System.out.println("You picked up a chest! on map: " + getStateMap());
                gp.objects.remove(index);
            } 
            // else if (gp.objects.get(index).getObjectId() == 4) {
            //     setMana(getMana() + 50);
            //     System.out.println("You picked up a mana! on map: " + getStateMap());
            //     gp.objects.remove(index);
            // }


            
        }

    }

    public void InteractNpc(int npcIdx) {
        if (npcIdx != -1) {
            System.out.println("Interacting with NPC");
        }
    }

    // END Player State

    public void ActionAttack() {
        spriteCounter++;
        if (spriteCounter > 2) {
            spriteCounter = 0;
            if (spriteNum == 3) {
                int OriginalX = attackArea.x;
                int OriginalY = attackArea.y;

                if (lastDirection != null) {
                    switch (lastDirection) {
                        case "up":
                            attackArea.y -= attackArea.height;
                            break;
                        case "down":
                            attackArea.y += attackArea.height;
                            break;
                        case "left":
                            attackArea.x -= attackArea.width;
                            break;
                        case "right":
                            attackArea.x += attackArea.width;
                            break;
                        default:
                            break;
                    }
                }

                ArrayList<Integer> monsterHit = gp.cChecker.checkPlayerAttackMonster(this, gp.monster);
                ArrayList<Integer> objectHit = gp.cChecker.checkPlayerAttackObject(this,gp.objects);
                damageObject(objectHit);
                damageMonster(monsterHit);
                setMana(getMana() - getCurrentManaCost());

                attackArea.x = OriginalX;
                attackArea.y = OriginalY;
            }

            spriteNum++;
        }
    }

    public void damageObject(ArrayList<Integer> objectIdx) {
        if (objectIdx.size() == 0) {
            System.out.println("No object hit");
            return;
        }

        for (int i = 0; i < objectIdx.size(); i++) {
            int idx = objectIdx.get(i);
            if (idx >= 0 && idx < gp.objects.size()) {
                Objects object = (Objects)gp.objects.get(idx);

                object.AttackedByPlayer(ElementEnums.getDamageElementId(getCurrentElement()));
                System.out.println("Object health: " + object.getHealth());
                
                if (object.ObjectisDead()) {
                    gp.objects.remove(idx);
                }
            }
        }
    }

    public void damageMonster(ArrayList<Integer> monsterIdx) {
        if (monsterIdx.size() == 0) {
            System.out.println("No monster hit");
            return;
        }
        for (int i = 0; i < monsterIdx.size(); i++) {
            int idx = monsterIdx.get(i);
            if (idx >= 0 && idx < gp.monster.size()) {
                Monster monster = (Monster) gp.monster.get(idx);

                if (monster.element == getCurrentElement()) {
                    monster.AttackedByPlayer(ElementEnums.getDamageElementId(getCurrentElement()));
                } else {
                    // gp.UiStatus.alertText = "Element not match and Attack power is One!";
                    monster.AttackedByPlayer(10);
                    // gp.UiStatus.cooldownAlert = 500/16;
                }

                if (monster.isDead()) {
                    gp.monster.remove(idx);
                }

                System.out.println("Monster health: " + monster.getHealth());
            }
        }
    }

    @Override
    public void setAction() {

    }

    public void restoreMana() {
        if (getMana() < 100) {
            setMana(getMana() + 1);
        }
    }

    @Override
    public void update() {
        if (gp.gameState != gp.playerState) {
            return;
        }

        // Check if player is dead
        if (isDead()) {
            gp.gameState = gp.gameOverState;
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

        // Check collision with monster
        gp.cChecker.checkEntity(this, gp.monster);

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
                } else if (getCurrentElement() == 3) {
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
                    attackArea.x = solidArea.x;
                    attackArea.y = solidArea.y;
                }
                break;
            case "attack_water":
                if (spriteNum >= attack_water.size()) {
                    spriteNum = 0;
                    setAttacking(false);
                    attackArea.x = solidArea.x;
                    attackArea.y = solidArea.y;
                }
                break;
            case "attack_wind":
                if (spriteNum >= attack_wind.size()) {
                    spriteNum = 0;
                    setAttacking(false);
                    attackArea.x = solidArea.x;
                    attackArea.y = solidArea.y;
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
    }

}
