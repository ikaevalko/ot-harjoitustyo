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
    protected boolean dodging;
    
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
    
    /**
     * Updates the input that handles player movement.
     */
    abstract void updateMovementInput();
    
    /**
     * Updates the input that handles player rotation.
     */
    abstract void updateRotationInput();
    
    /**
     * Updates the input that handles shooting.
     */
    abstract void updateShootingInput();
    
    /**
     * Updates the input that handles dodging.
     */
    abstract void updateDodgeInput();
    
    /**
     * Calls update methods that listen for keystrokes.
     */
    public void update() {
        
        updateMovementInput();
        updateRotationInput();
        updateShootingInput();
        updateDodgeInput();
        
        if (keyInputs.getOrDefault(KeyCode.DIGIT1, false)) {
            
            player.setShot(0);
            
        } else if (keyInputs.getOrDefault(KeyCode.DIGIT2, false)) {
            
            player.setShot(1);
            
        } else if (keyInputs.getOrDefault(KeyCode.DIGIT3, false)) {
            
            player.setShot(2);
        }
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
    
    public double getMovementInputX() {
        
        return this.movementInputX;
    }
    
    public double getMovementInputY() {
        
        return this.movementInputX;
    }
}