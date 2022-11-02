package entity;

import main.Constants;
import main.GameMotor;
import main.Input;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import static main.Constants.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

    Input input;
    GameMotor gM;

    BufferedImage playerImage;

    int leftMovementVel;
    int rightMovementVel;
    int jumpPhase;
    int jumpSequence;

    int gravityController;



    public Player(Input input, GameMotor gM) {
       
        this.input = input;
        this.gM = gM;

        yDirection = VerticalMotionDirection.GROUNDED;
        xDirection = HorizontalMotionDirection.STILL;

        leftMovementVel = 0;
        rightMovementVel = 0;
        gravityController = 0;
        setHitBox(1, 1, 46, 46);
        setEntityX(Constants.DISPLAY_TILE_SIZE);
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/Res/player/player.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void checkDirection() {
       
        if(input.isLeftPressed() && input.isRightPressed() || !input.isLeftPressed() && !input.isRightPressed()) {
            if(velX != 0) {
                xDirection = HorizontalMotionDirection.DECEL;
            } else {
                xDirection = HorizontalMotionDirection.STILL;
            } 
        }
        else if(input.isLeftPressed()) {
            xDirection = HorizontalMotionDirection.LEFT;
        }
        else if(input.isRightPressed()) {
            xDirection = HorizontalMotionDirection.RIGHT;
        }

    }

    private void playerMovementIfDecel() {
        if(leftMovementVel < 0) {
            if(leftMovementVel + 4 <= 0) {
                leftMovementVel += 4;
            } else {
                leftMovementVel = 0;
            }
         }
         else if(rightMovementVel > 0) {
            if(rightMovementVel - 4 >= 0) {
                rightMovementVel -= 4;
            } else {
                rightMovementVel = 0;
            }
         }
    }

    public void resetXMovement() {
        xDirection = HorizontalMotionDirection.STILL;
        leftMovementVel = 0;
        rightMovementVel = 0;
        velX = 0;
    }

    public void initiateHMovement() {

        if(xDirection == HorizontalMotionDirection.DECEL) {
           playerMovementIfDecel();
        }
        else if(xDirection != HorizontalMotionDirection.RIGHT && rightMovementVel > 0) {
            rightMovementVel -= 4;
        }
        else if(xDirection == HorizontalMotionDirection.LEFT  && leftMovementVel > -12) {
            leftMovementVel -= 2;
        }
        else if(xDirection != HorizontalMotionDirection.LEFT && leftMovementVel < 0) {
            leftMovementVel += 4;
        }
        else if(xDirection == HorizontalMotionDirection.RIGHT && rightMovementVel < 12) {
            rightMovementVel += 2;
        }

        setVelX(rightMovementVel + leftMovementVel);
    }
    
    public void playerMovement() {
        setEntityX(getEntityX() + getVelX());
    }


    private void jumpSimplifier() {
        switch(jumpPhase) {
        case 4:
            if(jumpSequence < 2) {
                setVelY(-1);
                jumpSequence++;
            }else {
                setVelY(-1);
                jumpSequence = 0;
                jumpPhase++;
            }
            break;
        case 5:
            if(jumpSequence < 1) {
                setVelY(0);
                jumpSequence++;
            } else {
                setVelY(0);
                jumpSequence = 0;
                jumpPhase++;
            }
            break;
        default:
            break;
        }
    }

    public void jump() {

        switch(jumpPhase) {
        case 0:
            setVelY(-16);
            jumpPhase++;
            break;
        case 1, 2:
            if (jumpSequence < 4 && jumpSequence > 0) {
                setVelY(-10);
                jumpSequence++;
                break;
            }
            if(jumpSequence == 4) {
                setVelY(-6);
                jumpSequence = 0;
                jumpPhase++;
                break;
            }
            setVelY(-7);
            jumpSequence++;
            break;
        case 3:
            switch(jumpSequence) {
            case 0:
                setVelY(-3);
                jumpSequence++;
                break;
            case 1:
                setVelY(-9);
                jumpSequence++;
                break;
            default:
                setVelY(-10);
                jumpSequence = 0;
                jumpPhase++;
                break;
            }
            break;
        case 4:
            jumpSimplifier();
            break;
        case 5:
            jumpSimplifier();
            break; 
        default:
            yDirection = VerticalMotionDirection.FALLING;
            break;
        }
    } 

    public void checkVertMovement() {

        if(input.isSpacePressed() && yDirection == VerticalMotionDirection.GROUNDED) {
            jumpPhase = 0;
            jumpSequence = 0;
            yDirection = VerticalMotionDirection.JUMPING;
        }
    }

    public void vertMovement() {
        setEntityY(getEntityY() + getVelY());
    }

    //may change to ArrayList
    public void initiateVertMovement() {
        switch(yDirection) {
        case FALLING:
        switch(gravityController) {
            case 0, 1:

                setVelY(3);
                gravityController++;
                break;
            case 2, 3:
                setVelY(5);
                gravityController++;
                break;
            case 4:
                setVelY(8);
                gravityController++;
                break;
            case 5:
                setVelY(13);
                gravityController++;
                break;
            case 6:
                setVelY(17);
                gravityController--;
                break;
            default:
                break;
            }
            break;
        case JUMPING:
            jump();
            break;
        case GROUNDED:
            gravityController = 0;
            setVelY(0);
            break;
        default:
            break;
        }
    }

    public void update() {
        checkDirection();
        checkVertMovement();
        setHorizontalCollision(false);
        setVertCollision(false);
        initiateHMovement();
        initiateVertMovement();
        gM.getCollisionEngine().checkTiles(this);

        if(!horizontalCollision) {
            playerMovement();
        } else {
            resetXMovement();
        }
        if(yDirection != VerticalMotionDirection.GROUNDED) {
            vertMovement();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(playerImage, getEntityX(), getEntityY(), DISPLAY_TILE_SIZE, DISPLAY_TILE_SIZE, null);
        g2.setColor(Color.RED);
        g2.draw(hitBox);
    }

}
