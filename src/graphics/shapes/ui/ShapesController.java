package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import graphics.shapes.Shape;

public class ShapesController extends Controller {
    private boolean shiftPressed = false;
    private Point lastClick;

    public ShapesController(Object newModel) {
        super(newModel);
    }

    public void mousePressed(MouseEvent e) {
        this.lastClick = new Point(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int xCursor = e.getX();
        int yCursor = e.getY();
        Shape target = this.getTarget(xCursor, yCursor);
        if (this.shiftPressed){
            if (target != null){
                ((SelectionAttributes)target.getAttributes("SelectionAttributes")).toggleSelection();
            }
        }
        else{
            this.unSelectAll();
        }
        getView().repaint();

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent evt)
    {
    }

    public void mouseDragged(MouseEvent evt) {
        int lx = lastClick.x;
        int ly = lastClick.y;
        Iterator<Shape> iterator = ((SCollection)this.getModel()).iterator();
        while (iterator.hasNext()){
            Shape shape = iterator.next();
            if (((SelectionAttributes)shape.getAttributes("SelectionAttributes")).isSelected()){
                shape.translate(evt.getX() - lx, evt.getY() - ly);
            }
        }
        this.lastClick = new Point(evt.getX(), evt.getY());
        getView().invalidate();
    }

    public void keyTyped(KeyEvent evt)
    {
    }

    public void keyPressed(KeyEvent evt) {
        if (evt.isShiftDown()){
            this.shiftPressed = true;
        }
    }

    public void keyReleased(KeyEvent evt) {
        this.shiftPressed = false;
    }

    public Shape getTarget(int x, int y){
        Iterator<Shape> iterator = ((SCollection)this.getModel()).iterator();
        while(iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getBounds().contains(x, y)){
                return shape;
            }
        }
        return null;
    }

    public void unSelectAll(){
        Iterator<Shape> iterator = ((SCollection)this.getModel()).iterator();
        while (iterator.hasNext()){
            ((SelectionAttributes)iterator.next().getAttributes("SelectionAttributes")).unselect();
        }
    }
}
