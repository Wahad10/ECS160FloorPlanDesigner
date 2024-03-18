package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Lamp implements DesignElement {
    private static final int DEFAULT_BASE_WIDTH = 10;
    private static final int DEFAULT_BASE_HEIGHT = 20;
    private static final int DEFAULT_STAND_LENGTH = 30;
    private static final int DEFAULT_LAMP_WIDTH = 20;
    private static final int DEFAULT_LAMP_HEIGHT = 10;

    private int baseWidth = DEFAULT_BASE_WIDTH;
    private int baseHeight = DEFAULT_BASE_HEIGHT;
    private int standLength = DEFAULT_STAND_LENGTH;
    private int lampWidth = DEFAULT_LAMP_WIDTH;
    private int lampHeight = DEFAULT_LAMP_HEIGHT;

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
            g.setColor(Color.ORANGE);
        }
        g.setStroke(new BasicStroke(2));

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate and rotate the graphics context to draw the lamp at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        // Draw lamp base
        g.drawRect(-baseWidth / 2, 0, baseWidth, baseHeight);

        // Draw lamp stand
        g.drawLine(0, baseHeight, 0, baseHeight + standLength);

        // Draw lamp shade
        g.drawArc(-lampWidth / 2, baseHeight + standLength - lampHeight, lampWidth, lampHeight * 2, 0, 180);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - baseWidth / 2, startPoint.y, baseWidth, baseHeight + standLength + lampHeight);
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
        baseWidth = (int) (scale * DEFAULT_BASE_WIDTH);
        baseHeight = (int) (scale * DEFAULT_BASE_HEIGHT);
        standLength = (int) (scale * DEFAULT_STAND_LENGTH);
        lampWidth = (int) (scale * DEFAULT_LAMP_WIDTH);
        lampHeight = (int) (scale * DEFAULT_LAMP_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
