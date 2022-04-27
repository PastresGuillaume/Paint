package graphics.ui.controllers;

import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.Constantes;
import graphics.formes.SCollection;
import graphics.formes.SModel;
import graphics.formes.SRectangle;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class RectangleCreator extends AbstractController {

    private Shape model;
    private Point locCreation ;
    private Color filledColor = Constantes.COLOR_INVISIBLE;
    private Color strokedColor = Constantes.DEFAULT_COLOR_ADD_RECTANGLE;
    final JPopupMenu menu = new JPopupMenu("Color Menu");

    public RectangleCreator(Shape newModel, ModelView view) {
        super(newModel);
        this.model = newModel;
        this.setView(view);
        this.locCreation = new Point();
        unselectedAll();

//        JMenu color = new JMenu("Color");
//        JMenuItem strokedColorMenu = new JMenuItem("Stroked color");
//        JMenuItem filledColorMenu = new JMenuItem("Filled color", new ImageIcon("graphics\\shapes\\ui\\image\\chat.jpg" ));
//
//        strokedColorMenu.addActionListener(e -> {
//            JFrame myFrame = new JFrame();
//            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            myFrame.setSize(500, 300);
//            myFrame.setLocationRelativeTo(null);
//            myFrame.setLayout(new BorderLayout());
//            strokedColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
//            myFrame.setTitle("Select your filled color");
//        });
//        filledColorMenu.addActionListener(e -> {
//            JFrame myFrame = new JFrame();
//            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            myFrame.setSize(500, 300);
//            myFrame.setLocationRelativeTo(null);
//            myFrame.setLayout(new BorderLayout());
//            filledColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
//            myFrame.setTitle("Select your filled color");
//        });
//
//        color.add(strokedColorMenu);
//        color.add(filledColorMenu);
//
//        menu.add(color);
    }

    @Override
    public void setModel(Shape model)
    {
        this.model = model;
    }

    @Override
    public Shape getModel() {return this.model;}

    @Override
    public void mousePressed(MouseEvent e) {
        unselectedAll();
        if(SwingUtilities.isRightMouseButton(e))
        {
            menu.show(this.getView() , e.getX(), e.getY());
        }
        else {
            this.locCreation = e.getPoint();
            SRectangle rect = new SRectangle(e.getPoint(), 10, 10);
            rect.addAttributes(new ColorAttributes(true, true, filledColor, strokedColor));
            rect.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) rect.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
            ((SCollection) this.model).add(rect);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.locCreation = new Point();
        unselectedAll();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.locCreation = new Point();
        unselectedAll();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int width = (int) (e.getPoint().getX() - this.locCreation.getX());
        int height = (int) (e.getPoint().getY() - this.locCreation.getY());
        this.changeSize(width, height);
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        if(keyCode == KeyEvent.VK_SHIFT || keyCode == KeyEvent.VK_CONTROL) {
            JFrame myFrame = new JFrame();
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            Color selectedColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if(keyCode == KeyEvent.VK_SHIFT){
                filledColor = selectedColor;
                myFrame.setTitle("Select your filled color");
            }
            else {
                strokedColor = selectedColor;
                myFrame.setTitle("Select your stroked color");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    private void unselectedAll(){
        try {
            for (Shape s : ((SModel) this.model).getCalqueUse().getElement())
                s.unselect();
        }
        catch (ClassCastException e){
            for (Shape s : ((SCollection) this.model).getElement())
                s.unselect();
        }
    }

    private void changeSize(int i,int p) {
        for (Shape s : ((SCollection) this.model).getElement()) {
            SelectionAttributes selectionAttributes = (SelectionAttributes) s.getAttributes(Constantes.SELECTION_ATTRIBUTE);
            if (selectionAttributes != null && selectionAttributes.isSelected()) {
                if (i < 0 && p < 0) {
                    s.setLoc(new Point(this.locCreation.x + i,this.locCreation.y +p));
                }
                else if (i < 0) {
                    s.setLoc((new Point(this.locCreation.x + i,this.locCreation.y)));
                }
                else if (p < 0) {
                    s.setLoc((new Point(this.locCreation.x,this.locCreation.y + p)));
                }
                s.setSize(Math.abs(i), Math.abs(p));
            }
        }
    }

    public void requestJPopopUpMenu(JPopupMenu menu,ModelController modelController) {
        JMenu color = new JMenu("Color");
        JMenuItem strokedColorMenu = new JMenuItem("Stroked color");
        JMenuItem filledColorMenu = new JMenuItem("Filled color", new ImageIcon("graphics\\shapes\\ui\\image\\chat.jpg" ));

        strokedColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            strokedColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (strokedColorMenu == null)
                strokedColor = Constantes.DEFAULT_COLOR_ADD_RECTANGLE;
            myFrame.setTitle("Select your stroked color");
        });
        filledColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            filledColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (filledColor == null)
                filledColor = Constantes.COLOR_INVISIBLE;
            myFrame.setTitle("Select your filled color");
        });

        color.add(strokedColorMenu);
        color.add(filledColorMenu);

        menu.add(color);
    }

}

