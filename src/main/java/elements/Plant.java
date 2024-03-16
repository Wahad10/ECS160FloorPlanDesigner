package elements;

import java.awt.*;

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
        g.fillRect(startPoint.x - plantWidth / 2, startPoint.y - plantHeight / 2, plantWidth, plantHeight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - plantWidth / 2, startPoint.y - plantHeight / 2, plantWidth, plantHeight);
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
        plantWidth = (int) (scale * DEFAULT_PLANT_WIDTH);
        plantHeight = (int) (scale * DEFAULT_PLANT_HEIGHT);
    }
}

