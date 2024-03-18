package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Table implements DesignElement {
    private static final int DEFAULT_TABLE_WIDTH = 100;
    private static final int DEFAULT_TABLE_HEIGHT = 60;
    private static final int DEFAULT_LEG_WIDTH = 5;

    private int tableWidth = DEFAULT_TABLE_WIDTH;
    private int tableHeight = DEFAULT_TABLE_HEIGHT;
    private int legWidth = DEFAULT_LEG_WIDTH;

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
            g.setColor(Color.DARK_GRAY);
        }
        g.setStroke(new BasicStroke(2));

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Calculate the center point of the desk
        int centerX = startPoint.x + tableWidth / 2;
        int centerY = startPoint.y + tableHeight / 2;

        // Translate to the center point
        g.translate(centerX, centerY);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Translate back to the original position
        g.translate(-centerX, -centerY);

        // Draw desk top (circle)
        int diameter = Math.min(tableWidth, tableHeight);
        g.drawOval(centerX - diameter/2, centerY - diameter/2, diameter, diameter);

        // Draw desk legs (four cardinal points)
        int legSize = legWidth;
        g.drawRect(centerX - diameter/2 + tableWidth / 3 - legWidth, startPoint.y, legSize, legSize); // North leg
        g.drawRect(centerX - diameter/2 + tableWidth / 3 - legWidth, startPoint.y + tableHeight - legSize, legSize, legSize); // South leg
        g.drawRect(centerX - diameter/2, startPoint.y + tableHeight / 2 - legWidth, legSize, legSize); // West leg
        g.drawRect(centerX - diameter/2 + tableWidth / 2 + legWidth, startPoint.y + tableHeight / 2 - legWidth, legSize, legSize); // East leg

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Shape getBounds() {
        // Calculate the center of the desk
        int centerX = startPoint.x + tableWidth / 2;
        int centerY = startPoint.y + tableHeight / 2;

        // Calculate the radius of the circle
        int radius = Math.min(tableWidth, tableHeight) / 2;

        // Create a circle shape
        return new java.awt.geom.Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
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
        tableWidth = (int) (scale * DEFAULT_TABLE_WIDTH);
        tableHeight = (int) (scale * DEFAULT_TABLE_HEIGHT);
        legWidth = (int) (scale * DEFAULT_LEG_WIDTH);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}

