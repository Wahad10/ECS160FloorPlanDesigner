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
        setLayout(new GridLayout(5, 1));
        addElement(new Wall());
        addElement(new DoorRight());
        addElement(new DoorLeft());
        addElement(new Window());
        addElement(new Furniture());
    }

    private void addElement(DesignElement element) {
        JButton button = new JButton(element.getClass().getSimpleName());

        button.addActionListener(e -> {
            if (element instanceof Furniture) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem bedItem = new JMenuItem("Bed");
                bedItem.addActionListener(ev -> {
                    notifyObservers(new Bed());
                });
                JMenuItem sofaItem = new JMenuItem("Sofa");
                sofaItem.addActionListener(ev -> {
                    notifyObservers(new Sofa());
                });
                JMenuItem tableItem = new JMenuItem("Table");
                tableItem.addActionListener(ev -> {
                    notifyObservers(new Table());
                });
                JMenuItem plantItem = new JMenuItem("Plant");
                plantItem.addActionListener(ev -> {
                    notifyObservers(new Plant());
                });
                JMenuItem stoveItem = new JMenuItem("Stove");
                stoveItem.addActionListener(ev -> {
                    notifyObservers(new Stove());
                });
                JMenuItem toiletItem = new JMenuItem("Toilet");
                toiletItem.addActionListener(ev -> {
                    notifyObservers(new Toilet());
                });
                JMenuItem chairItem = new JMenuItem("Chair");
                chairItem.addActionListener(ev -> {
                    notifyObservers(new Chair());
                });
                JMenuItem deskItem = new JMenuItem("Desk");
                deskItem.addActionListener(ev -> {
                    notifyObservers(new Desk());
                });
                JMenuItem sinkItem = new JMenuItem("Sink");
                sinkItem.addActionListener(ev -> {
                    notifyObservers(new Sink());
                });
                JMenuItem counterItem = new JMenuItem("Counter");
                counterItem.addActionListener(ev -> {
                    notifyObservers(new Counter());
                });
                JMenuItem bathItem = new JMenuItem("Bath");
                bathItem.addActionListener(ev -> {
                    notifyObservers(new Bath());
                });
                JMenuItem lampItem = new JMenuItem("Lamp");
                lampItem.addActionListener(ev -> {
                    notifyObservers(new Lamp());
                });
                JMenuItem stairsItem = new JMenuItem("Stairs");
                stairsItem.addActionListener(ev -> {
                    notifyObservers(new Stairs());
                });

                // Add more furniture items as needed

                menu.add(bedItem);
                menu.add(chairItem);
                menu.add(sofaItem);
                menu.add(deskItem);
                menu.add(plantItem);
                menu.add(stoveItem);
                menu.add(toiletItem);
                menu.add(tableItem);
                menu.add(sinkItem);
                menu.add(counterItem);
                menu.add(bathItem);
                menu.add(lampItem);
                menu.add(stairsItem);

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