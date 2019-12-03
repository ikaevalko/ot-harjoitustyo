package spaceshooter.domain;

import java.util.concurrent.TimeoutException;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import spaceshooter.ui.SpaceShooterUi;

public class ShotTest extends ApplicationTest {
    
    private Shot shot;
    private Stage stage;
    
    @Override
    public void start(Stage stage) {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession testSession = new GameSession(base, scene, 0);
        stage.setScene(scene);
    }
    
    @Before
    public void setUp() throws TimeoutException {
        
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SpaceShooterUi.class);
        this.shot = new Bullet(new Point2D(1, 0));
    }
    
    @Test
    public void constructorSetsVariablesCorrectly() {
        
        assertTrue(shot.getEnemies() != null);
        assertEquals(1, shot.getDirectionX(), 0.1);
        assertEquals(0, shot.getDirectionY(), 0.1);
        assertEquals(5.0, shot.getSpeed(), 0.1);
        assertEquals(1, shot.getDamage());
        assertEquals(20, shot.getDelay());
    }
    
    @Test
    public void shotCollidesWithEnemy() {
        
        BasicEnemy enemy = new BasicEnemy();
        enemy.setPosition(100, 100);
        shot.getEnemies().add(enemy);
        shot.setPosition(100, 100);
        shot.update();
        assertEquals(true, shot.getDisabled());
    }
}
