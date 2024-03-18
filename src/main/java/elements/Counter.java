package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Counter implements DesignElement {
    private static final int DEFAULT_COUNTER_WIDTH = 80;
    private static final int DEFAULT_COUNTER_HEIGHT = 50;

    private int counterWidth = DEFAULT_COUNTER_WIDTH;
    private int counterHeight = DEFAULT_COUNTER_HEIGHT;

    private Point startPoint;
    private boolean isSelected = false;
    private int rotationAngle = 0;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        if (isSelected) {
            g.setColor(Color.MAGENTA);
        } else {
            g.setColor(Color.GRAY);
        }
        g.setStroke(new BasicStroke(2));

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the starting point
        g.translate(startPoint.x, startPoint.y);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Draw counter
        g.drawRect(0, 0, counterWidth, counterHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x, startPoint.y, counterWidth, counterHeight);
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
    public void resize(double scale) {
        counterWidth = (int) (scale * DEFAULT_COUNTER_WIDTH);
        counterHeight = (int) (scale * DEFAULT_COUNTER_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
