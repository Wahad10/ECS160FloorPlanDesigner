package elements;

import java.awt.*;

/**
 * Class representing a furniture design element.
 *
 * @author Wahad Latif
 */
public class Furniture implements DesignElement {

    private int furnitureWidth = 40;
    private int furnitureHeight = 40;
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
        g.setColor(Color.ORANGE);
        g.fillRect(startPoint.x - furnitureWidth / 2, startPoint.y - furnitureHeight / 2, furnitureWidth, furnitureHeight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - furnitureWidth / 2, startPoint.y - furnitureHeight / 2, furnitureWidth, furnitureHeight);
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
    	furnitureWidth = width;
        furnitureHeight = height;
    }
}

