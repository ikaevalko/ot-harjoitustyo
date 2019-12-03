package spaceshooter.domain;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameSessionTest {
    
    GameSession testSession;
    
    @Before
    public void setUp() {
        
        Pane base = new Pane();
        this.testSession = new GameSession(base, new Scene(base, 800, 800), 0);
    }
    
    @Test
    public void addToSceneWorksCorrectly() {
        
        testSession.getBase().getChildren().clear();
        testSession.addToScene(new Group());
        assertEquals(1, testSession.getBase().getChildren().size());
    }
}
