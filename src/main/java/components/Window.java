package components;

import java.awt.*;

/**
 * Class representing a window design element.
 *
 * @author ChatGPT
 */
public class Window implements DesignElement {

    private static final int WINDOW_WIDTH = 40;
    private static final int WINDOW_HEIGHT = 5;

    @Override
    public void draw(Graphics2D g, Point start, Point end) {
        g.setColor(Color.BLUE);
        g.fillRect(start.x - WINDOW_WIDTH / 2, start.y - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    public void setStartPoint(Point start) {
    }
    
    public void setEndPoint(Point end) {
    }
}

