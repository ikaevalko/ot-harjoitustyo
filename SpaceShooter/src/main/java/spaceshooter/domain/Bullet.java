package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Bullet extends Shot {
    
    private int disablingTimer;
    
    public Bullet(Point2D direction) {
        
        super(new Point2D[] {new Point2D(-1, -1), new Point2D(1, -1), 
                             new Point2D(1, 1), new Point2D(-1, 1), 
                             new Point2D(-1, 1), new Point2D(-1, -1)}, 
                Color.ORANGE, direction, 5.0, 1, 20);
        
        this.disablingTimer = 100;
    }
    
    @Override
    public void update() {
        
        super.update();
        
        if (disablingTimer <= 0) {
            
            graphics.setVisible(false);
            disabled = true;
        }
        
        disablingTimer--;
        
        graphics.setTranslateX(graphics.getTranslateX() + directionX * speed);
        graphics.setTranslateY(graphics.getTranslateY() + directionY * speed);
        
        checkCollision();
    }
}