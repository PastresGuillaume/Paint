package graphics.shapes.ui.controllers;

import graphics.shapes.Shape;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.uiCalques.ModelView;
import graphics.ui.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AbstractController extends Controller {

    private Shape model;
    private ShapesView view;

    public AbstractController(Shape newModel) {
        super(newModel);
    }

    public void setView(ShapesView view)
    {
        this.view = view;
    }

    public ShapesView getView()
    {
        return this.view;
    }

    public void setModel(Shape model)
    {
        this.model = model;
    }

    public Shape getModel() {return this.model;}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseMoved(MouseEvent evt){}

    public void mouseDragged(MouseEvent evt){}

    public void keyTyped(KeyEvent evt){}

    public void keyPressed(KeyEvent evt){}

    public void keyReleased(KeyEvent evt){}
}
