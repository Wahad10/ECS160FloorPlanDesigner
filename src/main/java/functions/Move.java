package functions;

import java.awt.*;

import elements.DesignElement;
import floorplan.*;

public class Move implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private Select selectFunction;
    public Point startDragPoint;

    public Move(DrawingPanel drawingPanel, Select selectFunction) {
        this.drawingPanel = drawingPanel;
        this.selectFunction = selectFunction;
    }

    @Override
    public void performFunction(Point clickedPoint) {
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (clickedPoint == null) {return;}

        // Perform move logic for the element
        if (selectFunction.selectedElements != null) {
            //Get user input of new point to move to (moving to clicked point)
            ///selectFunction.selectedElements.setStartPoint(clickedPoint);
            for (DesignElement element : selectFunction.selectedElements) {
                element.setStartPoint(clickedPoint);
            }

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
    }

    public void performBetterFunction(Point draggedPoint) {
        if (draggedPoint == null) {
            return;
        }
    
        if (selectFunction.selectedElements != null) {
            int dx = draggedPoint.x - startDragPoint.x;
            int dy = draggedPoint.y - startDragPoint.y;
    
            for (DesignElement element : selectFunction.selectedElements) {
                Point start = element.getStartPoint();
                Point newStart = new Point(start.x + dx, start.y + dy);
                element.setStartPoint(newStart);
            }
    
            startDragPoint = draggedPoint;
    
            // Redraw the canvas to reflect the changes
            drawingPanel.repaint();
        }
    }
}
