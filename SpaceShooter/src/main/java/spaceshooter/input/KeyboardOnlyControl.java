package spaceshooter.input;

import javafx.scene.input.KeyCode;

public class KeyboardOnlyControl extends ControlScheme {
    
    @Override
    public void updateMovementInput() {
        
        if (keyInputs.getOrDefault(KeyCode.A, false)) {
            
            movementInputX = -1;
            
        } else if (keyInputs.getOrDefault(KeyCode.D, false)) {
            
            movementInputX = 1;
            
        } else {
            
            movementInputX = 0;
        }
        
        if (keyInputs.getOrDefault(KeyCode.W, false)) {
            
            movementInputY = -1;
            
        } else if (keyInputs.getOrDefault(KeyCode.S, false)) {
            
            movementInputY = 1;
            
        } else {
            
            movementInputY = 0;
        }
        
        normalizeMovementInput();
        player.move(movementInputX, movementInputY);
    }
    
    @Override
    public void updateRotationInput() {
        
        if (keyInputs.getOrDefault(KeyCode.J, false)) {
            
            currentRotation -= 5;
            
        } else if (keyInputs.getOrDefault(KeyCode.L, false)) {
            
            currentRotation += 5;
        }
        
        player.setRotation(currentRotation);
    }
    
    @Override
    public void updateShootingInput() {
        
        if (keyInputs.getOrDefault(KeyCode.K, false)) {
            
            player.shoot();
        }
    }
    
    @Override
    public void updateDodgeInput() {
        
        if (keyInputs.getOrDefault(KeyCode.SPACE, false)) {
            
            player.dodge();
        }
    }
}