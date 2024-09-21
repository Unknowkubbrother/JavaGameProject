package core;

import core.Entity.Entity;

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

}
