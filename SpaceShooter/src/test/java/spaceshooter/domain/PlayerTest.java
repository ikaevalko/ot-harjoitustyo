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

public class PlayerTest extends ApplicationTest {
    
    private Player player;
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
        this.player = new Player();
    }
    
    @Test
    public void constructorSetsVariablesCorrectly() {
        
        assertEquals(0.75, player.getSpeed(), 0.01);
        assertEquals(3, player.getCondition());
        assertEquals(0, player.getShot());
        assertTrue(player.getShots() != null);
        assertTrue(player.getShots().isEmpty());
        assertTrue(player.getEnemies() != null);
        assertTrue(player.getEnemies().isEmpty());
    }
    
    @Test
    public void playerMovesCorrectly() {
        
        for (int i = 0; i < 10; i++) {
            
            player.move(1, 1);
        }
        
        assertTrue(player.getPositionX() > 1);
        assertTrue(player.getPositionY() > 1);
        
        for (int i = 0; i < 20; i++) {
            
            player.move(-1, -1);
        }
        
        assertTrue(player.getPositionX() < -1);
        assertTrue(player.getPositionY() < -1);
    }
    
    @Test
    public void getForwardDirectionReturnsCorrectVector() {
        
        player.setRotation(90);
        Point2D direction = player.getForwardDirection();
        assertEquals(1.0, direction.getX(), 0.1);
        assertEquals(0.0, direction.getY(), 0.1);
    }
    
    @Test
    public void playerShootsCorrectly() {
        
        player.setRotation(90);
        player.shoot();
        assertEquals(player.getShots().size(), 1);
        assertEquals(1, player.getShots().get(0).getDirectionX(), 0.1);
        assertEquals(0, player.getShots().get(0).getDirectionY(), 0.1);
    }
}
