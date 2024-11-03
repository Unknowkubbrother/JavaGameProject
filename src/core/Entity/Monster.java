package core.Entity;

import core.GamePanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

public abstract class Monster extends Entity implements Runnable{
    
    // Thread
    protected Thread MonsterThread;
    protected int ThreadDelay;

    // Status
    protected int health = 100;
    protected int attackDamage;
    protected int rebound;
    protected int element;

    // Animation
    protected ArrayList<BufferedImage> idle = new ArrayList<>();
    protected ArrayList<BufferedImage> attack = new ArrayList<>();
    protected ArrayList<BufferedImage> hit = new ArrayList<>();
    public int actionLockCounter = 0;
    public int DestinationActionLockCounter = 60;

    public Monster(GamePanel gp){
        super(gp);
    }


    public void stopMonsterThread() {
        MonsterThread = null;
    }
    
    public void startMonsterThread() {
        ThreadDelay = 1000 / 60;
        MonsterThread = new Thread(this);
        MonsterThread.start();
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
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

    public void AttackedByPlayer (int attackDamage) {
        direction = "hit";
        spriteNum = 0;
        spriteCounter = 0;
        setHealth(getHealth() - attackDamage);

        if (isDead()) {
            stopMonsterThread();
        }
    }

    abstract public void setDefaultValues(int x, int y);
    abstract protected void loadAnimation();

    @Override
    public void setAction(){
        
        actionLockCounter++;
        if (actionLockCounter == DestinationActionLockCounter) {
            Random rand = new Random();
            int n = rand.nextInt(2);
            if (n == 0) {
                direction = "idle";
            }
            if (n == 1) {
                direction = "walk";
            }

            actionLockCounter = 0;
        }

        if (direction.equals("walk")) {
            int playerX = gp.player.worldX;
            int playerY = gp.player.worldY;
        
            int diffX = playerX - worldX;
            int diffY = playerY - worldY;
        
            double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        
            double directionX = diffX / distance;
            double directionY = diffY / distance;
        
            worldX += directionX * speed;
            worldY += directionY * speed;
        
            if (Math.abs(directionX) > Math.abs(directionY)) {
                if (directionX > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            } else {
                if (directionY > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
            }
        
            lastDirection = direction;
            isMoving = true;
        }
    };


    @Override
    public void update(){
        if (gp.gameState != gp.playerState) {
            return;
        }

        setAction();

        collisionOn = false;
        gp.cChecker.checkMap(this);

        gp.cChecker.checkObject(this, false);

        if (gp.cChecker.checkPlayer(this) && direction != "hit") {
            direction = "attack";
            isMoving = false;
            AttacktoPlayer();
        }

        if (!collisionOn && isMoving) {
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
        } else {
            isMoving = false;
        }

        spriteCounter++;
        if (spriteCounter > 8) {
            spriteCounter = 0;
            spriteNum++;
        }

        switch (direction) {
            case "idle":
                if (spriteNum >= idle.size()) {
                    spriteNum = 0;
                }
                break;
            case "attack":
                if (spriteNum >= attack.size()) {
                    spriteNum = 0;
                }
                break;
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
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            case "down":
                if (spriteNum >= right.size()) {
                    spriteNum = 0;
                }
                break;
            case "hit":
                if (spriteNum >= hit.size()) {
                    spriteNum = 0;
                }
                break;
            default:
                break;
        }
    };

    private int countHit = 0;
    public void AttacktoPlayer(){
        countHit++;
        if (countHit > 30) {
            gp.player.playerAttacked(attackDamage);
            countHit = 0;
            if (worldX > gp.player.worldX) {
                gp.player.worldX -= rebound;
            } else {
                gp.player.worldX += rebound;
            }
            if (worldY > gp.player.worldY) {
                gp.player.worldY -= rebound;
            } else {
                gp.player.worldY += rebound;
            }
            direction = "idle";
        }
    };

    abstract public void draw(Graphics g2);


    @Override
    public void run() {
        while (MonsterThread != null) {
            update();
            try {
                Thread.sleep(ThreadDelay); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
