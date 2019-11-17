package spaceshooter.input;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import spaceshooter.domain.Player;

public abstract class ControlScheme {
    
    protected Player player;
    protected Scene scene;
    protected HashMap<KeyCode, Boolean> keyInputs;
    protected double movementInputX;
    protected double movementInputY;
    protected double rotationInput;
    
    public ControlScheme(Player player, Scene scene) {
        
        this.player = player;
        this.scene = scene;
        this.keyInputs = new HashMap<>();
        setInputActions();
    }
    
    abstract void updateMovementInput();
    
    abstract void updateRotationInput();
    
    abstract void updateShootingInput();
    
    public void update() {
        
        updateMovementInput();
        updateRotationInput();
        updateShootingInput();
    }
    
    private void setInputActions() {

        scene.setOnKeyPressed(event -> {
            keyInputs.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            keyInputs.put(event.getCode(), Boolean.FALSE);
        });
    }
    
    protected void normalizeMovementInput() {
        
        double length = Math.sqrt(movementInputX * movementInputX + movementInputY * movementInputY);
        
        if(length > 0) {
            movementInputX /= length;
            movementInputY /= length;
        }
    }
}