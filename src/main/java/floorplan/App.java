package floorplan;

import java.awt.*;
import javax.swing.*;

import elements.BarElement;
import functions.BarFunction;

/**
 * Simple Floor Plan Designer using Java Swing.
 * Allows users to draw, save, load, and clear floor plans.
 *
 * @author ChatGPT
 */
public class App extends JFrame {

    private DrawingPanel drawingPanel;
    private BarElement toolbox;
    private BarFunction functionbox;

    public App() {
        super("Simple Floor Plan Designer");
        initUI();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Create and add the drawing panel to the center
        drawingPanel = new DrawingPanel(800, 600);
        add(drawingPanel, BorderLayout.CENTER);

        // Create and add the toolbox to the east
        toolbox = new BarElement();
        add(toolbox, BorderLayout.EAST);
        toolbox.addObserver(drawingPanel);

        // Create and add the functionbox to the north
        functionbox = new BarFunction(drawingPanel);
        add(functionbox, BorderLayout.NORTH);
        functionbox.addObserver(drawingPanel);

        setupMenuBar();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createHelpMenu());

        setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
    	  JMenu fileMenu = new JMenu("File");
    	  
          JMenuItem saveItem = new JMenuItem("Save");
          saveItem.addActionListener(e -> drawingPanel.saveImage());
          fileMenu.add(saveItem);

          JMenuItem loadItem = new JMenuItem("Load");
          loadItem.addActionListener(e -> drawingPanel.loadImage());
          fileMenu.add(loadItem);

          fileMenu.add(new JSeparator());

          JMenuItem exitItem = new JMenuItem("Exit");
          exitItem.addActionListener(e -> System.exit(0));
          fileMenu.add(exitItem);
          
          return fileMenu;
    }

    private JMenu createEditMenu() {
    	JMenu editMenu = new JMenu("Edit");
    	
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> drawingPanel.clearCanvas());
        editMenu.add(clearItem);
        
        return editMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Simple Paint Application\nVersion 1.0\nCreated by ChatGPT", "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);
        
        return helpMenu;
    }
}
