package floorplan;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Furniture implements DesignElement {

    private static final int FURNITURE_WIDTH = 40;
    private static final int FURNITURE_HEIGHT = 40;

    @Override
    public void draw(Graphics2D g, Point start, Point end) {
        g.setColor(Color.ORANGE);
        g.fillRect(start.x - FURNITURE_WIDTH / 2, start.y - FURNITURE_HEIGHT / 2, FURNITURE_WIDTH, FURNITURE_HEIGHT);
    }
    
    public void setStartPoint(Point start) {
    }
    
    public void setEndPoint(Point end) {
    }
}

