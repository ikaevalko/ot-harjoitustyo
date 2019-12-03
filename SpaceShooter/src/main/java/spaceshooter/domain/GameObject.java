package spaceshooter.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GameObject {
    
    protected Group graphics;
    protected List<Line> lines;
    private Color originalColor = Color.WHITE;
    
    public GameObject(Point2D[] lines, Color color) {
        
        if (lines == null || lines.length <= 0 || lines.length % 2 != 0) {
            
            this.graphics = new Group();
            this.lines = new ArrayList<>();
            
        } else {
            
            this.graphics = createGraphics(lines, color);
            this.originalColor = color;
        }
    }
    
    public GameObject() {
        
        this.graphics = new Group();
        this.lines = new ArrayList<>();
    }
    
    private Group createGraphics(Point2D[] lines, Color color) {
        
        Group group = new Group();
        this.lines = new ArrayList<>();
        
        for (int i = 0; i < lines.length - 1; i++) {
            
            Line l = new Line(lines[i].getX(), lines[i].getY(), lines[i + 1].getX(), lines[i + 1].getY());
            l.setStroke(color);
            group.getChildren().add(l);
            this.lines.add(l);
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
    
    public void setColor(Color color) {
        
        for (Line l : lines) {
            
            l.setStroke(color);
        }
        
        this.graphics.getChildren().setAll(lines);
    }
    
    public void resetColor() {
        
        setColor(originalColor);
    }
    
    public Group getGraphics() {
        
        return this.graphics;
    }
    
    public List<Line> getLines() {
        
        return this.lines;
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
}