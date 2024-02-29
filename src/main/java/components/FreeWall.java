package components;

import java.awt.*;

/**
 * Represents a line design element.
 * 
 * @author ChatGPT
 */
public class FreeWall implements DesignElement {

    /**public FreeWall(Color color, int thickness) {
        super(color, thickness);
    }**/

    @Override
    public void draw(Graphics2D g, Point start, Point end) {
        //g.setColor(color);
    	g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g.drawLine(start.x, start.y, end.x, end.y);
    }
    
    public void setStartPoint(Point start) {
    }
    
    public void setEndPoint(Point end) {
    }
}
