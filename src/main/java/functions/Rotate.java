package functions;

import java.awt.*;
import javax.swing.*;

import elements.DesignElement;
import floorplan.*;

/**
 * Class representing a rotate function
 *
 * @author ChatGPT, Wahad Latif
 */
public class Rotate extends JSlider implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private Select selectFunction;

    public Rotate(DrawingPanel drawingPanel, Select selectFunction) {
        super(JSlider.HORIZONTAL, 0, 360, 0); // Assuming original angle at 0
        this.drawingPanel = drawingPanel;
        this.selectFunction = selectFunction;
        
        setMajorTickSpacing(90);
        setPaintTicks(true);
        setPaintLabels(true);

        //Hide slider initially
        setVisible(false);

        // Add a listener to handle resizing
        addChangeListener(e -> performFunction(new Point()));
    }

    @Override
    public void performFunction(Point point) {
        int rotateFactor = getValue(); // Rotate factor from 0 to 360

        // Rotate the selected design element
        if (selectFunction.selectedElements != null) {
            for (DesignElement element : selectFunction.selectedElements) {
                element.rotate(rotateFactor);
            } 
        }

        //Update drawing panel and make sure it still recieves keyboard input (not this slider)
        drawingPanel.repaint();
        drawingPanel.requestFocusInWindow();
    }
}
