package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Door implements DesignElement {
    private static final int DEFAULT_DOOR_WIDTH = 80; // Diameter of the door
    private static final int DEFAULT_DOOR_HEIGHT = 80; // Diameter of the door
    private int doorWidth = DEFAULT_DOOR_WIDTH;
    private int doorHeight = DEFAULT_DOOR_HEIGHT;
    private Point startPoint;
    private boolean isSelected = false;
    private int rotationAngle = 0;

    /**public Door(Color color, int thickness) {
        super(color, thickness);
    }**/

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        if (isSelected) {
            g.setColor(Color.MAGENTA);
        } else {
            g.setColor(Color.RED);
        }
        g.setStroke(new BasicStroke(2));
        

        int x = - doorWidth / 2;
        int y = - doorHeight / 2;

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate and rotate the graphics context to draw the bed at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        // Draw the quarter circle
        g.drawArc(x, y, doorWidth, doorHeight, 90, 90);

        // Calculate the center of the circle
        int centerX = x + doorWidth / 2;
        int centerY = y + doorHeight / 2;

        // Calculate the endpoint of the line to meet the quarter circle
        int endX = (int) (centerX + doorWidth / 2 * Math.cos(Math.toRadians(270)));
        int endY = (int) (centerY + doorHeight / 2 * Math.sin(Math.toRadians(270)));

        // Draw a line from the center of the circle to the arc
        g.drawLine(centerX, centerY, endX, endY);
    
        // Restore the old graphics transformation
        g.setTransform(oldTransform);

    }

    @Override
    public Rectangle getBounds() {
        int x = startPoint.x - doorWidth / 2;
        int y = startPoint.y - doorHeight / 2;
        return new Rectangle(x, y, doorWidth / 2, doorHeight / 2);
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
    	doorWidth = (int) (scale * DEFAULT_DOOR_WIDTH);
        doorHeight = (int) (scale * DEFAULT_DOOR_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}