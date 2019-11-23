package spaceshooter.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    
    private Player player;
    
    @Before
    public void setUp() {
        
        this.player = new Player();
    }
    
    @Test
    public void constructorSetsVariablesCorrectly() {
        
        assertEquals(0.75, player.getSpeed(), 0.01);
        assertEquals(3, player.getCondition());
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
}
