package elements;

import java.awt.*;

/**
 * Class representing a plant design element.
 *
 * @author Wahad Latif
 */
public class Plant implements DesignElement {

    private static final int PLANT_WIDTH = 40;
    private static final int PLANT_HEIGHT = 40;
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
    		g.setColor(Color.GREEN);
    	}
        g.fillRect(startPoint.x - PLANT_WIDTH / 2, startPoint.y - PLANT_HEIGHT / 2, PLANT_WIDTH, PLANT_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - PLANT_WIDTH / 2, startPoint.y - PLANT_HEIGHT / 2, PLANT_WIDTH, PLANT_HEIGHT);
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

