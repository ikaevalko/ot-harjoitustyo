package spaceshooter.domain;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * The GameObject class acts as a base for any type of object that may be 
 * added into the game scene. A GameObject can be constructed by passing it 
 * a Point2D array with an even number of elements and a color value. 
 * It is also possible to construct a GameObject without graphics.
 */
public class GameObject {
    
    private Group graphics;
    private List<Line> lines;
    private Color originalColor = Color.WHITE;
    
    /**
     * This constructor generates graphics for the GameObject. Note that the 
     * Point2D array passed to it must have an even number of elements because 
     * each line is generated based on a pair of points.
     * 
     * @param lines Point2D array needed to create graphics for the GameObject
     * @param color Color of the graphics
     */
    public GameObject(Point2D[] lines, Color color) {
        
        if (lines == null || lines.length < 2 || lines.length % 2 != 0) {
            
            this.graphics = new Group();
            this.lines = new ArrayList<>();
            
        } else {
            
            this.graphics = createGraphics(lines, color);
            this.originalColor = color;
        }
    }
    
    /**
     * This constructor creates a GameObject without graphics.
     */
    public GameObject() {
        
        this.graphics = new Group();
        this.lines = new ArrayList<>();
    }
    
    private Group createGraphics(Point2D[] lines, Color color) {
        
        Group group = new Group();
        this.lines = new ArrayList<>();
        
        for (int i = 0; i < lines.length - 1; i += 2) {
            
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
    
    /**
     * Prevents the GameObject from moving outside the boundaries of the scene.
     * 
     * @param sizeX The size of the scene on the x axis
     * @param sizeY The size of the scene on the y axis
     */
    
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