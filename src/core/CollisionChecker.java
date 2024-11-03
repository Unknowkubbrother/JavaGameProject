package core;

import core.Entity.Entity;
import java.util.ArrayList;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkMap(Entity entity) {
        try {
            int entityLeftWorldX = entity.worldX + entity.solidArea.x;
            int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
            int entityTopWorldY = entity.worldY + entity.solidArea.y;
            int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

            int entityLeftCol = entityLeftWorldX / gp.titleSize;
            int entityRightCol = entityRightWorldX / gp.titleSize;
            int entityTopRow = entityTopWorldY / gp.titleSize;
            int entityBottomRow = entityBottomWorldY / gp.titleSize;

            Texture titleNum1, titleNum2;
            if (entity.direction == "up") {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
                titleNum1 = Texture.values()[gp.map.MapContenet[entityTopRow][entityLeftCol]];
                titleNum2 = Texture.values()[gp.map.MapContenet[entityTopRow][entityRightCol]];
                if (titleNum1.isCollision() || titleNum2.isCollision()) {
                    entity.collisionOn = true;
                }
            }
            if (entity.direction == "down") {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
                titleNum1 = Texture.values()[gp.map.MapContenet[entityBottomRow][entityLeftCol]];
                titleNum2 = Texture.values()[gp.map.MapContenet[entityBottomRow][entityRightCol]];
                if (titleNum1.isCollision() || titleNum2.isCollision()) {
                    entity.collisionOn = true;
                }
            }
            if (entity.direction == "left") {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
                titleNum1 = Texture.values()[gp.map.MapContenet[entityTopRow][entityLeftCol]];
                titleNum2 = Texture.values()[gp.map.MapContenet[entityBottomRow][entityLeftCol]];
                if (titleNum1.isCollision() || titleNum2.isCollision()) {
                    entity.collisionOn = true;
                }
            }
            if (entity.direction == "right") {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
                titleNum1 = Texture.values()[gp.map.MapContenet[entityTopRow][entityRightCol]];
                titleNum2 = Texture.values()[gp.map.MapContenet[entityBottomRow][entityRightCol]];
                if (titleNum1.isCollision() || titleNum2.isCollision()) {
                    entity.collisionOn = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            entity.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = -1;

        for (int i = 0; i < gp.objects.size(); i++) {
            if (gp.objects.get(i) != null && gp.objects.get(i).isShow()
                    && gp.objects.get(i).getMapId() == gp.player.getStateMap()) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gp.objects.get(i).getSolidArea().x = gp.objects.get(i).getWorldX() + gp.objects.get(i).getSolidArea().x;
                gp.objects.get(i).getSolidArea().y = gp.objects.get(i).getWorldY() + gp.objects.get(i).getSolidArea().y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y = entity.solidArea.y - entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y = entity.solidArea.y + entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x = entity.solidArea.x - entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x = entity.solidArea.x + entity.speed;
                        break;
                    default:
                        break;
                }

                if (entity.solidArea.intersects(gp.objects.get(i).getSolidArea())) {
                    if (gp.objects.get(i).isCollision()) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objects.get(i).getSolidArea().x = gp.objects.get(i).getSolidAreaDefaultX();
                gp.objects.get(i).getSolidArea().y = gp.objects.get(i).getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, ArrayList<Entity> target) {
        int index = -1;

        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the target entity's solid area position
                target.get(i).solidArea.x = target.get(i).worldX + target.get(i).solidArea.x;
                target.get(i).solidArea.y = target.get(i).worldY + target.get(i).solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y = entity.solidArea.y - entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y = entity.solidArea.y + entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x = entity.solidArea.x - entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x = entity.solidArea.x + entity.speed;
                        break;
                    default:
                        break;
                }

                if (entity.solidArea.intersects(target.get(i).solidArea)) {
                    if (target.get(i) != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.get(i).solidArea.x = target.get(i).solidAreaDefaultX;
                target.get(i).solidArea.y = target.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }

    public ArrayList<Integer> checkPlayerAttackMonster(Entity entity, ArrayList<Entity> target) {
        ArrayList<Integer> monsterHit = new ArrayList<>();
    
        for (int i = 0; i < target.size(); i++) {
            Entity monster = target.get(i);
            if (monster != null) {
                // เก็บค่าตำแหน่งเดิมไว้
                int originalAttackX = entity.attackArea.x;
                int originalAttackY = entity.attackArea.y;
                int originalSolidX = monster.solidArea.x;
                int originalSolidY = monster.solidArea.y;
    
                // คำนวณตำแหน่งใหม่
                entity.attackArea.x += entity.worldX;
                entity.attackArea.y += entity.worldY;
                monster.solidArea.x += monster.worldX;
                monster.solidArea.y += monster.worldY;
    
                // ตรวจสอบการชนกัน
                if (entity.attackArea.intersects(monster.solidArea)) {
                    if (monster != entity) {
                        monsterHit.add(i);
                    }
                }
    
                // คืนค่าตำแหน่งเดิม
                entity.attackArea.x = originalAttackX;
                entity.attackArea.y = originalAttackY;
                monster.solidArea.x = originalSolidX;
                monster.solidArea.y = originalSolidY;
            }
        }
        return monsterHit;
    }
    
    public boolean checkPlayer(Entity entity) {
        boolean collision = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            collision = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return collision;
    }
}
