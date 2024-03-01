package functions;

import java.awt.*;

import elements.*;
import floorplan.*;

public class Select implements ManipulationFunction {
    private DrawingPanel drawingPanel;

    public Select(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void performFunction(Point startPoint, Point endPoint) {
        // Iterate over the design elements and check if any are within the selection area
        for (DesignElement element : drawingPanel.getDesignElements()) {
            if (element.getBounds().intersects(new Rectangle(startPoint, new Dimension(endPoint.x - startPoint.x, endPoint.y - startPoint.y)))) {
                // Perform selection logic for the element
                // For example, you could set a selected flag in the element
                // and update the UI accordingly
            }
        }
        // Redraw the canvas to reflect the selection changes
        drawingPanel.repaint();
    }

}
