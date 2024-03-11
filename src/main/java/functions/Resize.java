package functions;

import java.awt.*;

import floorplan.*;

public class Resize implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private Select selectFunction;

    public Resize(DrawingPanel drawingPanel, Select selectFunction) {
        this.drawingPanel = drawingPanel;
        this.selectFunction = selectFunction;
    }

    @Override
    public void performFunction(Point clickedPoint) {
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (clickedPoint == null) {return;}

        // Perform move logic for the element
        if (selectFunction.selectedElement != null) {
            //Get user input of new point to move to (moving to clicked point)
            selectFunction.selectedElement.setStartPoint(clickedPoint);

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
    }
}
