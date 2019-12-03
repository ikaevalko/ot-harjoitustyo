package spaceshooter.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import spaceshooter.input.ControlScheme;
import spaceshooter.input.KeyboardOnlyControl;
import spaceshooter.input.MouseKeyboardControl;

public class GameSession {
    
    private static GameSession instance;
    private Pane base;
    private Scene scene;
    private Player player;
    private ControlScheme controls;
    private Point2D[] spawnPoints;
    private ArrayList<Enemy> enemies;
    private int spawnTimer;
    
    public GameSession(Pane base, Scene scene, int controlScheme) {
        
        instance = this;
        this.base = base;
        this.base.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.scene = scene;
        this.enemies = new ArrayList<>();
        this.player = new Player();
        this.base.getChildren().add(player.getGraphics());
        this.player.setPosition(this.scene.getWidth() * 0.5, this.scene.getHeight() * 0.5);
        setControlScheme(controlScheme);
        setSpawnPoints();
    }
    
    public static GameSession getInstance() {
        
        return instance;
    }
    
    public void update() {
        
        controls.update();
        player.update();
        player.keepInsideBounds(scene.getWidth(), scene.getHeight());
        updateEnemies();
        
        if (spawnTimer <= 0) {
            
            spawnEnemy();
            spawnTimer = 200;
        }
        
        spawnTimer--;
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
    
    private void updateEnemies() {
        
        for (int i = 0; i < enemies.size(); i++) {
            
            Enemy enemy = enemies.get(i);
            
            if (enemy.getDestroyed()) {
                
                enemy = null;
                enemies.remove(i);
                
            } else {
                
                enemy.update();
            }
        }
    }
    
    private void spawnEnemy() {
        
        Random random = new Random();
        Point2D spawnPoint = spawnPoints[random.nextInt(spawnPoints.length)];
        BasicEnemy enemy = new BasicEnemy();
        
        enemies.add(enemy);
        base.getChildren().add(enemy.getGraphics());
        enemy.setPosition(spawnPoint.getX(), spawnPoint.getY());
    }
    
    public void addToScene(Group graphics) {
        
        base.getChildren().add(graphics);
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
}