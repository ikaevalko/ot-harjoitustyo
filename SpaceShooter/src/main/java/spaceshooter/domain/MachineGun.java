package spaceshooter.domain;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class MachineGun extends Shot {
    
    private int disablingTimer;
    
    public MachineGun(Point2D direction) {
        
        super(new Point2D[] {new Point2D(-1, -1), new Point2D(1, -1), 
                             new Point2D(1, -1), new Point2D(1, 1), 
                             new Point2D(1, 1), new Point2D(-1, 1), 
                             new Point2D(-1, 1), new Point2D(-1, -1)}, 
                Color.ORANGE, direction, 5.0, 7, 8);
        
        this.disablingTimer = 100;
        randomizeDirection();
    }
    
    private void randomizeDirection() {
        
        int angle = -15 + new Random().nextInt(31);
        double rotation = Math.atan2(getDirectionX(), getDirectionY()) * -180 / Math.PI;
        
        setDirection(new Point2D(Math.cos(Math.toRadians(rotation + 90 + angle)), Math.sin(Math.toRadians(rotation + 90 + angle))));
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