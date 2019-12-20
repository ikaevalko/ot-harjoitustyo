package spaceshooter.domain;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * The Shot class provides the base functionality for all objects that 
 * the player can shoot.
 */
public abstract class Shot extends GameObject {
    
    private double speed;
    private int damage;
    private int delay;
    private double directionX;
    private double directionY;
    private boolean disabled;
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
    
    /**
     * Subclasses implement their specific update methods.
     */
    public abstract void update();
    
    /**
     * Checks collision with all current enemies in the scene. 
     * Damages an enemy and disables this object upon collision.
     */
    protected void checkCollision() {
        
        for (Enemy enemy : enemies) {
            
            if (getGraphics().getBoundsInParent().intersects(enemy.getGraphics().getBoundsInParent())) {
                
                enemy.damage(damage);
                setDisabled(true);
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
    
    public void setDisabled(boolean disabled) {
        
        getGraphics().setVisible(false);
        this.disabled = disabled;
    }
    
    public void setDirection(Point2D direction) {
        
        this.directionX = direction.getX();
        this.directionY = direction.getY();
    }
}