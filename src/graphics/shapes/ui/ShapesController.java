package graphics.shapes.ui;

import graphics.Constantes;
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
    private boolean selectionDragged=false;

    public ShapesController(Object newModel) {
        super(newModel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.selectionDragged=false;
        this.lastClick = e.getPoint();

        if (!this.shiftPressed) {
            ((Shape)super.getModel()).unselect();
        }
        for (Shape s:((SCollection) super.getModel()).getElement()){
            if (s.getBounds().contains(e.getPoint())){
                s.select();
                this.getView().invalidate();
                return;
            }
        }
        this.selectionDragged = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectionDragged) {
            Rectangle selection = new Rectangle(this.lastClick.x, this.lastClick.y, e.getX() - this.lastClick.x, e.getY() - this.lastClick.y);
            ((Shape) super.getModel()).unselect();
            for (Shape s : ((SCollection) super.getModel()).getElement()) {
                if (s.getBounds().intersection(selection).height>=0 && s.getBounds().intersection(selection).width>=0) {
                    s.select();
                }
            }
        }
        selectionDragged = false;
        super.getView().invalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCursor = e.getX();
        int yCursor = e.getY();
        Shape target = this.getTarget(xCursor, yCursor);
//        if (this.shiftPressed){
//            if (target != null){
//                ((SelectionAttributes)target.getAttributes(Constantes.SELECTION_ATTRIBUTE)).toggleSelection();
//            }
//        }
//        else{
//            this.unSelectAll();
//        }
        if (target!=null){
            if (this.shiftPressed){
                ((SelectionAttributes)target.getAttributes(Constantes.SELECTION_ATTRIBUTE)).toggleSelection();
            }
            else {
                this.unSelectAll();
                ((SelectionAttributes)target.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
            }
        }
        else{
            this.unSelectAll();
        }
        getView().repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        if (!this.selectionDragged) {
            int dx = evt.getX()-lastClick.x;
            int dy = evt.getY()-lastClick.y;
            Iterator<Shape> iterator = ((SCollection) this.getModel()).iterator();
            while (iterator.hasNext()) {
                Shape shape = iterator.next();
                if (((SelectionAttributes) shape.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected()) {
                    shape.translate(dx, dy);
                }
            }
            this.lastClick = new Point(evt.getX(), evt.getY());
            getView().invalidate();
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.isShiftDown()){
            this.shiftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (!evt.isShiftDown()) {
            this.shiftPressed = false;
        }
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
            ((SelectionAttributes)iterator.next().getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
    }
}
