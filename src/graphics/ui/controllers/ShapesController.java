package graphics.ui.controllers;

import graphics.attributes.SelectionAttributes;
import graphics.Constantes;
import graphics.formes.SCollection;
import graphics.formes.SModel;
import graphics.ui.View.ModelView;
import graphics.formes.Shape;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.ui.View.View;

public class ShapesController extends AbstractController {

    private Shape model;
    private ModelView view;
    private boolean shiftPressed = false;
    private Point lastClick;
    private boolean selectionDragged=false;

    public ShapesController(Shape newModel, View view) {
        super(newModel);
        this.model = newModel;
        this.lastClick = new Point();
        this.view = (ModelView) view;
    }

    @Override
    public void setView(ModelView view){
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.selectionDragged=false;
        this.lastClick = e.getPoint();

        if (!this.shiftPressed) {
            model.unselect();
        }
        Shape shapeSelec = getTarget(e.getX(), e.getY());
        if (shapeSelec!=null){
            shapeSelec.select();
            return;
        }
        this.selectionDragged = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectionDragged) {
            Rectangle selection = new Rectangle(this.lastClick.x, this.lastClick.y, e.getX() - this.lastClick.x, e.getY() - this.lastClick.y);
            model.unselect();
            SCollection model;
            try{
                model = (SCollection) this.model;
            }
            catch (ClassCastException ex){
                model = ((SModel) this.model).getModel();
            }

            for (Shape shape : model.getElement()) {
                if (shape.getBounds().intersection(selection).height >= 0 && shape.getBounds().intersection(selection).width >= 0){
                    shape.select();
                }
            }
        }
        selectionDragged = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCursor = e.getX();
        int yCursor = e.getY();
        Shape target = this.getTarget(xCursor, yCursor);
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
            Iterator<Shape> iterator;
            try {
                iterator = ((SCollection) model).iterator();
            }
            catch (ClassCastException e){
                iterator = ((SModel) model).getModel().iterator();
            }
            while (iterator.hasNext()) {
                Shape shape = iterator.next();
                if (((SelectionAttributes) shape.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected()) {
                    shape.translate(dx, dy);
                }
            }
            this.lastClick = new Point(evt.getX(), evt.getY());
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
        SCollection model;
        try{
            model = (SCollection) this.model;
        }
        catch (ClassCastException ex){
            model = ((SModel) this.model).getModel();
        }
        Iterator<Shape> iterator = model.iterator();
        Shape retour = null;
        while(iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getBounds().contains(x, y)){
                retour = shape;
            }
        }
        return retour;
    }

    public void unSelectAll(){
        Iterator<Shape> iterator = ((SCollection)model).iterator();
        while (iterator.hasNext()){
            ((SelectionAttributes)iterator.next().getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
    }
}