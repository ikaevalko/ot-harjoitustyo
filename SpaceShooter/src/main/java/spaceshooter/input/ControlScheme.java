package spaceshooter.input;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import spaceshooter.domain.GameSession;
import spaceshooter.domain.Player;

public abstract class ControlScheme {
    
    protected Player player;
    protected Scene scene;
    protected HashMap<KeyCode, Boolean> keyInputs;
    protected double movementInputX;
    protected double movementInputY;
    protected double currentRotation;
    protected boolean shooting;
    
    public ControlScheme() {
        
        try {
            this.player = GameSession.getInstance().getPlayer();
            this.scene = GameSession.getInstance().getScene();
            
        } catch (Exception e) {
            
            System.out.println("ControlScheme: Could not find GameSession");
        }
        
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
        
        if (length > 0) {
            movementInputX /= length;
            movementInputY /= length;
        }
    }
}