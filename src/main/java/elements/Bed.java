package elements;

import java.awt.*;

/**
 * Class representing a chair design element.
 *
 * @author Wahad Latif
 */
public class Bed implements DesignElement {

    private static final int BED_WIDTH = 40;
    private static final int BED_HEIGHT = 80;

    @Override
    public void draw(Graphics2D g, Point start) {
        g.setColor(Color.RED);
        g.fillRect(start.x - BED_WIDTH / 2, start.y - BED_HEIGHT / 2, BED_WIDTH, BED_HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0);
    }
}