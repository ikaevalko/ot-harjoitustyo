package spaceshooter.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import spaceshooter.domain.Player;

public class KeyboardOnlyControl extends ControlScheme {
    
    public KeyboardOnlyControl(Player player, Scene scene) {
        
        super(player, scene);
    }
    
    @Override
    public void updateMovementInput() {
        
        if(keyInputs.getOrDefault(KeyCode.A, false)) {
            
            movementInputX = -1;
            
        } else if(keyInputs.getOrDefault(KeyCode.D, false)) {
            
            movementInputX = 1;
            
        } else {
            
            movementInputX = 0;
        }
        
        if(keyInputs.getOrDefault(KeyCode.W, false)) {
            
            movementInputY = -1;
            
        } else if(keyInputs.getOrDefault(KeyCode.S, false)) {
            
            movementInputY = 1;
            
        } else {
            
            movementInputY = 0;
        }
        
        normalizeMovementInput();
        player.move(movementInputX, movementInputY);
    }
    
    @Override
    public void updateRotationInput() {
        
    }
    
    @Override
    public void updateShootingInput() {
        
    }
}