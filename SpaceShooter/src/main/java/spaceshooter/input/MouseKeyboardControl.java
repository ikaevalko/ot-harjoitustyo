package spaceshooter.input;

import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class MouseKeyboardControl extends ControlScheme {
    
    private double mouseX;
    private double mouseY;
    
    public MouseKeyboardControl() {
        
        scene.getRoot().setCursor(Cursor.CROSSHAIR);
        
        scene.setOnMouseMoved(e -> {
            
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        
        scene.setOnMouseDragged(e -> {
            
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        
        scene.setOnMousePressed(e -> {
            
            if (e.getButton() == MouseButton.PRIMARY) {
                
                shooting = true;
                
            } else if (e.getButton() == MouseButton.SECONDARY) {
                
                dodging = true;
            }
        });
        
        scene.setOnMouseReleased(e -> {
            
            if (e.getButton() == MouseButton.PRIMARY) {
                
                shooting = false;
                
            } else if (e.getButton() == MouseButton.SECONDARY) {
                
                dodging = false;
            }
        });
    }
    
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
        
        double dirX = mouseX - player.getPositionX();
        double dirY = mouseY - player.getPositionY();
        
        currentRotation = Math.atan2(dirX, dirY) * -180 / Math.PI;
        player.setRotation(currentRotation + 180);
    }
    
    @Override
    public void updateShootingInput() {
        
        if (shooting) {
            
            player.shoot();
        }
    }
    
    @Override
    public void updateDodgeInput() {
        
        if (dodging) {
            
            player.dodge();
        }
    }
}