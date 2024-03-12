package floorplan;

import java.util.*;
import java.util.List;

//import javax.imageio.ImageIO;
import javax.swing.*;

import elements.*;
//import elements.Window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import functions.*;

/**
 * Panel for drawing on a canvas.
 *
 * @author ChatGPT
 */
public class DrawingPanel extends JPanel implements ElementSelectedObserver, FunctionSelectedObserver {

    private BufferedImage canvas;
    private Point lastPoint;
    private List<DesignElement> designElements;
    private DesignElement currentElement;
    private ManipulationFunction currentFunction;
    private Select selectFunction;

    public DrawingPanel(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();
        setPreferredSize(new Dimension(width, height));

        designElements = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastPoint = e.getPoint();
            	System.out.print(designElements.size());
                //select and any other functions too (polymorphism)
                if (currentFunction != null && SwingUtilities.isLeftMouseButton(e)) {
                    currentFunction.performFunction(lastPoint);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Return if it wasn't the left mouse button
                if (e.getButton() != MouseEvent.BUTTON1) {return;}

                lastPoint = e.getPoint();
                //Wall will only be drawn once mouse is released later
                if (currentElement instanceof Wall) {
                    Wall newWall = new Wall(Color.BLACK, 3);
                    currentElement = newWall;
                    currentElement.setStartPoint(lastPoint);
                    designElements.add(currentElement);
                //if currentElement anything other than wall, just draw it where mouse is clicked
                }else if (currentElement != null) {
                    try {
                        // Create a new instance of the current design element using reflection
                        currentElement = currentElement.getClass().getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                        ex.printStackTrace();
                    }
                    currentElement.setStartPoint(lastPoint);
                    designElements.add(currentElement);

                	repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Return if it wasn't the left mouse button
                if (e.getButton() != MouseEvent.BUTTON1) {return;}

                if (currentElement instanceof Wall && currentFunction == null) {
                    ((Wall)currentElement).setEndPoint(e.getPoint());
                    //drawElement(lastPoint);
                    repaint();
                }
            }

        });

        // Add component listener to handle resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeCanvas(getWidth(), getHeight());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //erase everything
        eraseCanvas();
        //draw the grid
        drawGrid(g);
        //draw elements onto canvas
        drawElements(designElements);
        // Draw the canvas image
        g.drawImage(canvas, 0, 0, null);
    }

    private void drawElements(List<DesignElement> designElements) {
        Graphics2D g2d = canvas.createGraphics();

        System.out.println("REDRAW");
        for (DesignElement element : designElements){
            System.out.println(element);
            element.draw(g2d);
        }
        g2d.dispose();
    }

    @Override
    public void onElementSelected(DesignElement element) {
        currentElement = element;
        currentFunction = null;
    }

    public List<DesignElement> getDesignElements(){
        return designElements;
    }

    @Override
    public void onFunctionSelected(ManipulationFunction function) {
        currentElement = null;
        currentFunction = function;

        if(currentFunction instanceof Select){
            selectFunction = (Select)currentFunction;
        }

        //perform remove immediately on click
        if(currentFunction instanceof Remove){
            currentFunction.performFunction(lastPoint);
            currentFunction = null;
        }
    }

    private void drawGrid(Graphics g) {
        // Draw the grid lines
        int gridSize = 20; // Adjust this value to change the grid size
        g.setColor(Color.LIGHT_GRAY);
        for (int x = 0; x < getWidth(); x += gridSize) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += gridSize) {
            g.drawLine(0, y, getWidth(), y);
        }
    }

    private void resizeCanvas(int width, int height) {
        BufferedImage newCanvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newCanvas.createGraphics();
        g2d.drawImage(canvas, 0, 0, null);
        g2d.dispose();
        canvas = newCanvas;
        repaint();
    }

    public void eraseCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();
    }

    public void clearCanvas() {
        eraseCanvas();
        if(designElements != null){
            selectFunction.clearSelection();
            designElements.clear();
        }
        repaint();
    }

    public void saveImage() {
        //clear selections before saving
        selectFunction.clearSelection();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Image");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Serialize the design elements and canvas
                oos.writeObject(designElements);
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Image");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Deserialize the design elements
                designElements = (List<DesignElement>) ois.readObject();
                repaint();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
