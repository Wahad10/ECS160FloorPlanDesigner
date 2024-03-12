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

    public Point getStartPoint() {
        return startPoint;
    }
    
    public void setStartPoint(Point startPoint) {
        if(this.endPoint == null){
            this.startPoint = startPoint;
            this.endPoint = startPoint; // Reset end point to start point initially
        }else{
            if(this.startPoint.x == this.endPoint.x){
                int ydiff = this.endPoint.y - this.startPoint.y;
                this.startPoint = startPoint;
                this.endPoint = new Point(this.startPoint.x, this.startPoint.y + ydiff);
            }else{
                int xdiff = this.endPoint.x - this.startPoint.x;
                this.startPoint = startPoint;
                this.endPoint = new Point(this.startPoint.x + xdiff, this.startPoint.y);
            }
        }
    }
        
    public void setEndPoint(Point endPoint) {
        this.endPoint = calculateAdjustedEnd(startPoint, endPoint);
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
            if (isSelected == true) {
        		g.setColor(Color.MAGENTA);
        	} else {
        		g.setColor(color);
        	}
            g.setStroke(new BasicStroke(thickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }

    @Override
    public Rectangle getBounds() {
        if (startPoint == null || endPoint == null) {
            return new Rectangle();
        }
    
        int minX = Math.min(startPoint.x, endPoint.x);
        int minY = Math.min(startPoint.y, endPoint.y);
        int maxX = Math.max(startPoint.x, endPoint.x);
        int maxY = Math.max(startPoint.y, endPoint.y);
    
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

    @Override
    public void resize(int width, int height) {
    	//cant resize wall
    }
}