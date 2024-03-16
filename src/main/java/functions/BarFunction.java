package functions;

import javax.swing.*;

import floorplan.DrawingPanel;
import floorplan.FunctionSelectedObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Class for sidebar
 * @author Wahad Latif and ChatGPT
 *
 */
public class BarFunction extends JPanel {
    private List<FunctionSelectedObserver> observers = new ArrayList<>();

    public BarFunction(DrawingPanel drawingPanel) {
        setLayout(new GridLayout(1, 5));
        Select selectFunction = new Select(drawingPanel);
        addFunction(selectFunction);
        addFunction(new Move(drawingPanel, selectFunction));
        addFunction(new Remove(drawingPanel, selectFunction));
        addFunction(new Rotate(drawingPanel, selectFunction));
        addFunction(new Resize(drawingPanel, selectFunction));
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