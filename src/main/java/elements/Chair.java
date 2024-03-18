package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Chair implements DesignElement {
    private static final int DEFAULT_CHAIR_WIDTH = 30;
    private static final int DEFAULT_CHAIR_HEIGHT = 30;

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
    public Shape getBounds() {
        // Calculate the center of the chair
        int centerX = startPoint.x + chairWidth / 2;
        int centerY = startPoint.y + chairHeight / 2;

        // Calculate the coordinates of the corners of the unrotated rectangle
        int x1 = -chairWidth / 2;
        int y1 = -chairHeight / 2;
        int x2 = chairWidth / 2;
        int y2 = -chairHeight / 2;
        int x3 = chairWidth / 2;
        int y3 = chairHeight / 2;
        int x4 = -chairWidth / 2;
        int y4 = chairHeight / 2;

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
        chairWidth = (int) (scale * DEFAULT_CHAIR_WIDTH);
        chairHeight = (int) (scale * DEFAULT_CHAIR_HEIGHT);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
