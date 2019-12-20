package spaceshooter.domain;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * The Player class represents a type of GameObject that can receive 
 * instructions from a ControlScheme to move, rotate and shoot.
 */
public class Player extends GameObject {

    private double accelerationX;
    private double accelerationY;
    private double speed;
    private double originalSpeed;
    private int condition;
    private int shot;
    private GameSession session;
    private Scene scene;
    private ArrayList<Shot> shots;
    private ArrayList<Enemy> enemies;
    private int shotTimer = -1;
    private int dodgeTimer = -1;
    private double damageTimer = -1;
    private boolean invulnerable = false;
    private boolean takingDamage = false;
    private boolean destroyed = false;
    
    public Player() {
        
        super(new Point2D[] {new Point2D(-8, 8), new Point2D(0, -8), 
                             new Point2D(0, -8), new Point2D(8, 8)}, 
                Color.LIME);
        
        this.speed = 0.75;
        this.originalSpeed = this.speed;
        this.condition = 30;
        this.shot = 0;
        this.shots = new ArrayList<>();
        
        try {
            this.session = GameSession.getInstance();
            this.scene = session.getScene();
            this.enemies = session.getEnemies();
            
        } catch (Exception e) {
            
            System.out.println("Player: Could not find GameSession");
        }
    }
    
    /**
     * Advances the logic of the Player and the Shots it has fired.
     */
    public void update() {
        
        updateShots();
        
        if (shotTimer > 0) {
            
            shotTimer--;
        }
        
        updateDodgeTimer();
        updateDamageTimer();
        keepInsideBounds(scene.getWidth(), scene.getHeight());
    }
    
    /**
     * Moves the player in a direction determined by x and y with 
     * accelerating motion.
     * 
     * @param x Direction to move on the x axis
     * @param y Direction to move on the y axis
     */
    public void move(double x, double y) {
        
        accelerationX += x;
        accelerationY += y;
        accelerationX *= speed;
        accelerationY *= speed;
        
        setPosition(getPositionX() + accelerationX, getPositionY() + accelerationY);
    }
    
    /**
     * Creates a new Shot in front of the Player and adds it to the shots list 
     * to be updated. The type of the Shot depends on the shot variable.
     */
    public void shoot() {
        
        Point2D fwd = getForwardDirection();
        
        if (shotTimer <= 0) {
            
            if (shot == 0) {

                Bullet bullet = new Bullet(fwd);
                initializeShot(bullet, fwd);
                
            } else if (shot == 1) {
                
                ScatterShot scatterShot = new ScatterShot(fwd);
                initializeShot(scatterShot, fwd);
                
            } else if (shot == 2) {
                
                MachineGun machineGun = new MachineGun(fwd);
                initializeShot(machineGun, fwd);
            }
        }
    }
    
    private void initializeShot(Shot shot, Point2D direction) {
        
        session.addToScene(shot.getGraphics());
        shot.setPosition(getPositionX() + direction.getX() * 10, getPositionY() + direction.getY() * 10);
        shots.add(shot);
        shotTimer = shot.getDelay();
    }
    
    private void updateShots() {
        
        for (int i = 0; i < shots.size(); i++) {
            
            Shot shot = shots.get(i);
            
            if (shot.getDisabled()) {
                
                shots.remove(i);
                session.removeFromScene(shot.getGraphics());
                shot = null;
                
            } else {
                
                shot.update();
            }
        }
    }
    
    private void updateDodgeTimer() {
        
        if (dodgeTimer > 0) {
            
            if (invulnerable && dodgeTimer < 85) {
                
                speed = originalSpeed;
                getGraphics().setOpacity(1.0);
                invulnerable = false;
            }
            
            dodgeTimer--;
        }
    }
    
    private void updateDamageTimer() {
        
        if (damageTimer > 0) {
            
            if (damageTimer == 6) {
                
                setColor(Color.WHITE);
                
            } else if (damageTimer == 4) {
                
                resetColor();
                
            } else if (damageTimer == 2) {
                
                setColor(Color.WHITE);
            }
            
            damageTimer--;
            
        } else if (takingDamage) {
            
            resetColor();
            takingDamage = false;
        }
    }
    
    /**
     * Makes the Player faster and invulnerable for a very short time.
     */
    public void dodge() {
        
        if (dodgeTimer <= 0) {
            
            invulnerable = true;
            getGraphics().setOpacity(0.5);
            speed += 0.18;
            dodgeTimer = 100;
        }
    }
    
    /**
     * Damages the Player and destroys it if condition is zero or less.
     * 
     * @param dmg The amount of damage dealt
     */
    public void damage(int dmg) {
        
        if (invulnerable) {
            return;
        }
        
        condition -= dmg;
        damageTimer = 7;
        takingDamage = true;
        
        if (condition <= 0) {
            
            getGraphics().setVisible(false);
            destroyed = true;
        }
    }
    
    public void setShot(int shot) {
        
        this.shot = shot;
    }
    
    public double getSpeed() {
        
        return this.speed;
    }
    
    public int getCondition() {
        
        return this.condition;
    }
    
    public int getShot() {
        
        return this.shot;
    }
    
    public boolean getInvulnerable() {
        
        return this.invulnerable;
    }
    
    public boolean getDestroyed() {
        
        return this.destroyed;
    }
    
    public ArrayList<Shot> getShots() {
        
        return this.shots;
    }
    
    public ArrayList<Enemy> getEnemies() {
        
        return this.enemies;
    }
    
    /**
     * Gets the direction in which the player is facing as a Point2D.
     * 
     * @return the forward direction of the player
     */
    public Point2D getForwardDirection() {
        
        double x = Math.cos(Math.toRadians(getRotation() - 90));
        double y = Math.sin(Math.toRadians(getRotation() - 90));
        
        return new Point2D(x, y);
    }
}