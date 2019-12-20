package spaceshooter.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Point2D;

/**
 * The Wave class represents a wave of enemies. 
 * It sets the enemies and the rate at which they spawn based on
 * a difficulty parameter.
 */
public class Wave {
    
    private ArrayList<int[]> enemies;
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
        
        this.enemies = new ArrayList<>();
        
        this.difficulty = difficulty;
        this.spawnRate = 200 - difficulty * 20;
        setEnemies();
    }
    
    public boolean getCompleted() {
        
        return this.completed;
    }
    
    public int getDifficulty() {
        
        return this.difficulty;
    }
    
    public int getSpawnRate() {
        
        return this.spawnRate;
    }
    
    public ArrayList<int[]> getEnemies() {
        
        return this.enemies;
    }
    
    /**
     * Creates a random type of enemy to be spawned and subtracts its 
     * corresponding value in the enemies list. Marks this wave completed 
     * if all enemies have been spawned.
     */
    public void spawnEnemy() {
        
        if (completed) {
            return;
        }
        
        int index = new Random().nextInt(enemies.size());
        int[] enemy = enemies.get(index);
        int id = enemy[0];
        
        if (id == 0) {
            
            BasicEnemy basicEnemy = new BasicEnemy();
            initializeEnemy(basicEnemy);
            
        } else if (id == 1) {
            
            DashEnemy dashEnemy = new DashEnemy();
            initializeEnemy(dashEnemy);
        }
        
        enemy[1]--;
        
        if (enemy[1] <= 0) {
            
            enemies.remove(index);
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
        
        int amount = (int) Math.ceil(Math.log10(difficulty + 1) * 9);
        
        for (int i = 0; i < amount; i++) {
            
            int e = new Random().nextInt(2);
            int index = containsEnemy(e);
            
            if (index == -1) {
                
                enemies.add(new int[] {e, 1});
                
            } else {
                
                enemies.get(index)[1]++;
            }
        }
    }
    
    private int containsEnemy(int enemy) {
        
        if (enemies.isEmpty()) {
            
            return -1;
        }
        
        for (int i = 0; i < enemies.size(); i++) {
            
            if (enemies.get(i)[0] == enemy) {
                
                return i;
            }
        }
        
        return -1;
    }
}