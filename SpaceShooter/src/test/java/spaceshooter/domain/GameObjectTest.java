package spaceshooter.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameObjectTest {

    private GameObject gameObject;
    
    @Before
    public void setUp() {
        
        this.gameObject = new GameObject();
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
