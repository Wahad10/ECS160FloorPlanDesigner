package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bath implements DesignElement {
    private static final int DEFAULT_BATH_WIDTH = 80;
    private static final int DEFAULT_BATH_HEIGHT = 150;

    private int bathWidth = DEFAULT_BATH_WIDTH;
    private int bathHeight = DEFAULT_BATH_HEIGHT;

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
            g.setColor(Color.BLUE);
        }
        g.setStroke(new BasicStroke(2));

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the starting point
        g.translate(startPoint.x, startPoint.y);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Draw bath
        g.drawRect(0, 0, bathWidth, bathHeight);

        // Calculate tub dimensions
        int tubWidth = bathWidth - 15;
        int tubHeight = bathHeight - 15;
        int tubX = (bathWidth - tubWidth) / 2;
        int tubY = (bathHeight - tubHeight) / 2;

        // Draw tub
        g.drawRect(tubX, tubY, tubWidth, tubHeight);

        // Draw drain (oblate circle)
        int drainSize = Math.min(bathWidth, bathHeight) / 8;
        int drainX = (bathWidth - drainSize) / 2;
        int drainY = (bathHeight - drainSize) / 6;
        g.drawOval(drainX, drainY, drainSize, drainSize);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x, startPoint.y, bathWidth, bathHeight);
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
        bathWidth = (int) (scale * DEFAULT_BATH_WIDTH);
        bathHeight = (int) (scale * DEFAULT_BATH_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
