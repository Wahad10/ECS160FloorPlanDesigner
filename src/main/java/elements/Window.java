package elements;

import java.awt.*;

/**
 * Class representing a window design element.
 *
 * @author ChatGPT
 */
public class Window implements DesignElement {

    private static final int WINDOW_WIDTH = 40;
    private static final int WINDOW_HEIGHT = 5;
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
    		g.setColor(Color.BLUE);
    	}
        g.fillRect(startPoint.x - WINDOW_WIDTH / 2, startPoint.y - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - WINDOW_WIDTH / 2, startPoint.y - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);
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

