package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ScatterShot extends Shot {
    
    private int disablingTimer;
    private Bullet[] bullets;
    private boolean trigger = true;
    
    public ScatterShot(Point2D direction) {
        
        super(null, Color.BLACK, direction, 0.0, 0, 30);
        
        this.disablingTimer = 100;
        this.bullets = new Bullet[3];
    }
    
    @Override
    public void update() {
        
        if (getDisabled()) {
            return;
        }
        
        if (trigger) {
            
            shoot();
            trigger = false;
        }
        
        if (disablingTimer <= 0) {
            
            for (Bullet b : bullets) {
                
                b.setDisabled(true);
            }
            
            setDisabled(true);
        }
        
        disablingTimer--;
        
        for (Bullet b : bullets) {
            
            b.update();
        }
    }
    
    private void shoot() {
        
        double rotation = Math.atan2(getDirectionX(), getDirectionY()) * -180 / Math.PI;
        
        bullets[0] = new Bullet(new Point2D(Math.cos(Math.toRadians(rotation + 105)), Math.sin(Math.toRadians(rotation + 105))));
        bullets[1] = new Bullet(new Point2D(Math.cos(Math.toRadians(rotation + 75)), Math.sin(Math.toRadians(rotation + 75))));
        bullets[2] = new Bullet(new Point2D(getDirectionX(), getDirectionY()));
        
        for (Bullet b : bullets) {
            
            GameSession.getInstance().addToScene(b.getGraphics());
            b.setPosition(getPositionX(), getPositionY());
        }
    }
}