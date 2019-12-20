package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * The Enemy class provides the base functionality for all enemies.
 */
public abstract class Enemy extends GameObject {
    
    private Scene scene;
    private Player player;
    private double speed;
    private double originalSpeed;
    private int condition;
    private boolean destroyed;
    private double accelerationX;
    private double accelerationY;
    private int collisionTimer = -1;
    private int damageTimer = -1;
    private boolean takingDamage = false;
    private boolean enteredBounds = false;
    private boolean slowable = true;
    
    public Enemy(Point2D[] lines, Color color, double speed, int condition) {
        
        super(lines, color);
        
        try {
            this.scene = GameSession.getInstance().getScene();
            this.player = GameSession.getInstance().getPlayer();
            
        } catch (Exception e) {
            
            System.out.println("Enemy: Could not find GameSession");
        }
        
        this.speed = speed;
        this.originalSpeed = this.speed;
        this.condition = condition;
    }
    
    /**
     * Advances the logic of the Enemy.
     */
    public void update() {
        
        if (destroyed) {
            return;
        }
        
        updateDamageTimer();
        
        if (!enteredBounds && isInsideBounds()) {
            
            enteredBounds = true;
            
        } else if (enteredBounds) {
            
            keepInsideBounds(scene.getWidth(), scene.getHeight());
        }
    }
    
    private void updateDamageTimer() {
        
        if (damageTimer > 0) {
            
            if (damageTimer == 19) {
                
                setColor(Color.WHITE);
                
            } else if (damageTimer == 17) {
                
                resetColor();
                
            } else if (damageTimer == 15) {
                
                setColor(Color.WHITE);
                
            } else if (damageTimer == 13) {
                
                resetColor();
            }
            
            if (slowable) {
                
                setSpeed(originalSpeed - 0.1);
            }
            
            damageTimer--;
            
        } else if (takingDamage) {
            
            if (slowable) {
                
                resetSpeed();
            }
            
            resetColor();
            takingDamage = false;
        }
    }
    
    /**
     * Moves the Enemy in a direction of x and y with accelerating motion.
     * 
     * @param x Direction to move on the x axis
     * @param y Direction to move on the y axis
     */
    protected void move(double x, double y) {
        
        accelerationX += x;
        accelerationY += y;
        accelerationX *= speed;
        accelerationY *= speed;
        
        setPosition(getPositionX() + accelerationX, getPositionY() + accelerationY);
    }
    
    /**
     * Rotates the Enemy so that it points directly at the Player.
     */
    protected void lookAtPlayer() {
        
        Point2D dir = getDirectionToPlayer();
        setRotation((Math.atan2(dir.getX(), dir.getY()) * -180 / Math.PI) + 180);
    }

    protected Point2D normalizeDirection(Point2D direction) {
        
        double length = Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());
        double x = direction.getX();
        double y = direction.getY();
        
        if (length > 0) {
            x /= length;
            y /= length;
        }
        
        return new Point2D(x, y);
    }
    
    protected Point2D getDirectionToPlayer() {
        
        return new Point2D(player.getPositionX() - this.getPositionX(), player.getPositionY() - this.getPositionY());
    }
    
    protected double getDistanceToPlayer() {
        
        return Math.sqrt(Math.pow(player.getPositionX() - this.getPositionX(), 2) + Math.pow(player.getPositionY() - this.getPositionY(), 2));
    }
    
    protected boolean isInsideBounds() {
        
        double x = getPositionX();
        double y = getPositionY();

        if ((x > 0 && x < scene.getWidth()) && (y > 0 && y < scene.getHeight())) {

            return true;
        }
        
        return false;
    }
    
    protected void checkCollision() {
        
        if (collisionTimer <= 0) {
            
            if (getGraphics().getBoundsInParent().intersects(player.getGraphics().getBoundsInParent())) {
                
                if (player.getInvulnerable()) {
                    return;
                }
                
                damage(10);
                player.damage(10);
                collisionTimer = 20;
            }
            
        } else {
            
            collisionTimer--;
        }
    }
    
    /**
     * Damages the Enemy and destroys it if condition is zero or less.
     * 
     * @param dmg The amount of damage dealt
     */
    public void damage(int dmg) {
        
        condition -= dmg;
        damageTimer = 20;
        takingDamage = true;
        
        if (condition <= 0) {
            
            getGraphics().setVisible(false);
            destroyed = true;
        }
    }
    
    public double getSpeed() {
        
        return this.speed;
    }
    
    public double getOriginalSpeed() {
        
        return this.originalSpeed;
    }
    
    public int getCondition() {
        
        return this.condition;
    }
    
    public boolean getDestroyed() {
        
        return this.destroyed;
    }
    
    public Player getPlayer() {
        
        return this.player;
    }
    
    public void setSpeed(double speed) {
        
        this.speed = speed;
    }
    
    public void resetSpeed() {
        
        this.speed = originalSpeed;
    }
    
    public void setSlowable(boolean slowable) {
        
        this.slowable = slowable;
    }
    
    public void setPlayer(Player player) {
        
        this.player = player;
    }
}