package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class representing a plant design element.
 *
 * @author Wahad Latif
 */
public class Plant implements DesignElement { 
    private static final int DEFAULT_PLANT_WIDTH = 40;
    private static final int DEFAULT_PLANT_HEIGHT = 40;
    private int plantWidth = DEFAULT_PLANT_WIDTH;
    private int plantHeight = DEFAULT_PLANT_HEIGHT;
    private Point startPoint;
    private boolean isSelected = false;
    private int rotationAngle = 0;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        if (isSelected == true) {
    		g.setColor(Color.MAGENTA);
    	} else {
    		g.setColor(Color.GREEN);
    	}


        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate and rotate the graphics context to draw the bed at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        g.fillRect(- plantWidth / 2, - plantHeight / 2, plantWidth, plantHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - plantWidth / 2, startPoint.y - plantHeight / 2, plantWidth, plantHeight);
    }

    @Override
	public boolean isSelected() {
    	return isSelected;
    }
    
    @Override
    public void setSelected(boolean selected) {
    	isSelected = selected;
    }

    @Override
    public void resize(double scale) {
        plantWidth = (int) (scale * DEFAULT_PLANT_WIDTH);
        plantHeight = (int) (scale * DEFAULT_PLANT_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}

