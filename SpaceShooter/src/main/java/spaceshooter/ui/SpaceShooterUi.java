package spaceshooter.ui;

import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spaceshooter.dao.DatabaseScoreDao;
import spaceshooter.dao.ScoreDao;
import spaceshooter.domain.GameSession;

/**
 * The SpaceShooterUi class handles building scenes and switching between them, 
 * as well as starting a new GameSession.
 */
public class SpaceShooterUi extends Application {
    
    private Stage stage;
    private Scene mainMenu;
    private Scene newGameMenu;
    private Scene scoreboardMenu;
    private int controlScheme = 0;
    private ScoreDao scoreDao;
    
    /**
     * Sets up the application window and main menu.
     * 
     * @param primaryStage The main window of the application
     */
    @Override
    public void start(Stage primaryStage) {
        
        scoreDao = new DatabaseScoreDao("jdbc:h2:./scores");
        
        this.stage = primaryStage;
        createMainMenu();
        createNewGameMenu();
        createScoreboardMenu();
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
        
        scoreboard.setOnAction(e->{
            
            createScoreboardMenu();
            enterScoreboardMenu();
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
    
    private void createScoreboardMenu() {
        
        VBox base = new VBox();
        GridPane scoreboard = new GridPane();
        
        Label title = new Label("Top 10");
        title.textFillProperty().setValue(Color.WHITE);
        title.setStyle("-fx-font-size: 16");
        
        Button back = new Button("Back");
        
        base.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        base.setAlignment(Pos.CENTER);
        scoreboard.setPrefSize(100, 100);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setVgap(5);
        scoreboard.setHgap(40);
        scoreboard.setPadding(new Insets(20, 10, 50, 10));
        back.setPadding(new Insets(10, 10, 10, 10));
        
        List<String[]> scores = scoreDao.getTopTen();
        
        for (int i = 0; i < scores.size(); i++) {
            
            Text placement = new Text(Integer.toString(i + 1) + ".");
            Text score = new Text(scores.get(i)[0]);
            Text name = new Text(scores.get(i)[1]);
            
            placement.fillProperty().setValue(Color.WHITE);
            score.fillProperty().setValue(Color.WHITE);
            name.fillProperty().setValue(Color.WHITE);
            
            scoreboard.add(placement, 0, i);
            scoreboard.add(score, 1, i);
            scoreboard.add(name, 2, i);
        }
        
        back.setOnAction(e->{
            enterMainMenu();
        });
        
        base.getChildren().add(title);
        base.getChildren().add(scoreboard);
        base.getChildren().add(back);
        scoreboardMenu = new Scene(base, 800, 800);
    }
    
    private void enterScoreboardMenu() {
        
        stage.setScene(scoreboardMenu);
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
    
    /**
     * Displays a game over screen with statistics about the player's 
     * performance and a form for saving the score to the database.
     * 
     * @param scene The game scene
     * @param score The player's score
     * @param wavesCompleted The number of waves the player completed
     */
    public void showGameOverScreen(Scene scene, int score, int wavesCompleted, boolean victory) {
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label endTitle = new Label();
        endTitle.textFillProperty().setValue(Color.WHITE);
        
        if (victory) {
            
            endTitle.setText("You win");
            
        } else {
            
            endTitle.setText("Your spaceship was destroyed");
        }
        
        Label endScore = new Label("Score: " + Integer.toString(score));
        endScore.textFillProperty().setValue(Color.WHITE);
        
        Label waves = new Label("Waves completed: " + Integer.toString(wavesCompleted));
        waves.textFillProperty().setValue(Color.WHITE);
        
        TextField nameField = new TextField();
        nameField.setFocusTraversable(false);
        nameField.setPrefWidth(100);
        nameField.setMaxWidth(100);
        Button submit = new Button("Submit Score");
        Label submitError = new Label("");
        submitError.textFillProperty().setValue(Color.WHITE);
        
        Button playAgain = new Button("Play Again");
        Button mainMenu = new Button("Main Menu");
        
        endTitle.setPadding(new Insets(10, 10, 10, 10));
        endScore.setPadding(new Insets(20, 10, 0, 10));
        waves.setPadding(new Insets(0, 10, 40, 10));
        submit.setPadding(new Insets(10, 10, 10, 10));
        playAgain.setPadding(new Insets(10, 10, 10, 10));
        mainMenu.setPadding(new Insets(10, 10, 10, 10));
        
        submit.setOnAction(e -> {
            
            if (nameField.getText().isEmpty()) {
                
                submitError.setText("Name must not be empty");
                
            } else if (nameField.getText().length() > 10) {
                
                submitError.setText("Name must be max. 10 characters");
                
            } else {
                
                scoreDao.add(score, nameField.getText());
                nameField.setVisible(false);
                submit.setVisible(false);
                submitError.setVisible(false);
            }
        });
        
        playAgain.setOnAction(e -> {
            
            startNewGame();
        });
        
        mainMenu.setOnAction(e -> {
            
            enterMainMenu();
        });
        
        layout.getChildren().addAll(endTitle, endScore, waves, nameField, submit, submitError, playAgain, mainMenu);
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