package graphics.ui.controllers;

import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.Constantes;
import graphics.formes.*;
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
    private Color strokedColor = Constantes.DEFAULT_COLOR_ADD;
    final JPopupMenu menu = new JPopupMenu("Color Menu");

    public RectangleCreator(Shape newModel, ModelView view) {
        super(newModel);
        this.model = newModel;
        this.setView(view);
        this.locCreation = new Point();
        unselectedAll();
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
            SRectangle rect = new SRectangle(this.locCreation, 10, 10);
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
                s.resize(Math.abs(i), Math.abs(p));
            }
        }
    }

    public void requestJPopopUpMenu(JPopupMenu menu,ModelController modelController) {
        JMenu color = new JMenu("Color");
        JMenuItem strokedColorMenu = new JMenuItem("Stroked color");
        JMenuItem filledColorMenu = new JMenuItem("Filled color");

        strokedColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            Color strokedColorPrevious = this.strokedColor;
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            strokedColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (strokedColorMenu == null)
                strokedColor = strokedColorPrevious;
            myFrame.setTitle("Select your stroked color");
        });
        filledColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            Color filledColorPrevious = this.filledColor;
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            filledColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (filledColor == null)
                filledColor = filledColorPrevious;
            myFrame.setTitle("Select your filled color");
        });

        color.add(strokedColorMenu);
        color.add(filledColorMenu);

        menu.add(color);
    }

}

