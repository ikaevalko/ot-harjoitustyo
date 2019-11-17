package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Player extends GameObject {

    private double accelerationX;
    private double accelerationY;
    private double speed;
    private int condition;
    
    public Player() {
        
        super(new Point2D[] {new Point2D(0, -8), new Point2D(8, 8), 
                             new Point2D(0, -8), new Point2D(-8, 8)}, 
                Color.LIME);
        
        this.speed = 0.75;
        this.condition = 3;
    }
    
    public void move(double x, double y) {
        
        accelerationX += x;
        accelerationY += y;
        accelerationX *= speed;
        accelerationY *= speed;
        
        graphics.setTranslateX(graphics.getTranslateX() + accelerationX);
        graphics.setTranslateY(graphics.getTranslateY() + accelerationY);
    }
    
    public int getCondition() {
        
        return this.condition;
    }
}