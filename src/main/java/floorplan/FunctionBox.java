package floorplan;

import javax.swing.*;

import functions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Class for sidebar
 * @author Wahad Latif and ChatGPT
 *
 */

public class FunctionBox extends JPanel {
    //private DrawingPanel drawingPanel;
    private List<FunctionSelectedObserver> observers = new ArrayList<>();

    public FunctionBox(DrawingPanel drawingPanel) {
        //this.drawingPanel = drawingPanel;

        setLayout(new GridLayout(1, 4));
        Select selectFunction = new Select(drawingPanel);
        addFunction(selectFunction);
        addFunction(new Move(drawingPanel, selectFunction));
        //addFunction(new Rotate(drawingPanel));
        //addFunction(new Resize(drawingPanel));
        //Add other functions, MOVE, REMOVE, ROTATE, RESIZE
    }

    private void addFunction(ManipulationFunction function) {
        JButton button = new JButton(function.getClass().getSimpleName());
        button.addActionListener(e -> notifyObservers(function));
        add(button);
    }

    public void addObserver(FunctionSelectedObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(ManipulationFunction function) {
        for (FunctionSelectedObserver observer : observers) {
            observer.onFunctionSelected(function);
        }
    }
}