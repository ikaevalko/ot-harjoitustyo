package spaceshooter.domain;

import java.util.concurrent.TimeoutException;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import spaceshooter.ui.SpaceShooterUi;

public class PlayerTest extends ApplicationTest {
    
    private Player player;
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        
        FxToolkit.registerPrimaryStage();
    }
    
    @Before
    public void setUp() throws TimeoutException {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession testSession = new GameSession(new SpaceShooterUi(), base, scene, 0);
        this.player = new Player();
        
        FxToolkit.setupStage(stage -> {
            stage.setScene(scene);
        });
    }
    
    @Test
    public void constructorSetsVariablesCorrectly() {
        
        assertEquals(0.75, player.getSpeed(), 0.01);
        assertEquals(30, player.getCondition());
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
    public void playerShootsInTheRightDirection() {
        
        player.setRotation(90);
        player.shoot();
        assertEquals(player.getShots().size(), 1);
        assertEquals(1, player.getShots().get(0).getDirectionX(), 0.1);
        assertEquals(0, player.getShots().get(0).getDirectionY(), 0.1);
    }
    
    @Test
    public void playerCannotShootDuringShotCooldown() {
        
        player.shoot();
        player.update();
        player.shoot();
        assertTrue(player.getShots().size() < 2);
    }
    
    @Test
    public void playerCanTakeDamageWhenNotDodging() {
        
        player.damage(10);
        assertEquals(20, player.getCondition());
    }
    
    @Test
    public void playerCannotTakeDamageWhenDodging() {
        
        player.dodge();
        player.update();
        player.damage(10);
        assertEquals(30, player.getCondition());
    }
    
    @Test
    public void playerCannotDodgeDuringDodgeCooldown() {
        
        player.dodge();
        
        for (int i = 0; i < 30; i++) {
            
            player.update();
        }
        
        player.dodge();
        assertFalse(player.getInvulnerable());
    }
}
