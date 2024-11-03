package core.Entity;

import core.GamePanel;
import java.awt.Graphics;

public abstract class Monster extends Entity implements Runnable{
    
    // Thread
    protected Thread MonsterThread;
    protected int ThreadDelay;

    protected int health = 100;
    protected int attackDamage;
    protected int rebound;
    protected int element;

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
    }

    abstract public void setDefaultValues(int x, int y);
    abstract protected void loadAnimation();
    abstract public void setAction();
    abstract public void update();
    abstract public void draw(Graphics g2);
    abstract public void AttacktoPlayer();


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
