package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class representing a window design element.
 *
 * @author ChatGPT
 */
public class Window implements DesignElement {
    private static final int DEFAULT_WINDOW_WIDTH = 40;
    private static final int DEFAULT_WINDOW_HEIGHT = 5;
    private int windowWidth = DEFAULT_WINDOW_WIDTH;
    private int windowHeight = DEFAULT_WINDOW_HEIGHT;
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
    		g.setColor(Color.BLUE);
    	}


        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate and rotate the graphics context to draw the bed at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        g.fillRect( - windowWidth / 2, - windowHeight / 2, windowWidth, windowHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - windowWidth / 2, startPoint.y - windowHeight / 2, windowWidth, windowHeight);
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
        windowWidth = (int) (scale * DEFAULT_WINDOW_WIDTH);
        windowHeight = (int) (scale * DEFAULT_WINDOW_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}

