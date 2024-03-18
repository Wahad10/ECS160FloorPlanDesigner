package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sink implements DesignElement {
    private static final int DEFAULT_SINK_WIDTH = 60;
    private static final int DEFAULT_SINK_HEIGHT = 40;
    private static final int DEFAULT_FAUCET_SIZE = 10;

    private int sinkWidth = DEFAULT_SINK_WIDTH;
    private int sinkHeight = DEFAULT_SINK_HEIGHT;
    private int faucetSize = DEFAULT_FAUCET_SIZE;

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

         // Calculate center point
         int centerX = startPoint.x + sinkWidth / 2;
         int centerY = startPoint.y + sinkHeight / 2;
 
         // Save the current graphics transformation
         AffineTransform oldTransform = g.getTransform();
 
         // Translate to the center point
         g.translate(centerX, centerY);
 
         // Rotate the graphics context
         g.rotate(Math.toRadians(rotationAngle));
 
         // Translate back to original position
         g.translate(-centerX, -centerY);

        // Draw sink
        g.drawRect(startPoint.x, startPoint.y, sinkWidth, sinkHeight);

        // Draw faucet
        g.drawRect(startPoint.x + sinkWidth / 2 - faucetSize / 2, startPoint.y-faucetSize, faucetSize, faucetSize);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }


    @Override
    public Shape getBounds() {
        // Calculate the center of the bath
        int centerX = startPoint.x + sinkWidth / 2;
        int centerY = startPoint.y + sinkHeight / 2;

        // Calculate the coordinates of the corners of the unrotated rectangle
        int x1 = -sinkWidth / 2;
        int y1 = -sinkHeight / 2;
        int x2 = sinkWidth / 2;
        int y2 = -sinkHeight / 2;
        int x3 = sinkWidth / 2;
        int y3 = sinkHeight / 2;
        int x4 = -sinkWidth / 2;
        int y4 = sinkHeight / 2;

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
        sinkWidth = (int) (scale * DEFAULT_SINK_WIDTH);
        sinkHeight = (int) (scale * DEFAULT_SINK_HEIGHT);
        faucetSize = (int) (scale * DEFAULT_FAUCET_SIZE);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}