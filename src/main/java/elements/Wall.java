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
    private boolean isSelected = false;

    public Wall(Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        this.endPoint = startPoint; // Reset end point to start point initially
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
    public void draw(Graphics2D g) {
        if (startPoint != null && endPoint != null && endPoint != startPoint) {
            Point adjustedEnd = calculateAdjustedEnd(startPoint, endPoint);
            if (isSelected == true) {
        		g.setColor(Color.MAGENTA);
        	} else {
        		g.setColor(color);
        	}
            g.setStroke(new BasicStroke(thickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g.drawLine(startPoint.x, startPoint.y, adjustedEnd.x, adjustedEnd.y);
        }
    }

    @Override
    public Rectangle getBounds() {
        if (startPoint == null || endPoint == null) {
            return new Rectangle();
        }
    
        Point adjustedEnd = calculateAdjustedEnd(startPoint, endPoint);
    
        int minX = Math.min(startPoint.x, adjustedEnd.x);
        int minY = Math.min(startPoint.y, adjustedEnd.y);
        int maxX = Math.max(startPoint.x, adjustedEnd.x);
        int maxY = Math.max(startPoint.y, adjustedEnd.y);
    
        // Adjust bounds for the line thickness
        int halfThickness = thickness / 2;
        minX -= halfThickness;
        minY -= halfThickness;
        maxX += halfThickness;
        maxY += halfThickness;
    
        int width = maxX - minX;
        int height = maxY - minY;

        //System.out.println("Wall Bounds:" + minX + "," + minY + "," + width + "," + height);
    
        return new Rectangle(minX, minY, width, height);
    }

    @Override
	public boolean isSelected() {
    	return isSelected;
    }
    
    @Override
    public void setSelected(boolean selected) {
    	isSelected = selected;
    }
}