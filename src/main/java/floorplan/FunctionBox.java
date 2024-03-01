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
    private DrawingPanel drawingPanel;
    private List<FunctionSelectedObserver> observers = new ArrayList<>();

    public FunctionBox(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        setLayout(new GridLayout(1, 4));
        addFunction(new Select(drawingPanel));
        //addElement(new Wall(Color.BLACK, 3));
        //addElement(new Door());
        //addElement(new Window());
        //addElement(new Furniture());
        //addElement(new FreeWall(Color.BLACK, 3));
        //addElement(new Wall(Color.BLACK, 3));
        //addElement(new Door(Color.RED, 10));
        // Add other design elements as needed
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