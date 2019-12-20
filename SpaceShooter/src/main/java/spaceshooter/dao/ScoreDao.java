package spaceshooter.dao;

import java.util.List;

public interface ScoreDao {
    
    List<String[]> getTopTen();
    
    void add(int score, String name);
}
