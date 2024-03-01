package elements;

import java.awt.*;

/**
 * Class representing a wall design element.
 *
 * @author ChatGPT
 */
public class Wall implements DesignElement{
	
    protected Color color;
    protected int thickness;
    private Point startPoint;
    private Point endPoint;

    public Wall(Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
    }
    
    @Override
    public void draw(Graphics2D g, Point start, Point end) {
        if (startPoint != null && endPoint != null) {
            Point adjustedEnd = calculateAdjustedEnd(startPoint, endPoint);
            g.setColor(color);
            g.setStroke(new BasicStroke(thickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g.drawLine(startPoint.x, startPoint.y, adjustedEnd.x, adjustedEnd.y);
        }
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        //this.endPoint = startPoint; // Reset end point to start point initially
    }
   
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    private Point calculateAdjustedEnd(Point start, Point end) {
        int dx = Math.abs(end.x - start.x);
        int dy = Math.abs(end.y - start.y);
        if (dx > dy) {
            return new Point(end.x, start.y);
        } else {
            return new Point(start.x, end.y);
        }
    }

    @Override
    public Rectangle getBounds() {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        return new Rectangle(x, y, width, height);
    }
}