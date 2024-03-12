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

        /** Perform resize logic for the element
        if (selectFunction.selectedElement != null) {
            Rectangle bounds = selectFunction.selectedElement.getBounds();
            int newWidth = Math.abs(clickedPoint.x - bounds.x);
            int newHeight = Math.abs(clickedPoint.y - bounds.y);
            //bounds.setSize(newWidth, newHeight);
            // You may need to adjust the resize logic based on how you want the element to resize
            selectFunction.selectedElement.resize(newWidth, newHeight);

            selectFunction.clearSelection();
        }**/

         // Perform resize logic for the element
        if (selectFunction.selectedElement != null) {
            Point start = selectFunction.selectedElement.getStartPoint();
            int newWidth = Math.abs(clickedPoint.x - start.x);
            int newHeight = Math.abs(clickedPoint.y - start.y);
            selectFunction.selectedElement.resize(newWidth, newHeight);

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
    }
}
