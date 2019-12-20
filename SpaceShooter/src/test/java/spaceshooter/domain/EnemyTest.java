package spaceshooter.domain;

import java.util.concurrent.TimeoutException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import spaceshooter.ui.SpaceShooterUi;

public class EnemyTest extends ApplicationTest {
    
    private Enemy enemy;
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        
        FxToolkit.registerPrimaryStage();
    }
    
    @Before
    public void setUp() throws TimeoutException {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession testSession = new GameSession(new SpaceShooterUi(), base, scene, 0);
        this.enemy = new BasicEnemy();
        
        FxToolkit.setupStage(stage -> {
            stage.setScene(scene);
        });
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
        
        enemy.damage(30);
        assertFalse(enemy.getDestroyed());
        enemy.damage(30);
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
        
        enemy.damage(10);
        
        for (int i = 0; i <= 20; i++) {
            
            enemy.update();
        }
        
        assertEquals(0.78, enemy.getSpeed(), 0.1);
    }
}
