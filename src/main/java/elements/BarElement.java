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
        setLayout(new GridLayout(4, 1));
        addElement(new Wall());
        addElement(new Door());
        addElement(new Window());
        addElement(new Furniture());
    }

    private void addElement(DesignElement element) {
        JButton button = new JButton(element.getClass().getSimpleName());
        
        button.addActionListener(e -> {
            if (element instanceof Furniture) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem chairItem = new JMenuItem("Bed");
                chairItem.addActionListener(ev -> {
                    notifyObservers(new Bed());
                });
                JMenuItem tableItem = new JMenuItem("Table");
                tableItem.addActionListener(ev -> {
                    notifyObservers(new Table());
                });
                JMenuItem plantItem = new JMenuItem("Plant");
                plantItem.addActionListener(ev -> {
                    notifyObservers(new Plant());
                });
                // Add more furniture items as needed
    
                menu.add(chairItem);
                menu.add(tableItem);
                menu.add(plantItem);
                // Add more furniture items to the menu

                //int x = button.getLocationOnScreen().x - button.getWidth();
                //int y = button.getLocationOnScreen().y + button.getHeight();
                //menu.show(button, 0, y);
    
                menu.show(button, 0, 0);//button.getHeight());
            } else {
                notifyObservers(element);
            }
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