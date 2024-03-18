package elements;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Storage implements DesignElement {
    private static final int DEFAULT_STORAGE_WIDTH = 30;
    private static final int DEFAULT_STORAGE_HEIGHT = 20;
    private static final int DEFAULT_STORAGE_DEPTH = 15;

    private int storageWidth = DEFAULT_STORAGE_WIDTH;
    private int storageHeight = DEFAULT_STORAGE_HEIGHT;
    private int storageDepth = DEFAULT_STORAGE_DEPTH;

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

        // Draw outer storage box
        g.drawRect(startPoint.x, startPoint.y, storageWidth, storageHeight);

        // Draw inner lines to indicate depth
        g.drawLine(startPoint.x, startPoint.y, startPoint.x - storageDepth, startPoint.y - storageDepth);
        g.drawLine(startPoint.x + storageWidth, startPoint.y, startPoint.x + storageWidth - storageDepth, startPoint.y - storageDepth);
        g.drawLine(startPoint.x, startPoint.y + storageHeight, startPoint.x - storageDepth, startPoint.y + storageHeight - storageDepth);
        g.drawLine(startPoint.x + storageWidth, startPoint.y + storageHeight, startPoint.x + storageWidth - storageDepth, startPoint.y + storageHeight - storageDepth);

        g.drawLine(startPoint.x - storageDepth, startPoint.y - storageDepth, startPoint.x - storageDepth, startPoint.y + storageHeight - storageDepth);
        g.drawLine(startPoint.x + storageWidth - storageDepth, startPoint.y - storageDepth, startPoint.x + storageWidth - storageDepth, startPoint.y + storageHeight - storageDepth);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startPoint.x - storageDepth, startPoint.y - storageDepth, storageWidth + storageDepth, storageHeight + storageDepth);
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
        storageWidth = (int) (scale * DEFAULT_STORAGE_WIDTH);
        storageHeight = (int) (scale * DEFAULT_STORAGE_HEIGHT);
        storageDepth = (int) (scale * DEFAULT_STORAGE_DEPTH);
    }

    @Override
    public void rotate(int angle) {
        rotationAngle = angle;
    }
}
