package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class DashEnemy extends Enemy {
    
    private int dashTimer = 35;
    private int checkingTimer = -1;
    private boolean dashing = false;
    private Point2D dashDirection;
    
    public DashEnemy() {
        
        super(new Point2D[] {new Point2D(0, -12), new Point2D(8, 12), 
                             new Point2D(8, 12), new Point2D(-8, 12), 
                             new Point2D(-8, 12), new Point2D(0, -12)}, 
                Color.YELLOW, 0.75, 30);
        
        setSlowable(false);
    }
    
    @Override
    public void update() {
        
        super.update();
        
        updateCheckingTimer();
        updateDashing();
        checkCollision();
    }
    
    private void updateCheckingTimer() {
        
        if (checkingTimer > 0) {
            
            if (!dashing) {
                
                checkingTimer--;
            }
            
        } else if (!dashing) {
            
            if (isInsideBounds() && getDistanceToPlayer() < 250) {
                
                dashDirection = getForwardDirection();
                setSpeed(getOriginalSpeed() * 1.3);
                checkingTimer = 60;
                dashing = true;
                
            } else {
                
                checkingTimer = 10;
            }
        }
    }
    
    private void updateDashing() {
        
        if (dashing) {
            
            if (dashTimer > 0) {
                
                if (dashTimer > 30) {
                    
                    move(0, 0);
                    
                } else {
                    
                    move(dashDirection.getX(), dashDirection.getY());
                }
                
                dashTimer--;
                
            } else {
                
                resetSpeed();
                dashTimer = 35;
                dashing = false;
            }
            
        } else {
            
            Point2D direction = normalizeDirection(getDirectionToPlayer());
            move(direction.getX(), direction.getY());
            lookAtPlayer();
        }
    }
    
    private Point2D getForwardDirection() {
        
        double x = Math.cos(Math.toRadians(getRotation() - 90));
        double y = Math.sin(Math.toRadians(getRotation() - 90));
        
        return new Point2D(x, y);
    }
}