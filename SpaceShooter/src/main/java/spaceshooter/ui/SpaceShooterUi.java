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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import spaceshooter.domain.GameSession;

/**
 * The SpaceShooterUi class handles building scenes and switching between them, 
 * as well as starting a new GameSession.
 */
public class SpaceShooterUi extends Application {
    
    private Stage stage;
    private Scene mainMenu;
    private Scene newGameMenu;
    private int controlScheme = 0;
    
    /**
     * Sets up the application window and main menu.
     * 
     * @param primaryStage The main window of the application
     */
    @Override
    public void start(Stage primaryStage) {
        
        this.stage = primaryStage;
        createMainMenu();
        createNewGameMenu();
        enterMainMenu();
        stage.setTitle("Space Shooter");
        stage.setResizable(false);
        stage.show();
    }

    private void createMainMenu() {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label title = new Label("Space Shooter");
        title.textFillProperty().setValue(Color.WHITE);
        title.setStyle("-fx-font-size: 20");
        Button newGame = new Button("New Game");
        Button scoreboard = new Button("Scoreboard");
        Button quit = new Button("Quit");
        
        title.setPadding(new Insets(10, 10, 40, 10));
        newGame.setPadding(new Insets(10, 10, 10, 10));
        scoreboard.setPadding(new Insets(10, 10, 10, 10));
        quit.setPadding(new Insets(10, 10, 10, 10));
        
        newGame.setOnAction(e->{
            
            enterNewGameMenu();
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
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label text = new Label("Choose control scheme");
        text.textFillProperty().setValue(Color.WHITE);
        text.setStyle("-fx-font-size: 16");
        RadioButton mouseKeyboardControl = new RadioButton("Mouse + Keyboard");
        RadioButton keyboardOnlyControl = new RadioButton("Keyboard only");
        ToggleGroup group = new ToggleGroup();
        
        mouseKeyboardControl.textFillProperty().setValue(Color.WHITE);
        keyboardOnlyControl.textFillProperty().setValue(Color.WHITE);
        
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
        
        text.setPadding(new Insets(10, 10, 10, 10));
        mouseKeyboardControl.setPadding(new Insets(10, 10, 5, 10));
        keyboardOnlyControl.setPadding(new Insets(0, 0, 20, 0));
        start.setPadding(new Insets(10, 10, 10, 10));
        back.setPadding(new Insets(10, 10, 10, 10));
        
        layout.getChildren().addAll(text, mouseKeyboardControl, keyboardOnlyControl, start, back);
        newGameMenu = new Scene(layout, 800, 800);
    }
    
    private void enterNewGameMenu() {
        
        stage.setScene(newGameMenu);
    }
    
    private void startNewGame() {
        
        Pane base = new Pane();
        base.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(base, 800, 800);
        GameSession session = new GameSession(this, base, scene, controlScheme);
        
        stage.setScene(scene);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                session.update();
            }
        };
        timer.start();
    }
    
    public void showGameOverScreen(Scene scene, int score, int wavesCompleted) {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label endTitle = new Label("You win!");
        endTitle.textFillProperty().setValue(Color.WHITE);
        
        Label endScore = new Label("Score: " + Integer.toString(score));
        endScore.textFillProperty().setValue(Color.WHITE);
        
        Label waves = new Label("Waves completed: " + Integer.toString(wavesCompleted));
        waves.textFillProperty().setValue(Color.WHITE);
        
        Button playAgain = new Button("Play Again");
        Button mainMenu = new Button("Main Menu");
        
        endTitle.setPadding(new Insets(10, 10, 10, 10));
        endScore.setPadding(new Insets(20, 10, 0, 10));
        waves.setPadding(new Insets(0, 10, 40, 10));
        playAgain.setPadding(new Insets(10, 10, 10, 10));
        mainMenu.setPadding(new Insets(10, 10, 10, 10));
        
        playAgain.setOnAction(e -> {
            
            startNewGame();
        });
        
        mainMenu.setOnAction(e -> {
            
            enterMainMenu();
        });
        
        layout.getChildren().addAll(endTitle, endScore, waves, playAgain, mainMenu);
        scene.setRoot(layout);
    }
    
    /**
     * Launches the JavaFX application.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}