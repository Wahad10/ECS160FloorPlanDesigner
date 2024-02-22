package floorplan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Wahad Latif and ChatGPT
 *
 */

public class Toolbox extends JPanel {

    private List<ElementSelectedObserver> observers = new ArrayList<>();

    public Toolbox() {
        setLayout(new GridLayout(4, 1));
        addElement(new FreeWall());
        addElement(new Wall(Color.BLACK, 3));
        addElement(new Door());
        //addElement(new FreeWall(Color.BLACK, 3));
        //addElement(new Wall(Color.BLACK, 3));
        //addElement(new Door(Color.RED, 10));
        // Add other design elements as needed
    }

    private void addElement(DesignElement element) {
        JButton button = new JButton(element.getClass().getSimpleName());
        button.addActionListener(e -> notifyObservers(element));
        add(button);
    }

    public void addObserver(ElementSelectedObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(DesignElement element) {
        for (ElementSelectedObserver observer : observers) {
            observer.onElementSelected(element);
        }
    }
}