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

        // Calculate center point
        int centerX = startPoint.x + counterWidth / 2;
        int centerY = startPoint.y + counterHeight / 2;

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the center point
        g.translate(centerX, centerY);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Translate back to the original position
        g.translate(-centerX, -centerY);

        // Draw counter
        g.drawRect(startPoint.x, startPoint.y, counterWidth, counterHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Shape getBounds() {
        // Calculate the center of the bath
        int centerX = startPoint.x + counterWidth / 2;
        int centerY = startPoint.y + counterHeight / 2;

        // Calculate the coordinates of the corners of the unrotated rectangle
        int x1 = -counterWidth / 2;
        int y1 = -counterHeight / 2;
        int x2 = counterWidth / 2;
        int y2 = -counterHeight / 2;
        int x3 = counterWidth / 2;
        int y3 = counterHeight / 2;
        int x4 = -counterWidth / 2;
        int y4 = counterHeight / 2;

        // Apply the rotation to each corner
        double cosTheta = Math.cos(Math.toRadians(rotationAngle));
        double sinTheta = Math.sin(Math.toRadians(rotationAngle));

        int[] xPoints = {(int) (x1 * cosTheta - y1 * sinTheta), (int) (x2 * cosTheta - y2 * sinTheta),
                (int) (x3 * cosTheta - y3 * sinTheta), (int) (x4 * cosTheta - y4 * sinTheta)};
        int[] yPoints = {(int) (x1 * sinTheta + y1 * cosTheta), (int) (x2 * sinTheta + y2 * cosTheta),
                (int) (x3 * sinTheta + y3 * cosTheta), (int) (x4 * sinTheta + y4 * cosTheta)};

        // Translate the rotated points to the center of the chair
        for (int i = 0; i < 4; i++) {
            xPoints[i] += centerX;
            yPoints[i] += centerY;
        }

        // Create a polygon from the rotated corners
        Polygon polygon = new Polygon(xPoints, yPoints, 4);

        return polygon;
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
