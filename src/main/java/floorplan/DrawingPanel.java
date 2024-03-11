package floorplan;

import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import elements.*;
import elements.Window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    public DrawingPanel(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();
        setPreferredSize(new Dimension(width, height));

        designElements = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.out.print(designElements.size());
                if (currentFunction instanceof Select && SwingUtilities.isLeftMouseButton(e)) {
                    DesignElement clickedElement = getClickedElement(e.getPoint());
                    if (clickedElement != null) {
                        clickedElement.setSelected(true);
                        repaint();
                    	lastPoint = e.getPoint();
                        System.out.println("Clicked element class: " + clickedElement.getClass().getSimpleName());
                        if (clickedElement instanceof Door) {
                            Door clickedDoor = (Door) clickedElement;
                            System.out.println("Clicked on a door. Is selected: " + clickedDoor.isSelected());
                            //clickedElement.setSelected(true);
                            //drawElement(lastPoint);
                            repaint();
                        } else {
                            System.out.println("Clicked element is not a Door.");
                        }
                    } else {
                        System.out.println("No element found at the clicked point.");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Check if the left mouse button was pressed
                if (e.getButton() != MouseEvent.BUTTON1) {
                    return; // Return if it wasn't the left mouse button
                }

                if(currentFunction instanceof Select){
                    //DO SELECT STUFF HERE
                    //currentFunction = null;
                    return;
                }

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

                	//drawElement(lastPoint);
                	repaint();
                    //designElements.add(currentElement);
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
        
        //dont really need this, was trying ot show wall being drawn as mouse is dragged
        /**addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentElement instanceof Wall) {
                    //((Wall)currentElement).setEndPoint(e.getPoint());
                    //drawElement(lastPoint);
                    lastPoint = e.getPoint();
                    repaint();
                }
            }
        });**/

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

    public void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();

        if(designElements != null){
            designElements.clear();
        }

        repaint();
    }

    public void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Image");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(canvas, "PNG", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Image");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                canvas = ImageIO.read(file);
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private DesignElement getClickedElement(Point point) {
        List<DesignElement> elements = getDesignElements();
        for (DesignElement element : elements) {
            Rectangle bounds = element.getBounds();
            if (bounds.contains(point)) {
                System.out.println("Found element of class: " + element.getClass().getSimpleName());
                return element;
            }
        }
        System.out.println("No element found at the specified point.");
        return null;
    }
}
