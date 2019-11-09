package spaceshooter.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpaceShooterUi extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        
        drawer.setFill(Color.BLACK);
        drawer.fillRect(0, 0, 800, 800);
        
        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        
        Scene scene = new Scene(layout);
        
        primaryStage.setTitle("Space Shooter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}