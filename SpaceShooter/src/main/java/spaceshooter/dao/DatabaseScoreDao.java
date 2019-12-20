package spaceshooter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseScoreDao implements ScoreDao {
    
    private Connection connection;
    
    public DatabaseScoreDao(String file) {
        
        try {
            
            this.connection = DriverManager.getConnection(file);
            
            PreparedStatement stmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Score ("
                                                                        + "id INTEGER AUTO_INCREMENT PRIMARY KEY, "
                                                                        + "score INTEGER, name VARCHAR(10));");
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            
            System.out.println("Failed to initialize scores database");
        }
    }
    
    @Override
    public List<String[]> getTopTen() {
        
        List<String[]> scores = new ArrayList<>();
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 10;");
            ResultSet results = stmt.executeQuery();
            
            while (results.next()) {
                
                scores.add(new String[] {Integer.toString(results.getInt("score")), results.getString("name")});
            }
            
            results.close();
            stmt.close();
            
        } catch (SQLException e) {
            
            System.out.println("Failed to get scores from database");
        }
        
        return scores;
    }
    
    @Override
    public void add(int score, String name) {
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Score (score, name) "
                                                                + "VALUES (?, ?);");
            
            stmt.setInt(1, score);
            stmt.setString(2, name);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            
            System.out.println("Failed to add score to database");
        }
    }
}