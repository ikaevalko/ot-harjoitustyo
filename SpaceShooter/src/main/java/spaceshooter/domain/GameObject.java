package spaceshooter.domain;

import javafx.scene.Node;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GameObject {
    
    protected Node graphics;
    
    public GameObject(Point2D[] lines, Color color) {
        
        if (lines.length <= 0 || lines.length % 2 != 0) {
            this.graphics = new Rectangle(10, 10);
        }
        
        this.graphics = createGraphics(lines, color);
    }
    
    public GameObject() {
        
        this.graphics = new Rectangle(10, 10);
    }
    
    private Node createGraphics(Point2D[] lines, Color color) {
        
        Group group = new Group();
        
        for (int i = 0; i < lines.length - 1; i++) {
            
            Line l = new Line(lines[i].getX(), lines[i].getY(), lines[i + 1].getX(), lines[i + 1].getY());
            l.setStroke(color);
            group.getChildren().add(l);
        }
        
        return group;
    }
    
    public void setPosition(double x, double y) {
        
        graphics.setTranslateX(x);
        graphics.setTranslateY(y);
    }
    
    public void setRotation(double rotation) {
        
        graphics.setRotate(rotation);
    }
    
    public double getPositionX() {
        
        return this.graphics.getTranslateX();
    }
    
    public double getPositionY() {
        
        return this.graphics.getTranslateY();
    }
    
    public double getRotation() {
        
        return this.graphics.getRotate();
    }
    
    public void keepInsideBounds(double sizeX, double sizeY) {
        
        double posX = graphics.getTranslateX();
        double posY = graphics.getTranslateY();
        
        if (posX < 0) {
            graphics.setTranslateX(0);
            
        } else if (posX > sizeX) {
            graphics.setTranslateX(sizeX);
        }
        
        if (posY < 0) {
            graphics.setTranslateY(0);
            
        } else if (posY > sizeY) {
            graphics.setTranslateY(sizeY);
        }
    }
    
    public Node getGraphics() {
        
        return this.graphics;
    }
}