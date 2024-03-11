package elements;

import java.awt.*;

/**
 * Class representing a chair design element.
 *
 * @author Wahad Latif
 */
public class Bed implements DesignElement {

    private static final int BED_WIDTH = 40;
    private static final int BED_HEIGHT = 80;
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
    		g.setColor(Color.RED);
    	}
        g.fillRect(startPoint.x - BED_WIDTH / 2, startPoint.y - BED_HEIGHT / 2, BED_WIDTH, BED_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - BED_WIDTH / 2, startPoint.y - BED_HEIGHT / 2, BED_WIDTH, BED_HEIGHT);
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