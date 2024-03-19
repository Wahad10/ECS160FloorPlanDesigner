package functions;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import elements.*;
import floorplan.*;

public class Select implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    public List<DesignElement> selectedElements;
    public Point startPoint;
    public Point endPoint;

    public Select(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.selectedElements = new ArrayList<>();
    }

    @Override
    public void performFunction(Point draggedPoint) {
        List<DesignElement> elements = drawingPanel.getDesignElements();

        Rectangle selectionRect = new Rectangle(startPoint);
        endPoint = draggedPoint;
        selectionRect.add(endPoint);

        for (DesignElement element : elements) {
            if (element.getBounds().intersects(selectionRect)) {
                if (!selectedElements.contains(element)) {
                    selectedElements.add(element);
                    element.setSelected(true);
                }
            }
        }
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
