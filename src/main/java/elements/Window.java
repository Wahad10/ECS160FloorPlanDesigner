package elements;

import java.awt.*;

/**
 * Class representing a window design element.
 *
 * @author ChatGPT
 */
public class Window implements DesignElement {
    private static final int DEFAULT_WINDOW_WIDTH = 40;
    private static final int DEFAULT_WINDOW_HEIGHT = 5;
    private int windowWidth = DEFAULT_WINDOW_WIDTH;
    private int windowHeight = DEFAULT_WINDOW_HEIGHT;
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
    		g.setColor(Color.BLUE);
    	}
        g.fillRect(startPoint.x - windowWidth / 2, startPoint.y - windowHeight / 2, windowWidth, windowHeight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - windowWidth / 2, startPoint.y - windowHeight / 2, windowWidth, windowHeight);
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
        windowWidth = (int) (scale * DEFAULT_WINDOW_WIDTH);
        windowHeight = (int) (scale * DEFAULT_WINDOW_HEIGHT);
    }
}

