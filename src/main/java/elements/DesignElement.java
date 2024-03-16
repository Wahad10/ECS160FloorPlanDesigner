package elements;

import java.awt.*;
import java.io.Serializable;

/**
 * Abstract interface representing a design element.
 *
 * @author ChatGPT
 */
public interface DesignElement extends Serializable {
    public Point getStartPoint();
    public void setStartPoint(Point startPoint);
    public void draw(Graphics2D g);
    public Rectangle getBounds();
    public boolean isSelected();
    public void setSelected(boolean selected);
    public void resize(double scale);
}