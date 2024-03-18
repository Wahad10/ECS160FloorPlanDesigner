package elements;

import javax.swing.*;

import floorplan.ElementSelectedObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Class for sidebar
 * @author Wahad Latif and ChatGPT
 *
 */
public class BarElement extends JPanel {
    private List<ElementSelectedObserver> observers = new ArrayList<>();

    public BarElement() {
        setLayout(new GridLayout(6, 3));
        addElement(new Wall());
        addElement(new DoorRight());
        addElement(new DoorLeft());
        addElement(new Window());
        addElement(new Bath());
        addElement(new Bed());
        addElement(new Chair());
        addElement(new Counter());
        addElement(new Desk());
        addElement(new Lamp());
        addElement(new Plant());
        addElement(new Sink());
        addElement(new Sofa());
        addElement(new Stairs());
        addElement(new Stove());
        addElement(new Table());
        addElement(new Toilet());
    }

    private void addElement(DesignElement element) {
        JButton button = new JButton(element.getClass().getSimpleName());

        button.addActionListener(e -> {
            notifyObservers(element);
        });
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