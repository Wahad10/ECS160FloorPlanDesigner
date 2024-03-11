package functions;

import java.awt.*;

import elements.*;
import floorplan.*;

public class Select implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private DesignElement selectedElement;

    public Select(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void performFunction(Point startPoint, Point endPoint) {
        if (startPoint == null || endPoint == null) {
            // Handle the null points gracefully (throw an exception, log a message, etc.)
            return;
        }

        // Iterate over the design elements and check if any are within the selection area
        for (DesignElement element : drawingPanel.getDesignElements()) {
            if (element.getBounds().intersects(new Rectangle(startPoint, new Dimension(endPoint.x - startPoint.x, endPoint.y - startPoint.y)))) {
                // Perform selection logic for the element
                selectedElement = element;
                // For example, you could set a selected flag in the element
                // and update the UI accordingly
                selectedElement.setSelected(true);
            }
        }
        // Redraw the canvas to reflect the selection changes
        drawingPanel.repaint();
    }

    public DesignElement getSelectedElement() {
        return selectedElement;
    }

    public void clearSelection() {
        if (selectedElement != null) {
            selectedElement.setSelected(false);
            selectedElement = null;
            drawingPanel.repaint();
        }
    }
}
