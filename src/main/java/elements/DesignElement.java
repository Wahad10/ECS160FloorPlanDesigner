package elements;

import java.awt.*;

/**
 * Abstract interface representing a design element.
 *
 * @author ChatGPT
 */
public interface DesignElement {
    public void draw(Graphics2D g, Point start);
    public Rectangle getBounds();
}