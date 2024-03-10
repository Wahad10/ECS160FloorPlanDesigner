package elements;

import java.awt.*;

/**
 * Class representing a table design element.
 *
 * @author Wahad Latif
 */
public class Table implements DesignElement {

    private static final int TABLE_WIDTH = 80;
    private static final int TABLE_HEIGHT = 40;

    @Override
    public void draw(Graphics2D g, Point start) {
        g.setColor(Color.YELLOW);
        g.fillRect(start.x - TABLE_WIDTH / 2, start.y - TABLE_HEIGHT / 2, TABLE_WIDTH, TABLE_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0);
    }
}