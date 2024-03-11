package elements;

import java.awt.*;

/**
 * Abstract interface representing a design element.
 *
 * @author ChatGPT
 */
public interface DesignElement {
    public void setStartPoint(Point startPoint);
    public void draw(Graphics2D g);
    public Rectangle getBounds();
    public boolean isSelected();
    public void setSelected(boolean selected);
}