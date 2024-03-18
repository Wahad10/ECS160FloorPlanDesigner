package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Stairs implements DesignElement {
    private static final int DEFAULT_STAIRS_WIDTH = 30;
    private static final int DEFAULT_STAIRS_HEIGHT = 50;
    private static final int DEFAULT_STEP_WIDTH = 5;
    private static final int DEFAULT_STEP_HEIGHT = 10;
    private static final int MORTAR_WIDTH = 1;

    private int stairsWidth = DEFAULT_STAIRS_WIDTH;
    private int stairsHeight = DEFAULT_STAIRS_HEIGHT;
    private int stepWidth = DEFAULT_STEP_WIDTH;
    private int stepHeight = DEFAULT_STEP_HEIGHT;

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

        // Translate and rotate the graphics context to draw the stairs at the desired position and angle
        g.translate(startPoint.x, startPoint.y);
        g.rotate(Math.toRadians(rotationAngle));

        // Draw masonry stairs
        drawSteps(g);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    private void drawSteps(Graphics2D g) {
        // Draw steps and mortar joints
        int numStepsX = stairsWidth / (stepWidth + MORTAR_WIDTH);
        int numStepsY = stairsHeight / (stepHeight + MORTAR_WIDTH);

        int startX = -stairsWidth / 2;
        int startY = -stairsHeight;
        for (int i = 0; i < numStepsY; i++) {
            for (int j = 0; j < numStepsX * 2 + 1; j++) {
                int stepX = startX + j * (stepWidth + MORTAR_WIDTH);
                int stepY = startY + i * (stepHeight + MORTAR_WIDTH);
                g.fillRect(stepX, stepY, stepWidth, stepHeight);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - stairsWidth / 2, startPoint.y - stairsHeight, stairsWidth, stairsHeight);
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
        stairsWidth = (int) (scale * DEFAULT_STAIRS_WIDTH);
        stairsHeight = (int) (scale * DEFAULT_STAIRS_HEIGHT);
        stepWidth = (int) (scale * DEFAULT_STEP_WIDTH);
        stepHeight = (int) (scale * DEFAULT_STEP_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
