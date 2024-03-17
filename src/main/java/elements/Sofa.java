package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sofa implements DesignElement {
    private static final int DEFAULT_SOFA_WIDTH = 60;
    private static final int DEFAULT_SOFA_HEIGHT = 30;
    private int sofaWidth = DEFAULT_SOFA_WIDTH;
    private int sofaHeight = DEFAULT_SOFA_HEIGHT;
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

        // Calculate the center point of the sofa
        int centerX = startPoint.x;
        int centerY = startPoint.y + sofaHeight / 2;

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the center point
        g.translate(centerX, centerY);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Translate back to the original position
        g.translate(-centerX, -centerY);

        // Draw sofa base outline
        g.drawRect(startPoint.x - sofaWidth / 2, startPoint.y, sofaWidth, sofaHeight);

        // Draw sofa backrest outline
        g.drawRect(startPoint.x - sofaWidth / 2, startPoint.y - sofaHeight / 2, sofaWidth, sofaHeight / 2);

        // Draw sofa arms outline
        g.drawRect(startPoint.x - sofaWidth / 2 - sofaWidth / 6, startPoint.y, sofaWidth / 6, sofaHeight);
        g.drawRect(startPoint.x + sofaWidth / 2, startPoint.y, sofaWidth / 6, sofaHeight);

        // Draw sofa legs outline
        g.drawRect(startPoint.x - sofaWidth / 2 - sofaWidth / 6, startPoint.y + sofaHeight, sofaWidth / 6, sofaHeight / 4);
        g.drawRect(startPoint.x + sofaWidth / 2, startPoint.y + sofaHeight, sofaWidth / 6, sofaHeight / 4);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        // Calculate the bounding box of the sofa
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotationAngle), startPoint.x, startPoint.y);
        Shape rotatedShape = transform.createTransformedShape(new Rectangle(startPoint.x - sofaWidth / 2, startPoint.y - sofaHeight / 2, sofaWidth, sofaHeight));
        return rotatedShape.getBounds();
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
        sofaWidth = (int) (scale * DEFAULT_SOFA_WIDTH);
        sofaHeight = (int) (scale * DEFAULT_SOFA_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}

