package components;

import java.awt.*;

/**
 * Abstract interface representing a design element.
 *
 * @author ChatGPT
 */
public interface DesignElement {
    public void draw(Graphics2D g, Point start, Point end);
    public void setStartPoint(Point start);
    public void setEndPoint(Point end);
}
