package functions;

import java.awt.*;
import javax.swing.*;
import java.util.Hashtable;

import floorplan.*;

public class Resize extends JSlider implements ManipulationFunction {
    private DrawingPanel drawingPanel;
    private Select selectFunction;

    public Resize(DrawingPanel drawingPanel, Select selectFunction) {
        super(JSlider.HORIZONTAL, 0, 1000, 100); // Assuming original size is at 100
        this.drawingPanel = drawingPanel;
        this.selectFunction = selectFunction;
        
        setMajorTickSpacing(100);
        setPaintTicks(true);
        setPaintLabels(true);

        // Customize the labels to show 1 instead of 100 and 10 instead of 1000
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("0"));
        labelTable.put(100, new JLabel("1"));
        labelTable.put(200, new JLabel("2"));
        labelTable.put(300, new JLabel("3"));
        labelTable.put(400, new JLabel("4"));
        labelTable.put(500, new JLabel("5"));
        labelTable.put(600, new JLabel("6"));
        labelTable.put(700, new JLabel("7"));
        labelTable.put(800, new JLabel("8"));
        labelTable.put(900, new JLabel("9"));
        labelTable.put(1000, new JLabel("10"));
        setLabelTable(labelTable);

        setVisible(false);

        // Add a listener to handle resizing
        addChangeListener(e -> performFunction(new Point()));/*{
            double scaleFactor = (double) getValue() / 100; // Scale factor from 0.0 to 10.0
            // Resize the selected design element
            if (selectFunction.selectedElement != null) {
                selectFunction.selectedElement.resize(scaleFactor);
                drawingPanel.repaint();
            }
        });*/
    }

    @Override
    public void performFunction(Point clickedPoint) {
        double scaleFactor = (double) getValue() / 100; // Scale factor from 0.0 to 10.0
        // Resize the selected design element
        if (selectFunction.selectedElement != null) {
            selectFunction.selectedElement.resize(scaleFactor);
            drawingPanel.repaint();
        }

        /**
        // Handle the null points gracefully (throw an exception, log a message, etc.)
        if (clickedPoint == null) {return;}

        //Perform resize logic for the element
        if (selectFunction.selectedElement != null) {
            Rectangle bounds = selectFunction.selectedElement.getBounds();
            int newWidth = Math.abs(clickedPoint.x - bounds.x);
            int newHeight = Math.abs(clickedPoint.y - bounds.y);
            //bounds.setSize(newWidth, newHeight);
            // You may need to adjust the resize logic based on how you want the element to resize
            selectFunction.selectedElement.resize(newWidth, newHeight);

            selectFunction.clearSelection();
        }

         // Perform resize logic for the element
        if (selectFunction.selectedElement != null) {
            Point start = selectFunction.selectedElement.getStartPoint();
            //int newWidth = Math.abs(clickedPoint.x - start.x);
            //int newHeight = Math.abs(clickedPoint.y - start.y);
            //selectFunction.selectedElement.resize(scale);

            selectFunction.clearSelection();
        }

        // Redraw the canvas to reflect the changes
        drawingPanel.repaint();
        **/
    }
}
