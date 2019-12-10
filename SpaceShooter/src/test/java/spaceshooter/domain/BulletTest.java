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

public class BulletTest extends ApplicationTest {
    
    Bullet bullet;
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
        this.bullet = new Bullet(new Point2D(1, 0));
    }
    
    @Test
    public void bulletMovesInTheRightDirection() {
        
        for (int i = 0; i < 20; i++) {
            
            bullet.update();
        }
        
        assertTrue(bullet.getPositionX() > 50);
        assertTrue(bullet.getPositionY() > -5);
        assertTrue(bullet.getPositionY() < 5);
    }
    
    @Test
    public void bulletGetsDisabledAfterDisablingTimer() {
        
        for (int i = 0; i <= 100; i++) {
            
            bullet.update();
        }
        
        assertTrue(bullet.getDisabled());
    }
}
