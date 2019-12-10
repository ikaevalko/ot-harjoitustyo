package spaceshooter.domain;

import java.util.HashMap;
import java.util.Random;
import javafx.geometry.Point2D;

/**
 * The Wave class represents a wave of enemies. 
 * It sets the enemies and the rate at which they spawn based on
 * a difficulty parameter.
 */
public class Wave {
    
    private HashMap<Integer, Integer> enemies;
    private GameSession session;
    private int difficulty;
    private int spawnRate;
    private boolean completed = false;
    
    public Wave(int difficulty) {
        
        try {
            session = GameSession.getInstance();
            
        } catch (Exception e) {
            
            System.out.println("Wave: Could not find GameSession");
        }
        
        this.enemies = new HashMap<>();
        this.difficulty = difficulty;
        this.spawnRate = 200 - difficulty * 20;
        setEnemies();
    }
    
    public boolean getCompleted() {
        
        return this.completed;
    }
    
    public int getSpawnRate() {
        
        return this.spawnRate;
    }
    
    /**
     * Creates a random type of enemy to be spawned and subtracts its 
     * corresponding value in the enemies HashMap. Marks this wave completed 
     * if all enemies have been spawned.
     */
    public void spawnEnemy() {
        
        if (completed) {
            return;
        }
        
        int enemy = new Random().nextInt(enemies.keySet().size());
        
        if (enemy == 0) {
            
            BasicEnemy basicEnemy = new BasicEnemy();
            initializeEnemy(basicEnemy);
        }
        
        enemies.put(enemy, enemies.get(enemy) - 1);
        
        if (enemies.get(enemy) <= 0) {
            
            enemies.remove(enemy);
        }
        
        if (enemies.isEmpty()) {
            
            completed = true;
        }
    }
    
    private void initializeEnemy(Enemy enemy) {
        
        Point2D spawnPoint = session.getRandomSpawnPoint();
        session.addToScene(enemy.getGraphics());
        session.addEnemy(enemy);
        enemy.setPosition(spawnPoint.getX(), spawnPoint.getY());
    }
    
    private void setEnemies() {
        
        int amount = (int)Math.ceil(Math.log10(difficulty + 1) * 9);
        
        for (int i = 0; i < amount; i++) {
            
            int e = 0;
            
            if (enemies.containsKey(e)) {
                
                enemies.put(e, enemies.get(e) + 1);
                
            } else {
                
                enemies.put(e, 1);
            }
        }
    }
}