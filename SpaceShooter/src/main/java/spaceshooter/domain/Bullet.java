package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Bullet extends Shot {
    
    private int disablingTimer;
    
    public Bullet(Point2D direction) {
        
        super(new Point2D[] {new Point2D(-1, -1), new Point2D(1, -1), 
                             new Point2D(1, -1), new Point2D(1, 1), 
                             new Point2D(1, 1), new Point2D(-1, 1), 
                             new Point2D(-1, 1), new Point2D(-1, -1)}, 
                Color.ORANGE, direction, 5.0, 10, 25);
        
        this.disablingTimer = 100;
    }
    
    @Override
    public void update() {
        
        if (getDisabled()) {
            return;
        }
        
        if (disablingTimer <= 0) {
            
            setDisabled(true);
        }
        
        disablingTimer--;
        
        setPosition(getPositionX() + getDirectionX() * getSpeed(), getPositionY() + getDirectionY() * getSpeed());
        
        checkCollision();
    }
}