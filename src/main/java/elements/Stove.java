package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Stove implements DesignElement {
    private static final int DEFAULT_STOVE_WIDTH = 60;
    private static final int DEFAULT_STOVE_HEIGHT = 40;
    private int stoveWidth = DEFAULT_STOVE_WIDTH;
    private int stoveHeight = DEFAULT_STOVE_HEIGHT;
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

        // Translate and rotate the graphics context to draw the stove at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        // Draw stove body outline
        g.drawRect(-stoveWidth / 2, -stoveHeight / 2, stoveWidth, stoveHeight);

        // Draw stove burners outline
        int burnerSize = Math.min(stoveWidth, stoveHeight) / 4;
        int burnerX = -stoveWidth / 3;
        int burnerY = -stoveHeight / 3;
        for (int i = 0; i < 2; i++) {
            g.drawOval(burnerX, burnerY, burnerSize, burnerSize);
            g.drawOval(burnerX, burnerY + stoveHeight / 3, burnerSize, burnerSize);
            burnerX += stoveWidth / 2;
        }

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - stoveWidth / 2, startPoint.y - stoveHeight / 2, stoveWidth, stoveHeight);
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
        stoveWidth = (int) (scale * DEFAULT_STOVE_WIDTH);
        stoveHeight = (int) (scale * DEFAULT_STOVE_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}