package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class representing a table design element.
 *
 * @author Wahad Latif
 */
public class Table implements DesignElement {
    private static final int DEFAULT_TABLE_WIDTH = 80;
    private static final int DEFAULT_TABLE_HEIGHT = 40;
    private int tableWidth = DEFAULT_TABLE_WIDTH;
    private int tableHeight = DEFAULT_TABLE_HEIGHT;
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
    		g.setColor(Color.YELLOW);
    	}


        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate and rotate the graphics context to draw the bed at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        //Draw the table
        g.fillRect( - tableWidth / 2, - tableHeight / 2, tableWidth, tableHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - tableWidth / 2, startPoint.y - tableHeight / 2, tableWidth, tableHeight);
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
        tableWidth = (int) (scale * DEFAULT_TABLE_WIDTH);
        tableHeight = (int) (scale * DEFAULT_TABLE_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}