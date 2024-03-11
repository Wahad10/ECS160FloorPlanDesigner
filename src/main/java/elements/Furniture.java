package elements;

import java.awt.*;

/**
 * Class representing a furniture design element.
 *
 * @author Wahad Latif
 */
public class Furniture implements DesignElement {

    private static final int FURNITURE_WIDTH = 40;
    private static final int FURNITURE_HEIGHT = 40;
    private Point startPoint;
    private boolean isSelected = false;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(startPoint.x - FURNITURE_WIDTH / 2, startPoint.y - FURNITURE_HEIGHT / 2, FURNITURE_WIDTH, FURNITURE_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - FURNITURE_WIDTH / 2, startPoint.y - FURNITURE_HEIGHT / 2, FURNITURE_WIDTH, FURNITURE_HEIGHT);
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

