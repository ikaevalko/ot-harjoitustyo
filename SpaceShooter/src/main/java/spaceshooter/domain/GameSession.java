package spaceshooter.domain;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import spaceshooter.input.ControlScheme;
import spaceshooter.input.KeyboardOnlyControl;

public class GameSession {
    
    private Pane base;
    private Scene scene;
    private Player player;
    private ControlScheme controls;
    
    public GameSession(Pane base, Scene scene) {
        
        this.base = base;
        this.base.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.scene = scene;
        this.player = new Player();
        this.base.getChildren().add(player.getGraphics());
        this.player.setPosition(this.scene.getWidth() * 0.5, this.scene.getHeight() * 0.5);
        this.controls = new KeyboardOnlyControl(player, scene);
    }
    
    public void update() {
        
        controls.update();
        player.keepInsideBounds(scene.getWidth(), scene.getHeight());
    }
}