package spaceshooter.dao;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class DatabaseScoreDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    private DatabaseScoreDao testDao;
    
    @Before
    public void setUp() {
        
        testDao = new DatabaseScoreDao("jdbc:h2:" + testFolder.getRoot() + "/test");
    }
    
    @Test
    public void scoreIsAddedToDatabase() {
        
        testDao.add(100, "test");
        List<String[]> results = testDao.getTopTen();
        assertEquals("100", results.get(0)[0]);
        assertEquals("test", results.get(0)[1]);
    }
    
    @Test
    public void getTopTenLimitsResultsToTen() {
        
        for (int i = 0; i < 12; i++) {
            
            testDao.add(100, "test" + i);
        }
        
        List<String[]> results = testDao.getTopTen();
        
        assertEquals(10, results.size());
    }
}
