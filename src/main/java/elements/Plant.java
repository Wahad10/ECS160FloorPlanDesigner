package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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

        // Draw the bounding box
        Rectangle bounds = getBounds();
        g.setColor(Color.BLUE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

    }

    

    @Override
    public Rectangle getBounds() {
        //return new Rectangle(startPoint.x - plantWidth / 2, startPoint.y - plantHeight / 2, plantWidth, plantHeight);
        // Create a rectangle centered at (0, 0) with the dimensions of the plant
        Rectangle plantBounds = new Rectangle(-plantWidth / 2, -plantHeight / 2, plantWidth, plantHeight);

        // Create an AffineTransform to rotate the rectangle
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotationAngle));

        // Transform the rectangle based on the rotation
        Shape transformedShape = transform.createTransformedShape(plantBounds);
        Rectangle bounds = transformedShape.getBounds();

        // Translate the bounds to match the start point
        bounds.translate(startPoint.x, startPoint.y);

        return bounds;
    }
    /**
    @Override
    public Rectangle getBounds() {
        // Create original corner points
        Point2D a0 = new Point2D.Double(-plantWidth / 2.0, -plantHeight / 2.0);
        Point2D b0 = new Point2D.Double(plantWidth / 2.0, -plantHeight / 2.0);
        Point2D c0 = new Point2D.Double(-plantWidth / 2.0, plantHeight / 2.0);
        Point2D d0 = new Point2D.Double(plantWidth / 2.0, plantHeight / 2.0);
        Point2D[] originalCorners = {a0, b0, c0, d0};

        // Create affine rotation transform
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle));

        // Transform original corners to rotated corners
        Point2D[] rotatedCorners = new Point2D[4];
        transform.transform(originalCorners, 0, rotatedCorners, 0, originalCorners.length);

        // Determine rotated width and height as difference between maximum and minimum rotated coordinates
        double minRotatedX = Double.POSITIVE_INFINITY;
        double maxRotatedX = Double.NEGATIVE_INFINITY;
        double minRotatedY = Double.POSITIVE_INFINITY;
        double maxRotatedY = Double.NEGATIVE_INFINITY;

        for (Point2D rotatedCorner : rotatedCorners) {
            minRotatedX = Math.min(minRotatedX, rotatedCorner.getX());
            maxRotatedX = Math.max(maxRotatedX, rotatedCorner.getX());
            minRotatedY = Math.min(minRotatedY, rotatedCorner.getY());
            maxRotatedY = Math.max(maxRotatedY, rotatedCorner.getY());
        }

        // The bounding box is the rectangle with minimum rotated X and Y as offset
        int x = (int) Math.round(startPoint.x + minRotatedX);
        int y = (int) Math.round(startPoint.y + minRotatedY);
        int width = (int) Math.round(maxRotatedX - minRotatedX);
        int height = (int) Math.round(maxRotatedY - minRotatedY);

        return new Rectangle(x, y, width, height);
    }
    **/


    
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

