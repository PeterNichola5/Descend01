package main;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {

        JFrame gameWindow = new JFrame();
        GameMotor mainGameMotor = new GameMotor();

        gameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.add(mainGameMotor);
        gameWindow.setTitle("Descend");
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        mainGameMotor.startGameThread();
    }
    
}
