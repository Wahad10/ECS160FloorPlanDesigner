package floorplan;

import java.awt.*;

public class Door implements DesignElement {

  /**  public Door(Color color, int thickness) {
        super(color, thickness);
    }**/

    @Override
    public void draw(Graphics2D g, Point start, Point end) {
    	g.setColor(Color.RED);
        int diameter = Math.min(Math.abs(end.x - start.x), Math.abs(end.y - start.y));
        g.drawArc(start.x, start.y, diameter, diameter, 90, 90);
    }
    
    public void setStartPoint(Point start) {
    }
    
    public void setEndPoint(Point end) {
    }
}
