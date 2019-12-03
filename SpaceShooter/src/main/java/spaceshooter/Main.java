package spaceshooter;

import spaceshooter.ui.SpaceShooterUi;

public class Main {
    
    public static void main(String[] args) {
        
        System.setProperty("quantum.multithreaded", "false");
        SpaceShooterUi.main(args);
    }
}