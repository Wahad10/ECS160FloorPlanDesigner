package elements;

import java.awt.*;

/**
 * Class representing a furniture design element.
 *
 * @author Wahad Latif
 */
public class Furniture implements DesignElement {

    private static final int FURNITURE_WIDTH = 40;
    private static final int FURNITURE_HEIGHT = 40;

    @Override
    public void draw(Graphics2D g, Point start) {
        g.setColor(Color.ORANGE);
        g.fillRect(start.x - FURNITURE_WIDTH / 2, start.y - FURNITURE_HEIGHT / 2, FURNITURE_WIDTH, FURNITURE_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0);
    }
}

