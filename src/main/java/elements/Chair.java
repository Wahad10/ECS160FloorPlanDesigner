package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Chair implements DesignElement {
    private static final int DEFAULT_CHAIR_WIDTH = 50;
    private static final int DEFAULT_CHAIR_HEIGHT = 50;

    private int chairWidth = DEFAULT_CHAIR_WIDTH;
    private int chairHeight = DEFAULT_CHAIR_HEIGHT;

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

        // Calculate center point of the chair
        int centerX = startPoint.x + chairWidth / 2;
        int centerY = startPoint.y + chairHeight / 2;

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the center point
        g.translate(centerX, centerY);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Translate back to the original position
        g.translate(-centerX, -centerY);

        // Draw chair backrest
        int backrestWidth = chairWidth;
        int backrestHeight = chairHeight / 3;
        g.drawRect(startPoint.x, startPoint.y, backrestWidth, backrestHeight);

        // Draw chair seat
        int seatWidth = chairWidth;
        int seatHeight = chairHeight / 5;
        g.drawRect(startPoint.x, startPoint.y + backrestHeight, seatWidth, seatHeight);

        // Draw chair legs
        int legWidth = chairWidth / 10;
        int legHeight = chairHeight / 2;
        g.drawRect(startPoint.x, startPoint.y + backrestHeight + seatHeight, legWidth, legHeight);
        g.drawRect(startPoint.x + chairWidth - legWidth, startPoint.y + backrestHeight + seatHeight, legWidth, legHeight);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x, startPoint.y, chairWidth, chairHeight);
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
        chairWidth = (int) (scale * DEFAULT_CHAIR_WIDTH);
        chairHeight = (int) (scale * DEFAULT_CHAIR_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
