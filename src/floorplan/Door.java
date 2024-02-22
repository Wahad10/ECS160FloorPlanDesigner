package floorplan;

import java.awt.*;

public class Door implements DesignElement {

    private static final int DOOR_SIZE = 80; // Diameter of the door

    /**public Door(Color color, int thickness) {
        super(color, thickness);
    }**/

    @Override
    public void draw(Graphics2D g, Point start, Point end) {
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2));

        int x = start.x - DOOR_SIZE / 2;
        int y = start.y - DOOR_SIZE / 2;

        // Draw the quarter circle
        g.drawArc(x, y, DOOR_SIZE, DOOR_SIZE, 90, 90);

        // Calculate the center of the circle
        int centerX = x + DOOR_SIZE / 2;
        int centerY = y + DOOR_SIZE / 2;

        // Draw a line from one of the arc's endpoints to the circle center
        //g.drawLine(centerX, centerY, x + DOOR_SIZE, y + DOOR_SIZE);
        
     // Calculate the endpoint of the line to meet the quarter circle
        int endX = (int) (centerX + DOOR_SIZE / 2 * Math.cos(Math.toRadians(270)));
        int endY = (int) (centerY + DOOR_SIZE / 2 * Math.sin(Math.toRadians(270)));

        // Draw a line from the center of the circle to the arc
        g.drawLine(centerX, centerY, endX, endY);

    }
    
    public void setStartPoint(Point start) {
    }
    
    public void setEndPoint(Point end) {
    }
}