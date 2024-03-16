package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class representing a wall design element.
 *
 * @author ChatGPT
 */
public class Wall implements DesignElement{
    private int DEFUALT_WALL_THICKNESS = 3;
    protected int wallThickness = DEFUALT_WALL_THICKNESS;
    private Point startPoint;
    private Point endPoint;
    private boolean isSelected = false;
    private int rotationAngle = 0;

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
        		g.setColor(Color.BLACK);
        	}
            g.setStroke(new BasicStroke(wallThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            
            //first draw
            if(rotationAngle == 0){
                g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            //rotate draw
            }else{
                // Save the current graphics transformation
                AffineTransform oldTransform = g.getTransform();

                // Translate and rotate the graphics context to draw at the desired position and angle
                g.translate(startPoint.x, startPoint.y);
                g.rotate(Math.toRadians(rotationAngle));

                g.drawLine(0, 0, endPoint.x - startPoint.x, endPoint.y - startPoint.y);

                // Restore the old graphics transformation
                g.setTransform(oldTransform);
            }
            
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
        int halfThickness = wallThickness / 2;
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
    public void resize(double scale) {
        wallThickness = (int) (scale * DEFUALT_WALL_THICKNESS);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}