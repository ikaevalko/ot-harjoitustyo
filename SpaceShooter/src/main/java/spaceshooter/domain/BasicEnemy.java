package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import java.util.Random;

public class BasicEnemy extends Enemy {
    
    private int rotationDirection;
    
    // Helper variable for shape construction
    private static final double P = Math.sqrt(3) * 0.5 * 16;
    
    public BasicEnemy() {
        
        super(new Point2D[] {new Point2D(16, 0), new Point2D(8, P), 
                             new Point2D(8, P), new Point2D(-8, P), 
                             new Point2D(-8, P), new Point2D(-16, 0), 
                             new Point2D(-16, 0), new Point2D(-8, -P), 
                             new Point2D(-8, -P), new Point2D(8, -P), 
                             new Point2D(8, -P), new Point2D(16, 0) ,
                             new Point2D(0, 0), new Point2D(0, 0)}, 
                Color.RED, 0.78, 60);

        boolean rand = new Random().nextBoolean();
        
        if (rand) {
            
            rotationDirection = 6;
            
        } else {
            
            rotationDirection = -6;
        }
    }
    
    @Override
    public void update() {
        
        super.update();
        
        setRotation(getRotation() + rotationDirection);
        
        Point2D direction = normalizeDirection(getDirectionToPlayer());
        move(direction.getX(), direction.getY());
        checkCollision();
    }
}