package spaceshooter.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import spaceshooter.input.ControlScheme;
import spaceshooter.input.KeyboardOnlyControl;
import spaceshooter.input.MouseKeyboardControl;
import spaceshooter.ui.SpaceShooterUi;

/**
 * The GameSession class keeps track of the overall state of the game. 
 * It spawns the player and enemies, forwards update calls, advances the game 
 * and counts points for the player.
 */
public class GameSession {
    
    private static GameSession instance;
    private SpaceShooterUi ui;
    private Pane base;
    private Scene scene;
    
    private Player player;
    private ControlScheme controls;
    
    private ArrayList<Wave> waves;
    private ArrayList<Enemy> enemies;
    private Point2D[] spawnPoints;
    private int currentWaveIndex = 0;
    private Wave currentWave;
    private int spawnTimer = -1;
    private boolean gameOver = false;
    
    private Label waveText;
    private Label scoreDisplay;
    private int score = 0;
    
    public GameSession(SpaceShooterUi ui, Pane base, Scene scene, int controlScheme) {
        
        instance = this;
        this.ui = ui;
        this.base = base;
        this.scene = scene;
        
        this.enemies = new ArrayList<>();
        this.waves = new ArrayList<>();
        setSpawnPoints();
        setWaves();
        currentWave = waves.get(0);
        
        this.player = new Player();
        addToScene(player.getGraphics());
        this.player.setPosition(this.scene.getWidth() * 0.5, this.scene.getHeight() * 0.5);
        setControlScheme(controlScheme);
        
        setUi();
    }
    
    /**
     * Gets the static singleton instance of this class.
     * 
     * @return static singleton instance
     */
    public static GameSession getInstance() {
        
        return instance;
    }
    
    /** 
     * Advances all game logic by updating the state of this object and
     * calling update methods of various other objects.
     */
    public void update() {
        
        if (gameOver) {
            return;
        }
        
        controls.update();
        player.update();
        updateEnemies();
        updateWaves();
    }
    
    private void updateEnemies() {
        
        for (int i = 0; i < enemies.size(); i++) {
            
            Enemy enemy = enemies.get(i);
            
            if (enemy.getDestroyed()) {
                
                enemies.remove(i);
                removeFromScene(enemy.getGraphics());
                enemy = null;
                increaseScore(10);
                
            } else {
                
                enemy.update();
            }
        }
    }
    
    private void updateWaves() {
        
        if (spawnTimer <= 0 && !currentWave.getCompleted()) {
            
            currentWave.spawnEnemy();
            spawnTimer = currentWave.getSpawnRate();
            
        } else {
            
            spawnTimer--;
        }
        
        if (currentWave.getCompleted() && enemies.isEmpty()) {
            
            if (currentWaveIndex >= waves.size() - 1) {
                
                ui.showGameOverScreen(scene, score, currentWaveIndex + 1);
                waveText.setVisible(false);
                scoreDisplay.setVisible(false);
                gameOver = true;
                
            } else {
                
                currentWaveIndex++;
                currentWave = waves.get(currentWaveIndex);
                waveText.textProperty().setValue("Wave " + (currentWaveIndex + 1));
            }
        }
    }
    
    private void setControlScheme(int controlScheme) {
        
        if (controlScheme == 0) {
            
            this.controls = new MouseKeyboardControl();
            
        } else if (controlScheme == 1) {
            
            this.controls = new KeyboardOnlyControl();
        }
    }
    
    private void setSpawnPoints() {
        
        this.spawnPoints = new Point2D[] {new Point2D(-50, -50), 
                                          new Point2D(scene.getWidth() * 0.5, -50), 
                                          new Point2D(scene.getWidth() + 50, -50), 
                                          new Point2D(-50, scene.getHeight() * 0.5), 
                                          new Point2D(scene.getWidth() + 50, scene.getHeight() * 0.5), 
                                          new Point2D(-50, scene.getHeight() + 50), 
                                          new Point2D(scene.getWidth() * 0.5, scene.getHeight() + 50), 
                                          new Point2D(scene.getWidth() + 50, scene.getHeight() + 50)};
    }
    
    private void setWaves() {
        
        for (int i = 0; i < 5; i++) {
            
            waves.add(new Wave(i + 1));
        }
    }
    
    private void setUi() {
        
        this.scoreDisplay = new Label("0");
        scoreDisplay.textFillProperty().setValue(Color.WHITE);
        base.getChildren().add(scoreDisplay);
        scoreDisplay.setTranslateX(20);
        scoreDisplay.setTranslateY(20);
        
        this.waveText = new Label("Wave 1");
        waveText.textFillProperty().setValue(Color.WHITE);
        base.getChildren().add(waveText);
        waveText.setTranslateX(scene.getWidth() * 0.5 - 20);
        waveText.setTranslateY(20);
    }
    
    /**
     * Adds the graphics component of a GameObject to the scene.
     * 
     * @param graphics The graphics to be added into the scene.
     */
    public void addToScene(Group graphics) {
        
        base.getChildren().add(graphics);
    }
    
    /**
     * Removes the graphics component of a GameObject from the scene.
     * 
     * @param graphics The graphics to be removed from the scene
     */
    public void removeFromScene(Group graphics) {
        
        base.getChildren().remove(graphics);
    }
    
    /**
     * Adds a new Enemy to the enemies list for updating.
     */
    public void addEnemy(Enemy enemy) {
        
        this.enemies.add(enemy);
    }
    
    /**
     * Increases the player's current score by a set amount and updates the 
     * score display to match the new score value.
     * 
     * @param amount the increase in score
     */
    public void increaseScore(int amount) {
        
        score += amount;
        
        scoreDisplay.textProperty().setValue(Integer.toString(score));
    }
    
    public Scene getScene() {
        
        return scene;
    }
    
    public Pane getBase() {
        
        return base;
    }
    
    public Player getPlayer() {
        
        return player;
    }
    
    public ArrayList<Enemy> getEnemies() {
        
        return enemies;
    }
    
    public ControlScheme getControlScheme() {
        
        return this.controls;
    }
    
    /**
     * Gets a random spawn point from the spawn points list as a Point2D.
     * 
     * @return randomly chosen spawn point
     */
    public Point2D getRandomSpawnPoint() {
        
        return spawnPoints[new Random().nextInt(spawnPoints.length)];
    }
}