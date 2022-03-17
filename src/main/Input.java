package main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import static main.Constants.*;

public class Input {

    Action leftAction;
    Action rightAction;
    Action rightReleased;
    Action leftReleased;
    Action jumpAction;
    Action jumpRelease;

    long time;
    long leftTimePressed;
    long leftTimeReleased;
    long rightTimePressed;
    long rightTimeReleased;

    boolean leftPressed;
    boolean rightPressed;
    boolean spacePressed;
    boolean leftTapped;
    boolean rightTapped;

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    GameMotor gM;

    Input(GameMotor gM) {

        this.gM = gM;
        leftAction = new LeftAction();
        rightAction = new RightAction();
        rightReleased = new RightRelease();
        leftReleased = new LeftRelease();
        jumpAction = new JumpAction();
        jumpRelease = new JumpRelease();

        leftPressed = false;
        rightPressed = false;
        spacePressed = false;
        leftTapped = false;
        rightTapped = false;

        time = System.nanoTime();
        leftTimePressed = 0;
        leftTimeReleased = 0;
        rightTimePressed = 0;
        rightTimeReleased = 0;

        gM.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        gM.getActionMap().put("leftAction", leftAction);
        gM.getInputMap().put(KeyStroke.getKeyStroke("released A"), LEFT_RELEASE_KEYBIND);
        gM.getActionMap().put(LEFT_RELEASE_KEYBIND, leftReleased);
        
        gM.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        gM.getActionMap().put("rightAction", rightAction);
        gM.getInputMap().put(KeyStroke.getKeyStroke("released D"), RIGHT_RELEASE_KEYBIND);
        gM.getActionMap().put(RIGHT_RELEASE_KEYBIND, rightReleased);

        gM.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "jumpAction");
        gM.getActionMap().put("jumpAction", jumpAction);
        gM.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "jumpRelease");
        gM.getActionMap().put("jumpRelease", jumpRelease);
    }

    public boolean getLeftTapped() {
        return leftTapped;
    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            leftTimePressed = time;
            leftPressed = true;  
        }
    }
 
    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            rightPressed = true; 
        }
    }

    public class RightRelease extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            rightPressed = false;  
        }
    }

    public class LeftRelease extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            leftTimeReleased = time;
            if(leftTimeReleased - leftTimePressed < Constants.NANOSECONDS_TO_SECONDS/5) {
                leftTapped = true;
            }
            leftPressed = false; 
        }
    }

    public class JumpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            spacePressed = true;
        }
    }

    public class JumpRelease extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            spacePressed = false;
        }
    }
}
