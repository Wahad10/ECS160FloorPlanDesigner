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

    private Select selectFunction;
    private Move moveFunction;
    private Remove removeFunction;
    private Resize resizeSlider;
    private Rotate rotateSlider;
    private ManipulationFunction currentFunction;


    public DrawingPanel(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true); // Enable focus for the panel

        designElements = new ArrayList<>();

        selectFunction = new Select(this);
        moveFunction = new Move(this, selectFunction);
        removeFunction = new Remove(this, selectFunction);
        resizeSlider = new Resize(this, selectFunction);
        rotateSlider = new Rotate(this, selectFunction);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastPoint = e.getPoint();
            	System.out.print(designElements.size());
                //select and any other functions too (polymorphism)
                if (currentFunction != null && SwingUtilities.isLeftMouseButton(e) && !(currentFunction instanceof Move)) {
                    currentFunction.performFunction(lastPoint);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Return if it wasn't the left mouse button
                if (e.getButton() != MouseEvent.BUTTON1) {return;}

                if(currentFunction instanceof Select){
                    selectFunction.startPoint = e.getPoint();
                    selectFunction.endPoint = selectFunction.startPoint;
                    selectFunction.selectedElements.clear();
                }
                if(currentFunction instanceof Move){
                    moveFunction.startDragPoint = e.getPoint();
                }

                lastPoint = e.getPoint();
                //Wall will only be drawn once mouse is released later
                if (currentElement instanceof Wall) {
                    Wall newWall = new Wall();
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

                if(currentFunction instanceof Select){
                    selectFunction.startPoint = null;
                    selectFunction.endPoint = null;
                    repaint();
                }

                if(currentFunction instanceof Move){
                    //selectFunction.clearSelection();
                    moveFunction.startDragPoint = null;
                    repaint();
                }
                
                lastPoint = e.getPoint();
                if (currentElement instanceof Wall && currentFunction == null) {
                    ((Wall)currentElement).setEndPoint(lastPoint);
                    repaint();
                }
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Return if it wasn't the left mouse button
                if (!SwingUtilities.isLeftMouseButton(e)) {return;}

                if(currentFunction instanceof Select){
                    selectFunction.endPoint = e.getPoint();
                    //selectFunction.draw();
                    selectFunction.selectElements();
                    repaint();
                }

                if (currentFunction instanceof Move) {
                    moveFunction.performBetterFunction(e.getPoint());
                }
                
                lastPoint = e.getPoint();
                if (currentElement instanceof Wall && currentFunction == null) {
                    ((Wall)currentElement).setEndPoint(lastPoint);
                    repaint();
                }
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("Escape key pressed");
                    selectFunction.clearSelection();
                    //repaint();
                    resizeSlider.setVisible(false);
                    rotateSlider.setVisible(false);
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    onFunctionSelected(selectFunction);
                }

                if (e.getKeyCode() == KeyEvent.VK_M) {
                    onFunctionSelected(moveFunction);
                }

                if (e.getKeyCode() == KeyEvent.VK_R) {
                    onFunctionSelected(removeFunction);
                }

                if (e.getKeyCode() == KeyEvent.VK_T) {
                    onFunctionSelected(rotateSlider);
                }

                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    onFunctionSelected(resizeSlider);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
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

    private void drawElements(List<DesignElement> designElements) {
        Graphics2D g2d = canvas.createGraphics();

        System.out.println("REDRAW");
        for (DesignElement element : designElements){
            System.out.println(element);
            element.draw(g2d);
        }

        //also draw selection rectangle if needed
        if(currentFunction instanceof Select){
            selectFunction.draw(g2d);
        }

        g2d.dispose();
    }

    public List<DesignElement> getDesignElements(){
        return designElements;
    }

    @Override
    public void onElementSelected(DesignElement element) {
        currentElement = element;
        currentFunction = null;

        selectFunction.clearSelection();
        //should i hide resizeslider?
        resizeSlider.setVisible(false);
        rotateSlider.setVisible(false);

        // Request focus for the panel
        requestFocusInWindow();
    }

    

    @Override
    public void onFunctionSelected(ManipulationFunction function) {
        currentElement = null;
        currentFunction = function;

        //perform remove immediately on click
        if(currentFunction instanceof Remove){
            currentFunction.performFunction(lastPoint);
            currentFunction = null;
        }

        if(currentFunction instanceof Resize){      
            //show resize slider
            setLayout(new BorderLayout());
            add(resizeSlider, BorderLayout.NORTH);
            resizeSlider.setVisible(true);
        }else{
            //hide resize slider
            resizeSlider.setVisible(false);
        }

        if(currentFunction instanceof Rotate){      
            //show rotate slider
            setLayout(new BorderLayout());
            add(rotateSlider, BorderLayout.NORTH);
            rotateSlider.setVisible(true);
        }else{
            //hide rotate slider
            rotateSlider.setVisible(false);
        }

        // Request focus for the panel
        requestFocusInWindow();
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
            if(selectFunction != null){selectFunction.clearSelection();}
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

    public Select getSelect(){
        return selectFunction;
    }

    public Move getMove(){
        return moveFunction;
    }

    public Remove getRemove(){
        return removeFunction;
    }

    public Rotate getRotate(){
        return rotateSlider;
    }

    public Resize getResize(){
        return resizeSlider;
    }
}
