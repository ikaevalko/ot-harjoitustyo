package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShotTest {
    
    private GameSession testSession;
    private Shot shot;
    
    @Before
    public void setUp() {
        
        Pane base = new Pane();
        this.testSession = new GameSession(base, new Scene(base, 800, 800), 0);
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
