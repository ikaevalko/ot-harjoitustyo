package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import java.util.Random;

public class BasicEnemy extends Enemy {
    
    private boolean rotationDirection;
    
    public BasicEnemy() {
        
        super(new Point2D[] {new Point2D(16, 0), new Point2D(8, Math.sqrt(3) * 0.5 * 16), 
                             new Point2D(8, Math.sqrt(3) * 0.5 * 16), new Point2D(-8, Math.sqrt(3) * 0.5 * 16), 
                             new Point2D(-8, Math.sqrt(3) * 0.5 * 16), new Point2D(-16, 0), 
                             new Point2D(-16, 0), new Point2D(-8, Math.sqrt(3) * 0.5 * -16), 
                             new Point2D(-8, Math.sqrt(3) * 0.5 * -16), new Point2D(8, Math.sqrt(3) * 0.5 * -16), 
                             new Point2D(8, Math.sqrt(3) * 0.5 * -16), new Point2D(16, 0) ,
                             new Point2D(0, 0), new Point2D(0, 0)}, 
                Color.RED, 3.2, 60);

        this.rotationDirection = new Random().nextBoolean();
    }
    
    @Override
    public void update() {
        
        super.update();
        
        if (rotationDirection) {
            
            setRotation(getRotation() + 6);
            
        } else {
            
            setRotation(getRotation() - 6);
        }
        
        moveTowardsPlayer();
        checkCollision();
    }
}