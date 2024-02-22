package floorplan;

import java.awt.*;

/**
 * Abstract class representing a design element.
 *
 * @author ChatGPT
 */
public interface DesignElement {
    public void draw(Graphics2D g, Point start, Point end);
    public void setStartPoint(Point start);
    public void setEndPoint(Point end);
}
