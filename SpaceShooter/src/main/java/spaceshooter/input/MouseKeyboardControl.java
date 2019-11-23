package spaceshooter.input;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import spaceshooter.domain.Player;

public class MouseKeyboardControl extends ControlScheme {
    
    private double mouseX;
    private double mouseY;
    
    public MouseKeyboardControl(Player player, Scene scene) {
        
        super(player, scene);
        scene.getRoot().setCursor(Cursor.CROSSHAIR);
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
        
        scene.setOnMouseMoved(e -> {
            
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        
        double dirX = mouseX - player.getPositionX();
        double dirY = mouseY - player.getPositionY();
        
        currentRotation = Math.atan2(dirX, dirY) * -180 / Math.PI;
        player.setRotation(currentRotation + 180);
    }
    
    @Override
    public void updateShootingInput() {
        
    }
}