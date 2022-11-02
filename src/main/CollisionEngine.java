package main;

import entity.Entity;
import entity.Entity.HorizontalMotionDirection;
import entity.Entity.VerticalMotionDirection;

public class CollisionEngine {

    GameMotor gM;

    int hitBoxLeftX;
    int hitBoxRightX;
    int hitBoxTopY;
    int hitBoxBottomY;
    
    int hitBoxLeftCol;
    int hitBoxRightCol;
    int hitBoxTopRow;
    int hitBoxBottomRow;

    int entityXCenter;
    int entityYCenter;
    int entityCenterCol; 
    int entityCenterRow;

    int tileChecking1;
    int tileChecking2;


    public CollisionEngine(GameMotor gM) {
        this.gM = gM;
    }

    private void checkh(Entity entity) {
        if(entity.getXDirection() == HorizontalMotionDirection.LEFT || entity.getXDirection() == HorizontalMotionDirection.DECEL && entity.getVelX() < 0) {

            hitBoxLeftCol = (hitBoxLeftX + entity.getVelX())/Constants.DISPLAY_TILE_SIZE + 1;
            tileChecking1 = gM.tileM.getMapTileNum(hitBoxLeftCol, hitBoxTopRow);
            tileChecking2 = gM.tileM.getMapTileNum(hitBoxLeftCol, hitBoxBottomRow);

            if(gM.tileM.getTile(tileChecking1).isCollision() || gM.tileM.getTile(tileChecking2).isCollision()) {
                entity.setEntityX(entityCenterCol * Constants.DISPLAY_TILE_SIZE);
                entity.setHorizontalCollision(true);
            }
        } else if(entity.getXDirection() == HorizontalMotionDirection.RIGHT || entity.getXDirection() == HorizontalMotionDirection.DECEL && entity.getVelX() > 0) {
            
            hitBoxRightCol = (hitBoxRightX + entity.getVelX())/Constants.DISPLAY_TILE_SIZE + 1;
            tileChecking1 = gM.tileM.getMapTileNum(hitBoxRightCol, hitBoxTopRow);
            tileChecking2 = gM.tileM.getMapTileNum(hitBoxRightCol, hitBoxBottomRow);

            if(gM.tileM.getTile(tileChecking1).isCollision() || gM.tileM.getTile(tileChecking2).isCollision()) {
                entity.setEntityX(entityCenterCol * Constants.DISPLAY_TILE_SIZE);
                entity.setHorizontalCollision(true);
            }
        }

    }

    public void checkTiles(Entity entity) {
        hitBoxLeftX = entity.getEntityX() + entity.getHitBox().x;
        hitBoxRightX = entity.getEntityX() + entity.getHitBox().x + entity.getHitBox().width;
        hitBoxTopY = entity.getEntityY() + entity.getHitBox().y;
        hitBoxBottomY = entity.getEntityY() + entity.getHitBox().y + entity.getHitBox().height;
        
        hitBoxLeftCol = hitBoxLeftX / Constants.DISPLAY_TILE_SIZE + 1;
        hitBoxRightCol = hitBoxRightX / Constants.DISPLAY_TILE_SIZE + 1;
        hitBoxTopRow = hitBoxTopY / Constants.DISPLAY_TILE_SIZE + 1;
        hitBoxBottomRow = hitBoxBottomY / Constants.DISPLAY_TILE_SIZE + 1;

        entityXCenter = hitBoxLeftX + entity.getHitBox().width/2;
        entityYCenter = hitBoxTopY + entity.getHitBox().height/2;
        entityCenterCol = entityXCenter / Constants.DISPLAY_TILE_SIZE;
        entityCenterRow = entityYCenter / Constants.DISPLAY_TILE_SIZE;

        checkh(entity);

        hitBoxLeftCol = hitBoxLeftX / Constants.DISPLAY_TILE_SIZE + 1;
        hitBoxRightCol = hitBoxRightX / Constants.DISPLAY_TILE_SIZE + 1;

        if(entity.getYDirection() == VerticalMotionDirection.FALLING ) {
            hitBoxBottomRow = (hitBoxBottomY + entity.getVelY())/Constants.DISPLAY_TILE_SIZE + 1;
            tileChecking1 = gM.tileM.getMapTileNum((hitBoxLeftCol), hitBoxBottomRow);
            tileChecking2 = gM.tileM.getMapTileNum(hitBoxRightCol, hitBoxBottomRow);

            if(gM.tileM.getTile(tileChecking1).isCollision() || gM.tileM.getTile(tileChecking2).isCollision()) {
                entity.setEntityY(entityCenterRow * Constants.DISPLAY_TILE_SIZE);
                entity.setYDirection(Entity.VerticalMotionDirection.GROUNDED);
            }
        }
        else if(entity.getYDirection() == VerticalMotionDirection.GROUNDED ) {
            hitBoxBottomRow = (hitBoxBottomY + entity.getVelY())/Constants.DISPLAY_TILE_SIZE + 1;
            tileChecking1 = gM.tileM.getMapTileNum((hitBoxLeftCol), hitBoxBottomRow);
            tileChecking2 = gM.tileM.getMapTileNum(hitBoxRightCol, hitBoxBottomRow);

            if(gM.tileM.getTile(tileChecking1).isCollision() || gM.tileM.getTile(tileChecking2).isCollision()) {
                entity.setEntityY(entityCenterRow * Constants.DISPLAY_TILE_SIZE);
            } else {
                entity.setYDirection(Entity.VerticalMotionDirection.FALLING);
            }
        }
    }
}
