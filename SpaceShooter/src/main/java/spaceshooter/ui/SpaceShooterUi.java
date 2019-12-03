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
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import spaceshooter.domain.GameSession;

public class SpaceShooterUi extends Application {
    
    private Stage stage;
    private Scene mainMenu;
    private Scene newGameMenu;
    private int controlScheme = 0;
    
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        createMainMenu();
        enterMainMenu();
        stage.setTitle("Space Shooter");
        stage.show();
    }
    
    private void createMainMenu() {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("Space Shooter");
        title.setStyle("-fx-font-size: 20");
        Button newGame = new Button("New Game");
        Button scoreboard = new Button("Scoreboard");
        Button quit = new Button("Quit");
        
        Insets padding = new Insets(10, 10, 10, 10);
        
        title.setPadding(padding);
        newGame.setPadding(padding);
        scoreboard.setPadding(padding);
        quit.setPadding(padding);
        
        newGame.setOnAction(e->{
            
            if (newGameMenu != null) {
                
                enterNewGameMenu();
                
            } else {
                
                createNewGameMenu();
                enterNewGameMenu();
            }
        });
        
        quit.setOnAction(e->{
            Platform.exit();
            System.exit(0);
        });
        
        layout.getChildren().addAll(title, newGame, scoreboard, quit);
        
        mainMenu = new Scene(layout, 800, 800);
    }
    
    private void enterMainMenu() {
        
        stage.setScene(mainMenu);
    }
    
    private void createNewGameMenu() {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        
        Label text = new Label("Choose control scheme");
        text.setStyle("-fx-font-size: 16");
        RadioButton mouseKeyboardControl = new RadioButton("Mouse + Keyboard");
        RadioButton keyboardOnlyControl = new RadioButton("Keyboard only");
        ToggleGroup group = new ToggleGroup();
        
        mouseKeyboardControl.setUserData(0);
        keyboardOnlyControl.setUserData(1);
        mouseKeyboardControl.setToggleGroup(group);
        keyboardOnlyControl.setToggleGroup(group);
        
        mouseKeyboardControl.setSelected(true);
        
        group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
                                                        controlScheme = (int) newVal.getUserData();
                                                    });
        
        Button start = new Button("Start");
        Button back = new Button("Back");
        
        start.setOnAction(e->{
            startNewGame();
        });
        
        back.setOnAction(e->{
            enterMainMenu();
        });
        
        Insets padding = new Insets(10, 10, 10, 10);
        
        text.setPadding(padding);
        keyboardOnlyControl.setPadding(new Insets(0, 0, 50, 0));
        start.setPadding(padding);
        back.setPadding(padding);
        
        layout.getChildren().addAll(text, mouseKeyboardControl, keyboardOnlyControl, start, back);
        newGameMenu = new Scene(layout, 800, 800);
    }
    
    private void enterNewGameMenu() {
        
        stage.setScene(newGameMenu);
    }
    
    private void startNewGame() {
        
        Pane base = new Pane();
        Scene scene = new Scene(base, 800, 800);
        GameSession session = new GameSession(base, scene, controlScheme);
        
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