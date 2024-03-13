package elements;

import java.awt.*;
import java.awt.geom.GeneralPath;

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


        int x = startPoint.x - bedWidth / 2;
        int y = startPoint.y - bedHeight / 2;

        /** Draw the bed frame
        g.fillRect(x, y, bedWidth, bedHeight);

        // Draw the bed headboard
        g.fillRect(x, y, bedWidth, 10);

        // Draw the bed legs
        g.fillRect(x + bedWidth - 10, y, 10, bedHeight);
        g.fillRect(x, y + bedHeight - 10, bedWidth, 10);


        GeneralPath bedShape = new GeneralPath();
        bedShape.moveTo(x, y);
        bedShape.lineTo(x + bedWidth, y);
        bedShape.lineTo(x + bedWidth, y + bedHeight / 2);
        bedShape.lineTo(x, y + bedHeight / 2);
        bedShape.closePath();

        g.fill(bedShape);

        // Draw the bed frame
        g.drawRect(x, y, bedWidth, bedHeight);

        // Draw the pillow
        int pillowHeight = bedHeight / 4;
        g.drawRect(x, y, bedWidth, pillowHeight);

        // Draw the blanket
        int blanketHeight = bedHeight / 2;
        g.drawRect(x, y + pillowHeight, bedWidth, blanketHeight);
        
        // Draw the bed frame
        g.drawRect(x, y, bedWidth, bedHeight);

        // Offset the pillow from the bed frame
        int pillowWidth = bedWidth / 2;
        int pillowHeight = bedHeight / 4;
        int pillowX = x + (bedWidth - pillowWidth) / 4;
        int pillowY = y + pillowHeight / 4;

        // Draw the pillow
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);

        // Draw the blanket
        int blanketHeight = bedHeight / 2;
        g.drawRect(x, pillowY + pillowHeight, bedWidth, blanketHeight);*/
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