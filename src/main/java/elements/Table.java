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

//COME BACK AND CHECK BOUNDS ONCE DRAW SOMETHING GOOD
    @Override
    public Shape getBounds() {
        // Calculate the coordinates of the corners of the unrotated rectangle
        int x1 = -tableWidth / 2;
        int y1 = -tableHeight / 2;
        int x2 = tableWidth / 2;
        int y2 = -tableHeight / 2;
        int x3 = tableWidth / 2;
        int y3 = tableHeight / 2;
        int x4 = -tableWidth / 2;
        int y4 = tableHeight / 2;

        // Apply the rotation to each corner
        double cosTheta = Math.cos(Math.toRadians(rotationAngle));
        double sinTheta = Math.sin(Math.toRadians(rotationAngle));

        int[] xPoints = {(int) (x1 * cosTheta - y1 * sinTheta), (int) (x2 * cosTheta - y2 * sinTheta),
                (int) (x3 * cosTheta - y3 * sinTheta), (int) (x4 * cosTheta - y4 * sinTheta)};
        int[] yPoints = {(int) (x1 * sinTheta + y1 * cosTheta), (int) (x2 * sinTheta + y2 * cosTheta),
                (int) (x3 * sinTheta + y3 * cosTheta), (int) (x4 * sinTheta + y4 * cosTheta)};

        // Create a polygon from the rotated corners
        Polygon polygon = new Polygon(xPoints, yPoints, 4);

        // Translate the polygon to the start point
        polygon.translate(startPoint.x, startPoint.y);

        return polygon;
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