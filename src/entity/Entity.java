package entity;
import java.awt.Rectangle;

public class Entity {

    // Enums for horizontal and vertical motion direction
    // These will be used to establish how the entity should move

    public enum HorizontalMotionDirection {
        LEFT, RIGHT, SLOW, STILL;
    }

    public enum VerticalMotionDirection {
        JUMPING, GROUNDED, FALLING;
    }

    // Initializing
    
    Rectangle hitBox  = new Rectangle();
    HorizontalMotionDirection xDirection;
    VerticalMotionDirection yDirection;


    int entityX;
    int entityY;
    int velX;
    int velY;

    boolean horizontalCollision = false;
    boolean verticalCollision = false;


    // Getters and Setters
    
    public boolean isHorizontalCollision() {
        return horizontalCollision;
    }
    public void setHorizontalCollision(boolean horizontalCollision) {
        this.horizontalCollision = horizontalCollision;
    }

    public boolean isVertCollision() {
        return verticalCollision;
    }
    public void setVertCollision(boolean verticalCollision) {
        this.verticalCollision = verticalCollision;
    }

    public HorizontalMotionDirection getXDirection() {
        return xDirection;
    }
    public void setXDirection(HorizontalMotionDirection xDirection) {
        this.xDirection = xDirection;
    }

    public VerticalMotionDirection getYDirection() {
        return yDirection;
    }
    public void setYDirection(VerticalMotionDirection yDirection) {
        this.yDirection = yDirection;
    }

    public int getVelX() {
        return velX;
    }
    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }
    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getEntityX() {
        return entityX;
    }
    public void setEntityX(int entityX) {
        this.entityX = entityX;
    }

    public int getEntityY() {
        return entityY;
    }
    public void setEntityY(int entityY) {
        this.entityY = entityY;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
    public void setHitBox(int x, int y, int width, int height) {
        this.hitBox.x = x;
        this.hitBox.y = y;
        this.hitBox.width = width;
        this.hitBox.height = height;
    }
    public void setHitBox(int x, int y) {
        this.hitBox.x = x;
        this.hitBox.y = y;
    }    
    public void setHitBoxX(int x) {
        this.hitBox.x = x;
    }    
    public void setHitBoxY(int y) {
        this.hitBox.y = y;
    }
}


