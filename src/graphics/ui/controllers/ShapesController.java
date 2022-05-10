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

import static graphics.Constantes.*;

public class ShapesController extends AbstractController {

    private Shape model;
    private ModelView view;
    private boolean shiftPressed = false;
    private Point lastClick;
    private boolean selectionDragged=false;
    private Shape resizeableShape;
    private Point initLoc;

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

        this.resizeableShape = getResizeableShape(e.getX(), e.getY());
        if (resizeableShape!=null){
            this.unSelectAll();
            resizeableShape.select();
            this.initLoc = resizeableShape.getLoc();
        }
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
                model = ((SModel) this.model).getCalqueUse();
            }

            for (Shape shape : model.getElement()) {
                if (shape.getBounds().intersection(selection).height >= 0 && shape.getBounds().intersection(selection).width >= 0){
                    shape.select();
                }
            }
            this.view.setCursor(DEFAULT_CURSOR);
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
        Iterator<Shape> iterator = ((SCollection)this.getModel()).iterator();
        while(iterator.hasNext()) {
            Shape shape = iterator.next();
            if (((SelectionAttributes) shape.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected()){
                Rectangle r = shape.getBounds();
                if ((new Rectangle(r.x+r.width, r.y+r.height, SIZE_SHAPE_SELECTED, SIZE_SHAPE_SELECTED)).contains(evt.getX(), evt.getY())){
                    this.view.setCursor(SE_RESIZE_CURSOR);
                    return;
                }
                else {
                    this.view.setCursor(DEFAULT_CURSOR);
                }
            }
        }
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
                iterator = ((SModel) model).getCalqueUse().iterator();
            }
            while (iterator.hasNext()) {
                Shape shape = iterator.next();
                if (((SelectionAttributes) shape.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected()) {
                    shape.translate(dx, dy);
                }
            }
            this.lastClick = new Point(evt.getX(), evt.getY());
        }

        if (this.resizeableShape != null){
            int newWidth = evt.getX() - initLoc.x;
            int newHeight = evt.getY() - initLoc.y;
            this.resizeableShape.setLoc(new Point(this.initLoc.x, this.initLoc.y));
            if (newWidth<0 && newHeight >=0){
                this.resizeableShape.translate(evt.getX()-resizeableShape.getLoc().x, 0);
            }
            else if (newWidth>=0 && newHeight<0){
                this.resizeableShape.translate(0, evt.getY()-resizeableShape.getLoc().y);
            }
            else if(newWidth<0){
                this.resizeableShape.translate(evt.getX()-resizeableShape.getLoc().x, evt.getY()-resizeableShape.getLoc().y);
            }
            this.resizeableShape.resize(Math.abs(newWidth), Math.abs(newHeight));
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
            model = ((SModel) this.model).getCalqueUse();
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

    public Shape getResizeableShape(int x, int y){
        SCollection model;
        try{
            model = (SCollection) this.model;
        }
        catch (ClassCastException ex){
            model = ((SModel) this.model).getCalqueUse();
        }
        Iterator<Shape> iterator = model.iterator();
        while(iterator.hasNext()) {
            Shape shape = iterator.next();
            if (((SelectionAttributes) shape.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected()){
                Rectangle r = shape.getBounds();
                if ((new Rectangle(r.x+r.width, r.y+r.height, SIZE_SHAPE_SELECTED, SIZE_SHAPE_SELECTED)).contains(x,y)){
                    return shape;
                }
            }

        }
        return null;
    }

    public void unSelectAll(){
        SCollection model;
        try{
            model = (SCollection) this.model;
        }
        catch (ClassCastException ex){
            model = ((SModel) this.model).getCalqueUse();
        }
        Iterator<Shape> iterator = model.iterator();
        while (iterator.hasNext()){
            ((SelectionAttributes)iterator.next().getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
    }
}