package functions;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import elements.*;
import floorplan.*;

public class Select implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    //public DesignElement selectedElements;
    public List<DesignElement> selectedElements;
    public Point startPoint;
    public Point endPoint;

    public Select(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.selectedElements = new ArrayList<>();
    }

    @Override
    public void performFunction(Point clickedPoint) {
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (clickedPoint == null) {return;}

        // Already selected something
        //if (selectedElement != null) {return;}

        //Iterate over the design elements and check if any are within the selection area
        DesignElement selectedElement = getSelectedElement(clickedPoint);
        if(selectedElement != null){
            selectedElements.add(selectedElement);
            selectedElement.setSelected(true);
        }
        

        // Perform selection logic for the element
       // if (selectedElement != null) {
            // For example, you could set a selected flag in the element
            // and update the UI accordingly
            //selectedElement.setSelected(true);
        //}

        //for (DesignElement element : selectedElements) {
        //    element.setSelected(true);
        //}

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

    public void selectElements() {
        List<DesignElement> elements = drawingPanel.getDesignElements();
        Rectangle selectionRect = new Rectangle(startPoint);
        selectionRect.add(endPoint);

        List<DesignElement> elementsToRemove = new ArrayList<>();
        for (DesignElement element : elements) {
            if (element.getBounds().intersects(selectionRect)) {
                if (!selectedElements.contains(element)) {
                    selectedElements.add(element);
                    element.setSelected(true);
                }
            }
            if (!element.getBounds().intersects(selectionRect)) {
                //WHY THIS NOT WORK?? selectedElements.remove(element);
                elementsToRemove.add(element);
                element.setSelected(false);
            }
        }

        // Remove elements that are not intersecting the selection rectangle
        selectedElements.removeAll(elementsToRemove);
    }

    public void draw(Graphics2D g) {
        if (startPoint != null && endPoint != null) {
            g.setColor(new Color(0, 0, 255, 100)); // Transparent blue
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);
            g.fill(new Rectangle(x, y, width, height));
        }
    }

    public void clearSelection() {
        for (DesignElement element : selectedElements) {
            element.setSelected(false);
        }
        selectedElements.clear();
        drawingPanel.repaint();
    }
}
