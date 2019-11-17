package spaceshooter.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import spaceshooter.domain.GameSession;

public class SpaceShooterUi extends Application {
    
    private Stage stage;
    private Scene mainMenu;
    
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        createMainMenu();
    }
    
    private void createMainMenu() {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("Space Shooter");
        Button newGame = new Button("New Game");
        Button scoreboard = new Button("Scoreboard");
        Button quit = new Button("Quit");
        
        newGame.setOnAction(e->{
            startNewGame();
        });
        
        quit.setOnAction(e->{
            Platform.exit();
            System.exit(0);
        });
        
        layout.getChildren().addAll(title, newGame, scoreboard, quit);
        
        mainMenu = new Scene(layout, 800, 800);
        
        stage.setTitle("Space Shooter");
        stage.setScene(mainMenu);
        stage.show();
    }
    
    private void startNewGame() {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession session = new GameSession(base, scene);
        
        stage.setScene(scene);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                session.update();
            }
        };
        timer.start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}