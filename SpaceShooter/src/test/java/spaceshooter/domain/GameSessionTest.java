package spaceshooter.domain;

import java.util.concurrent.TimeoutException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import spaceshooter.ui.SpaceShooterUi;

public class GameSessionTest extends ApplicationTest {
    
    Stage stage;
    
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
    }
    
    @Test
    public void addToSceneWorksCorrectly() {
        
        GameSession.getInstance().getBase().getChildren().clear();
        GameSession.getInstance().addToScene(new Group());
        assertEquals(1, GameSession.getInstance().getBase().getChildren().size());
    }
}