package functions;

import java.awt.*;

import elements.DesignElement;
import floorplan.*;
import elements.*;

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
                //element.setStartPoint(clickedPoint);
            }

            System.out.println("Moved");

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
    }

    public void performBetterFunction(Point draggedPoint) {
        /** 
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (draggedPoint == null || startDragPoint == null) {
            return;
        }

        if (selectFunction.selectedElements != null) {
            int dx = draggedPoint.x - startDragPoint.x;
            int dy = draggedPoint.y - startDragPoint.y;
            System.out.println("Moving");

            // Calculate the total distance the mouse has been dragged
            double dragDistance = Math.sqrt(dx * dx + dy * dy);

            for (DesignElement element : selectFunction.selectedElements) {
                Point start = element.getStartPoint();

                // Normalize the movement vector to ensure consistent movement speed
                int normalizedDx = (int) (dx * (1.0 / dragDistance));
                int normalizedDy = (int) (dy * (1.0 / dragDistance));

                // Move the element by the normalized movement vector
                element.setStartPoint(new Point(start.x + normalizedDx, start.y + normalizedDy));
            }

            startDragPoint = draggedPoint;

            // Redraw the canvas to reflect the changes
            drawingPanel.repaint();
        }
        */
        /* 
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (draggedPoint == null) {return;}

        if (selectFunction.selectedElements != null) {
            int dx = draggedPoint.x - startDragPoint.x;
            int dy = draggedPoint.y - startDragPoint.y;
            System.out.println("Moving");
    

            for (DesignElement element : selectFunction.selectedElements) {
                Point start = element.getStartPoint();
                element.setStartPoint(new Point(start.x + dx, start.y + dy));
                System.out.println("START");
                System.out.println(start.x);
                System.out.println(dx);
                System.out.println(start.y);
                System.out.println(dy);
                System.out.println("END");
                //((Bath)element).updateStartPoint(dx, dy);
            }

            startDragPoint = draggedPoint;

            // Redraw the canvas to reflect the changes
            drawingPanel.repaint();
        }
        */
        if (draggedPoint == null) {
            return;
        }
    
        if (selectFunction.selectedElements != null) {
            int dx = draggedPoint.x - startDragPoint.x;
            int dy = draggedPoint.y - startDragPoint.y;
            System.out.println("Moving");

            for (DesignElement element : selectFunction.selectedElements) {
                System.out.println(element);
            }
    
            for (DesignElement element : selectFunction.selectedElements) {
                Point start = element.getStartPoint();
                Point newStart = new Point(start.x + dx, start.y + dy);
                element.setStartPoint(newStart);
                System.out.println("START");
                System.out.println(start.x);
                System.out.println(dx);
                System.out.println(start.y);
                System.out.println(dy);
                System.out.println("END");
            }
    
            startDragPoint = draggedPoint;
    
            // Redraw the canvas to reflect the changes
            drawingPanel.repaint();
        }
        /** 
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (draggedPoint == null) {return;}

        // Perform move logic for the element
        if (selectFunction.selectedElements != null) {
            //Get user input of new point to move to (moving to clicked point)
            ///selectFunction.selectedElements.setStartPoint(clickedPoint);
            for (DesignElement element : selectFunction.selectedElements) {
                element.setStartPoint(draggedPoint);
            }

            System.out.println("Moving");

            //selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
        */
    }
}
