package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class representing a chair design element.
 *
 * @author Wahad Latif
 */
public class Bed implements DesignElement {
    private static final int DEFAULT_BED_WIDTH = 40;
    private static final int DEFAULT_BED_HEIGHT = 80;
    private int bedWidth = DEFAULT_BED_WIDTH;
    private int bedHeight = DEFAULT_BED_HEIGHT;
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
    		g.setColor(Color.RED);
    	}
        //g.fillRect(startPoint.x - bedWidth / 2, startPoint.y - bedHeight / 2, bedWidth, bedHeight);
        g.setStroke(new BasicStroke(2));

        
        int x = - bedWidth / 2;
        int y = - bedHeight / 2;
    
        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();
    
        // Translate and rotate the graphics context to draw the bed at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));
    
        // Draw the bed frame
        g.drawRect(x, y, bedWidth, bedHeight);
    
        // Offset the pillow from the bed frame
        int pillowWidth = bedWidth * 3 / 4; // Make the pillow wider
        int pillowHeight = bedHeight / 4;
        int pillowX =  x + (bedWidth - pillowWidth) / 2;//-pillowWidth / 2;
        int pillowY = y + bedHeight / 12; // Adjusted for closer to the top
    
        // Draw the pillow
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);
    
        // Draw the blanket
        int blanketHeight = bedHeight / 2;
        g.drawRect(x, pillowY + pillowHeight, bedWidth, blanketHeight);
    
        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - bedWidth / 2, startPoint.y - bedHeight / 2, bedWidth, bedHeight);
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
        bedWidth = (int) (scale * DEFAULT_BED_WIDTH);
        bedHeight = (int) (scale * DEFAULT_BED_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}