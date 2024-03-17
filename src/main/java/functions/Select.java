package functions;

import java.util.List;
import java.awt.*;

import elements.*;
import floorplan.*;

public class Select implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    public DesignElement selectedElement;

    public Select(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void performFunction(Point clickedPoint) {
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (clickedPoint == null) {return;}

        // Already selected something
        if (selectedElement != null) {return;}

        //Iterate over the design elements and check if any are within the selection area
        selectedElement = getSelectedElement(clickedPoint);

        // Perform selection logic for the element
        if (selectedElement != null) {
            // For example, you could set a selected flag in the element
            // and update the UI accordingly
            selectedElement.setSelected(true);
        }

        // Redraw the canvas to reflect the selection changes
        drawingPanel.repaint();
    }

    private DesignElement getSelectedElement(Point point) {
        List<DesignElement> elements = drawingPanel.getDesignElements();
        for (int i = elements.size() - 1; i >= 0; i--) {
            DesignElement element = elements.get(i);
            Shape bounds = element.getBounds();
            if (bounds.contains(point)) {
                return element;
            }
        }
        return null;
    }

    public void clearSelection() {
        if (selectedElement != null) {
            selectedElement.setSelected(false);
            selectedElement = null;
            drawingPanel.repaint();
        }
    }
}
