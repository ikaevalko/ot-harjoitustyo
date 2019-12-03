package spaceshooter.domain;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Shot extends GameObject {
    
    protected double speed;
    protected int damage;
    protected int delay;
    protected double directionX;
    protected double directionY;
    protected boolean disabled;
    private ArrayList<Enemy> enemies;
    
    public Shot(Point2D[] lines, Color color, Point2D direction, double speed, int damage, int delay) {
        
        super(lines, color);
        
        try {
            this.enemies = GameSession.getInstance().getEnemies();
            
        } catch (Exception e) {
            
            System.out.println("Shot: Could not find GameSession");
        }
        
        this.directionX = direction.getX();
        this.directionY = direction.getY();
        this.speed = speed;
        this.damage = damage;
        this.delay = delay;
    }
    
    public void update() {
        
        if (disabled) {
            return;
        }
    }
    
    protected void checkCollision() {
        
        for (Enemy enemy : enemies) {
            
            if (this.graphics.getBoundsInParent().intersects(enemy.getGraphics().getBoundsInParent())) {
                
                enemy.takeDamage(damage);
                graphics.setVisible(false);
                disabled = true;
            }
        }
    }
    
    public double getSpeed() {
        
        return this.speed;
    }
    
    public int getDamage() {
        
        return this.damage;
    }
    
    public int getDelay() {
        
        return this.delay;
    }
    
    public double getDirectionX() {
        
        return this.directionX;
    }
    
    public double getDirectionY() {
        
        return this.directionY;
    }
    
    public boolean getDisabled() {
        
        return this.disabled;
    }
    
    public ArrayList<Enemy> getEnemies() {
        
        return this.enemies;
    }
}