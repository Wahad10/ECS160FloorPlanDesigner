package elements;

import java.awt.*;

/**
 * Class representing a table design element.
 *
 * @author Wahad Latif
 */
public class Table implements DesignElement {

    private static final int TABLE_WIDTH = 80;
    private static final int TABLE_HEIGHT = 40;
    private Point startPoint;
    private boolean isSelected = false;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        if (isSelected == true) {
    		g.setColor(Color.MAGENTA);
    	} else {
    		g.setColor(Color.YELLOW);
    	}
        g.fillRect(startPoint.x - TABLE_WIDTH / 2, startPoint.y - TABLE_HEIGHT / 2, TABLE_WIDTH, TABLE_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - TABLE_WIDTH / 2, startPoint.y - TABLE_HEIGHT / 2, TABLE_WIDTH, TABLE_HEIGHT);
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