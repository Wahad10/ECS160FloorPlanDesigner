package functions;

import java.util.*;
import java.util.List;
import java.awt.*;

import floorplan.*;
import elements.*;

public class Remove implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private Select selectFunction;

    public Remove(DrawingPanel drawingPanel, Select selectFunction) {
        this.drawingPanel = drawingPanel;
        this.selectFunction = selectFunction;
    }

    @Override
    public void performFunction(Point point) {
        // Perform move logic for the element
        if (selectFunction.selectedElements != null) {
            //Removing it
            List<DesignElement> elements = drawingPanel.getDesignElements();
            Iterator<DesignElement> iterator = elements.iterator();
            while (iterator.hasNext()) {
                DesignElement element = iterator.next();
                if (element.isSelected()) {
                    iterator.remove();
                }
            }

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
    }
}
