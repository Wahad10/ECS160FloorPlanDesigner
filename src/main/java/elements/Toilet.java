package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Toilet implements DesignElement {
    private static final int DEFAULT_TOILET_RADIUS_X = 20;
    private static final int DEFAULT_TOILET_RADIUS_Y = 15;
    private int toiletRadiusX = DEFAULT_TOILET_RADIUS_X;
    private int toiletRadiusY = DEFAULT_TOILET_RADIUS_Y;
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
            g.setColor(Color.BLACK);
        }
        g.setStroke(new BasicStroke(2));

        // Calculate the center point of the toilet
        int centerX = startPoint.x;
        int centerY = startPoint.y;

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the center point
        g.translate(centerX, centerY);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Translate back to the original position
        g.translate(-centerX, -centerY);

        // Draw oblate toilet body outline
        g.drawOval(startPoint.x - toiletRadiusX, startPoint.y - toiletRadiusY, 2 * toiletRadiusX, 2 * toiletRadiusY);

        // Draw toilet tank outline
        int tankWidth = toiletRadiusX / 2;
        int tankHeight = toiletRadiusY * 2;
        g.drawRect(startPoint.x + toiletRadiusX, startPoint.y - tankHeight / 2, tankWidth, tankHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - toiletRadiusX, startPoint.y - toiletRadiusY, 2 * toiletRadiusX, 2 * toiletRadiusY);
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
        toiletRadiusX = (int) (scale * DEFAULT_TOILET_RADIUS_X);
        toiletRadiusY = (int) (scale * DEFAULT_TOILET_RADIUS_Y);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
