package elements;

import java.awt.*;

/**
 * Class representing a chair design element.
 *
 * @author Wahad Latif
 */
public class Bed implements DesignElement {

    private int bedWidth = 40;
    private int bedHeight = 80;
    private Point startPoint;
    private boolean isSelected = false;

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


        int x = startPoint.x - bedWidth / 2;
        int y = startPoint.y - bedHeight / 2;

        // Draw the bed frame
        g.drawRect(x, y, bedWidth, bedHeight);

        // Offset the pillow from the bed frame
        int pillowWidth = bedWidth * 3 / 4; // Make the pillow wider
        int pillowHeight = bedHeight / 4;
        int pillowX = x + (bedWidth - pillowWidth) / 2;
        int pillowY = y + bedHeight / 12; // Adjusted for closer to the top

        // Draw the pillow
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);

        // Draw the blanket
        int blanketHeight = bedHeight / 2;
        g.drawRect(x, pillowY + pillowHeight, bedWidth, blanketHeight);

        
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
    public void resize(int width, int height) {
    	bedWidth = width;
        bedHeight = height;
    }
}