package elements;

import java.awt.*;

/**
 * Class representing a table design element.
 *
 * @author Wahad Latif
 */
public class Table implements DesignElement {

    private int tableWidth = 80;
    private int tableHeight = 40;
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
    		g.setColor(Color.YELLOW);
    	}
        g.fillRect(startPoint.x - tableWidth / 2, startPoint.y - tableHeight / 2, tableWidth, tableHeight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - tableWidth / 2, startPoint.y - tableHeight / 2, tableWidth, tableHeight);
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
    	tableWidth = width;
        tableHeight = height;
    }
}