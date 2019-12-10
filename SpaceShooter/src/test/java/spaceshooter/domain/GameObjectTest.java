package spaceshooter.domain;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameObjectTest {

    private GameObject gameObject;
    
    @Before
    public void setUp() {
        
        Point2D[] lines = new Point2D[] {new Point2D(-5, 0), new Point2D(5, 0)};
        this.gameObject = new GameObject(lines, Color.WHITE);
    }
    
    @Test
    public void canBeCreatedWithGraphics() {
        
        assertFalse(gameObject.getLines().isEmpty());
    }
    
    @Test
    public void canBeCreatedWithoutGraphics() {
        
        gameObject = new GameObject(null, Color.WHITE);
        assertTrue(gameObject.getGraphics() != null);
        assertTrue(gameObject.getLines() != null);
        gameObject = new GameObject();
        assertTrue(gameObject.getGraphics() != null);
        assertTrue(gameObject.getLines() != null);
    }
    
    @Test
    public void setPositionWorksCorrectly() {
        
        gameObject.setPosition(300, 100);
        assertEquals(300, gameObject.getPositionX(), 0.1);
        assertEquals(100, gameObject.getPositionY(), 0.1);
        gameObject.setPosition(-50, -200);
        assertEquals(-50, gameObject.getPositionX(), 0.1);
        assertEquals(-200, gameObject.getPositionY(), 0.1);
    }
    
    @Test
    public void setRotationWorksCorrectly() {
        
        gameObject.setRotation(20);
        assertEquals(20, gameObject.getRotation(), 0.1);
        gameObject.setRotation(-20);
        assertEquals(-20, gameObject.getRotation(), 0.1);
    }
    
    @Test
    public void setColorWorksCorrectly() {
        
        gameObject.setColor(Color.BLUE);
        
        for (Line l : gameObject.getLines()) {
            
            assertEquals(Color.BLUE, l.getStroke());
        }
    }
    
    @Test
    public void resetColorWorksCorrectly() {
        
        gameObject.setColor(Color.BLUE);
        gameObject.resetColor();
        
        for (Line l : gameObject.getLines()) {
            
            assertTrue(l.getStroke().equals(Color.WHITE));
        }
    }
    
    @Test
    public void keepInsideBoundsClampsMinimumPosition() {
        
        gameObject.setPosition(-10, -10);
        gameObject.keepInsideBounds(800, 800);
        assertEquals(0, gameObject.getPositionX(), 0.1);
        assertEquals(0, gameObject.getPositionY(), 0.1);
    }
    
    @Test
    public void keepInsideBoundsClampsMaximumPosition() {
        
        gameObject.setPosition(810, 810);
        gameObject.keepInsideBounds(800, 800);
        assertEquals(800, gameObject.getPositionX(), 0.1);
        assertEquals(800, gameObject.getPositionY(), 0.1);
    }
}
