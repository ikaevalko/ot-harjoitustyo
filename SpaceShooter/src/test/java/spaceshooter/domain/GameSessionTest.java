package spaceshooter.domain;

import java.util.concurrent.TimeoutException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import spaceshooter.ui.SpaceShooterUi;

public class GameSessionTest extends ApplicationTest {
    
    private GameSession testSession;
    private Stage stage;
    
    @Override
    public void start(Stage stage) {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        testSession = new GameSession(new SpaceShooterUi(), base, scene, 0);
        stage.setScene(scene);
    }
    
    @Before
    public void setUp() throws TimeoutException {
        
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(SpaceShooterUi.class);
    }
    
    @Test
    public void addEnemyWorksCorrectly() {
        
        testSession.addEnemy(new BasicEnemy());
        assertEquals(1, testSession.getEnemies().size());
    }
    
    @Test
    public void addToSceneWorksCorrectly() {
        
        testSession.getBase().getChildren().clear();
        testSession.addToScene(new Group());
        assertEquals(1, testSession.getBase().getChildren().size());
    }
    
    @Test
    public void enemiesUpdateCorrectly() {
        
        testSession.update();
        testSession.getEnemies().clear();
        testSession.addEnemy(new BasicEnemy());
        Enemy e = new BasicEnemy();
        testSession.addEnemy(e);
        e.takeDamage(100);
        testSession.update();
        assertEquals(1, testSession.getEnemies().size());
    }
}