package graphics.ui.controllers;

import graphics.Constantes;
import graphics.formes.Calque;
import graphics.formes.GameCalque;
import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;
import graphics.ui.View.View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.Objects;

public class ModelController extends Controller {
    private AbstractController controller;
    private JPopupMenu menu = new JPopupMenu("Menu");
    private boolean control;

    public ModelController(Object newModel, View v) {
        super(newModel);
        this.controller = new ShapesController(((SModel)newModel).getCalques().get(0).getContent(),v);
        this.control = false;
    }

    public AbstractController getController() {return this.controller;}

    public void setController() {
        for (Calque calque : ((SModel) this.getModel()).getCalques()) {
            if (calque.isUsed()) {
                this.controller.setView(this.getView());
                this.controller.setModel(calque.getContent());
                this.getView().invalidate();
                return;
            }
        }
    }

    public void setController(AbstractController c){
        this.controller = c;
        this.setController();
        this.menu = new JPopupMenu("menu");
        c.requestJPopopUpMenu(this.menu, this);
    }

    private void unSelectAll() {
        SModel model = (SModel) this.getModel();
        for (Calque c:model.getCalques()){
            for (Shape s:c.getContent().getElement()){
                s.unselect();
            }
        }
    }

    private Calque getCalqueUsed() {
        for (Calque calque : ((SModel) this.getModel()).getCalques()) {
            if (calque.isUsed())
                return calque;
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setController();
        if(SwingUtilities.isRightMouseButton(e)) {
//            ((ModelView)this.getView()).setNoRightClickMenu(false);
            if(!Objects.equals(menu, new JPopupMenu()))
                menu.show(this.getView(), e.getX(), e.getY());
        }
        else
            this.controller.mousePressed(e);
//        ((ModelView)this.getView()).setNoRightClickMenu(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setController();
        this.controller.mouseReleased(e);
        try {
            ((ModelView) this.getView()).updateIconCalqueBar(this.getCalqueUsed());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setController();
        this.controller.mouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setController();
        this.controller.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setController();
        this.controller.mouseExited(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setController();
        this.controller.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setController();
        this.controller.mouseDragged(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        setController();
        this.controller.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setController();
        if (e.getKeyCode()==8){
            ((SModel) this.getModel()).getCalqueUse().getElement().removeIf(Shape::isSelected);
        }
        if (e.getKeyCode()==KeyEvent.VK_META) {
            this.control = true;
        }
        if (this.control){
            if (e.getKeyCode()==KeyEvent.VK_A) {
                for (Shape shape : ((SModel) this.getModel()).getCalqueUse().getElement()) {
                    shape.select();
                }
            }
            else if (e.getKeyCode()==KeyEvent.VK_UP){
                ((SModel) this.getModel()).force_translate(0, Constantes.DEFAULT_DEPLACEMENT_VERTICAL);
            }
            else if (e.getKeyCode()==KeyEvent.VK_DOWN){
                ((SModel) this.getModel()).force_translate(0, -Constantes.DEFAULT_DEPLACEMENT_VERTICAL);
            }
            else if (e.getKeyCode()==KeyEvent.VK_LEFT){
                ((SModel) this.getModel()).force_translate(Constantes.DEFAULT_DEPLACEMENT_HORIZONTAL, 0);
            }
            else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                ((SModel) this.getModel()).force_translate(-Constantes.DEFAULT_DEPLACEMENT_HORIZONTAL, 0);
            }
        }

        this.controller.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setController();
        if (e.getKeyCode()==KeyEvent.VK_META) {
            this.control = false;
        }
        this.controller.keyReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (this.control){
            if (e.getWheelRotation()<0){
                ((SModel) this.getModel()).zoomIn();
            }
            if (e.getWheelRotation()>0){
                ((SModel) this.getModel()).zoomOut();
            }
        }
    }
}
