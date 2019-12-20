package spaceshooter.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WaveTest {
    
    private Wave wave;
    
    @Before
    public void setUp() {
        
        this.wave = new Wave(5);
    }
    
    @Test
    public void constructorSetsVariablesCorrectly() {
        
        assertEquals(5, wave.getDifficulty());
        assertEquals(100, wave.getSpawnRate());
    }
}
