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

        // Save the current graphics transformation
        AffineTransform oldTransform = g.getTransform();

        // Translate to the starting point
        g.translate(startPoint.x, startPoint.y);

        // Rotate the graphics context
        g.rotate(Math.toRadians(rotationAngle));

        // Draw sink
        g.drawRect(0, 0, sinkWidth, sinkHeight);

        // Draw faucet
        g.drawRect(sinkWidth / 2 - faucetSize / 2, -faucetSize, faucetSize, faucetSize);

        // Restore the old graphics transformation
        g.setTransform(oldTransform);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x, startPoint.y, sinkWidth, sinkHeight);
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