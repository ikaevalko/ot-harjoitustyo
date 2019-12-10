package spaceshooter.domain;

import com.sun.prism.paint.Color;
import java.util.concurrent.TimeoutException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import spaceshooter.ui.SpaceShooterUi;

public class EnemyTest extends ApplicationTest {
    
    private Enemy enemy;
    private Stage stage;
    
    @Override
    public void start(Stage stage) {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession testSession = new GameSession(new SpaceShooterUi(), base, scene, 0);
        stage.setScene(scene);
    }
    
    @Before
    public void setUp() throws TimeoutException {
        
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SpaceShooterUi.class);
        this.enemy = new BasicEnemy();
    }
    
    @Test
    public void enemyCollidesWithPlayer() {
        
        Player player = new Player();
        player.setPosition(100, 100);
        enemy.setPlayer(player);
        enemy.setPosition(100, 100);
        enemy.update();
        assertEquals(50, enemy.getCondition());
    }
    
    @Test
    public void enemyGetsDestroyedCorrectly() {
        
        enemy.takeDamage(30);
        assertFalse(enemy.getDestroyed());
        enemy.takeDamage(30);
        assertTrue(enemy.getDestroyed());
    }
    
    @Test
    public void enemyStaysInsideBounds() {
        
        enemy.setPosition(100, 100);
        enemy.update();
        enemy.setPosition(-100, -100);
        enemy.update();
        assertTrue(enemy.getPositionX() >= 0);
        assertTrue(enemy.getPositionY() >= 0);
    }
    
    @Test
    public void collisionTimerWorksCorrectly() {
        
        Player player = new Player();
        player.setPosition(100, 100);
        enemy.setPlayer(player);
        enemy.setPosition(100, 100);
        enemy.update();
        enemy.update();
        enemy.update();
        assertEquals(50, enemy.getCondition());
    }
    
    @Test
    public void speedResetsAfterDamageTimer() {
        
        enemy.takeDamage(10);
        
        for (int i = 0; i <= 20; i++) {
            
            enemy.update();
        }
        
        assertEquals(3.2, enemy.getSpeed(), 0.1);
    }
}
