package spaceshooter;

import spaceshooter.ui.SpaceShooterUi;

public class Main {
    
    /**
     * The main method launches a JavaFX application with the 
     * SpaceShooterUi class. It also disables multithreading to counter 
     * a bug related to the AnimationTimer class which causes the update 
     * rate of the game to break on some operating systems.
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        System.setProperty("quantum.multithreaded", "false");
        SpaceShooterUi.main(args);
    }
}