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
        setLayout(new GridLayout(9, 2));
        addElement(new Wall());
        addElement(new DoorRight());
        addElement(new DoorLeft());
        addElement(new Window());
        addElement(new Bath());
        addElement(new Bed());
        addElement(new Chair());
        addElement(new Counter());
        addElement(new Desk());
        addElement(new Fridge());
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
        String buttonName = element.getClass().getSimpleName();
        if(buttonName.equals("DoorRight")){
            buttonName = "<html>Door<br />Right</html>";
        }
        if(buttonName.equals("DoorLeft")){
            buttonName = "<html>Door<br />Left</html>";
        }
        JButton button = new JButton(buttonName);

        // Set a preferred size for the button
        button.setPreferredSize(new Dimension(60, 40)); // Adjust the dimensions as needed

        // Set a margin to provide padding around the text
        button.setMargin(new Insets(3, 3, 3, 3)); // Adjust the insets as needed

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