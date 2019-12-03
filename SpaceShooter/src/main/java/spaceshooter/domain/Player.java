package spaceshooter.domain;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Player extends GameObject {

    private double accelerationX;
    private double accelerationY;
    private double speed;
    private int condition;
    private int shot;
    private ArrayList<Shot> shots;
    private ArrayList<Enemy> enemies;
    private int shotTimer = -1;
    
    public Player() {
        
        super(new Point2D[] {new Point2D(-8, 8), new Point2D(0, -8), 
                             new Point2D(0, -8), new Point2D(8, 8)}, 
                Color.LIME);
        
        this.speed = 0.75;
        this.condition = 3;
        this.shot = 0;
        this.shots = new ArrayList<>();
        
        try {
            this.enemies = GameSession.getInstance().getEnemies();
            
        } catch (Exception e) {
            
            System.out.println("Player: Could not find GameSession");
        }
        
    }
    
    public void update() {
        
        updateShots();
        
        if (shotTimer > 0) {
            
            shotTimer--;
        }
    }
    
    public void move(double x, double y) {
        
        accelerationX += x;
        accelerationY += y;
        accelerationX *= speed;
        accelerationY *= speed;
        
        graphics.setTranslateX(graphics.getTranslateX() + accelerationX);
        graphics.setTranslateY(graphics.getTranslateY() + accelerationY);
    }
    
    public void shoot() {
        
        Point2D fwd = getForwardDirection();
        
        if (shotTimer <= 0) {
            
            if (shot == 0) {

                Bullet bullet = new Bullet(fwd);
                GameSession.getInstance().addToScene(bullet.getGraphics());
                setShotPosition(bullet, fwd);
                shots.add(bullet);
                shotTimer = bullet.getDelay();
            }
        }
    }
    
    private void setShotPosition(Shot shot, Point2D direction) {
        
        shot.setPosition(getPositionX() + direction.getX() * 10, getPositionY() + direction.getY() * 10);
    }
    
    private void updateShots() {
        
        for (int i = 0; i < shots.size(); i++) {
            
            Shot shot = shots.get(i);
            
            if (shot.getDisabled()) {
                
                shot = null;
                shots.remove(i);
                
            } else {
                
                shot.update();
            }
        }
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
    
    public ArrayList<Shot> getShots() {
        
        return this.shots;
    }
    
    public ArrayList<Enemy> getEnemies() {
        
        return this.enemies;
    }
    
    public Point2D getForwardDirection() {
        
        double x = Math.cos(Math.toRadians(getRotation() - 90));
        double y = Math.sin(Math.toRadians(getRotation() - 90));
        
        return new Point2D(x, y);
    }
}