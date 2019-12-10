package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public abstract class Enemy extends GameObject {
    
    private Scene scene;
    private Player player;
    private double speed;
    private double originalSpeed;
    private int condition;
    private boolean destroyed;
    private double movementDirX;
    private double movementDirY;
    private int collisionTimer = -1;
    private int damageTimer = -1;
    private boolean takingDamage = false;
    private boolean enteredBounds = false;
    
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
    
    public void update() {
        
        if (destroyed) {
            return;
        }
        
        if (!enteredBounds && isInsideBounds()) {
            
            enteredBounds = true;
            
        } else if (enteredBounds) {
            
            keepInsideBounds(scene.getWidth(), scene.getHeight());
        }
        
        if (damageTimer > 0) {
            
            if (damageTimer > 18) {
                setColor(Color.WHITE);
            } else if (damageTimer > 16) {
                resetColor();
            } else if (damageTimer > 14) {
                setColor(Color.WHITE);
            } else {
                resetColor();
            }
            
            speed = originalSpeed * 0.6;
            damageTimer--;
            
        } else if (takingDamage) {
            
            resetColor();
            speed = originalSpeed;
            takingDamage = false;
        }
    }
    
    protected void moveTowardsPlayer() {
        
        movementDirX = player.getPositionX() - this.getPositionX();
        movementDirY = player.getPositionY() - this.getPositionY();
        
        normalizeMovementDirection();
        movementDirX *= speed;
        movementDirY *= speed;
        
        setPosition(getPositionX() + movementDirX, getPositionY() + movementDirY);
    }
    
    protected void normalizeMovementDirection() {
        
        double length = Math.sqrt(movementDirX * movementDirX + movementDirY * movementDirY);
        
        if (length > 0) {
            movementDirX /= length;
            movementDirY /= length;
        }
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
                
                takeDamage(10);
                collisionTimer = 5;
            }
            
        } else {
            
            collisionTimer--;
        }
    }
    
    public void takeDamage(int dmg) {
        
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
    
    public int getCondition() {
        
        return this.condition;
    }
    
    public boolean getDestroyed() {
        
        return this.destroyed;
    }
    
    public void setPlayer(Player player) {
        
        this.player = player;
    }
}