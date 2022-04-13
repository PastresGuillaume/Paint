package graphics.ui.controllers;

import graphics.formes.Calque;
import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;
import graphics.ui.View.View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ModelController extends Controller {
    private AbstractController controller;
    private JPopupMenu menu = new JPopupMenu("Menu");

    public ModelController(Object newModel, View v) {
        super(newModel);
        this.controller = new ShapesController(((SModel)newModel).getCalques().get(0).getContent(), v);
    }

    private void setController() {
        for (Calque calque : ((SModel) this.getModel()).getCalques()) {
            if (calque.isUsed()) {
                this.controller.setView(this.getView());
                this.controller.setModel(calque.getContent());
//                this.getView().invalidate();
                return;
            }
        }
    }

    public void setController(AbstractController c){
        c.setView(this.getView());
        this.controller = c;
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

    @Override
    public void mousePressed(MouseEvent e) {
        setController();
        if(SwingUtilities.isRightMouseButton(e)) {
            ((ModelView)this.getView()).setNoRightClickMenu(false);
            if(!Objects.equals(menu, new JPopupMenu()))
                menu.show(this.getView(), e.getX(), e.getY());
        }
        else
            this.controller.mousePressed(e);
        ((ModelView)this.getView()).setNoRightClickMenu(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setController();
        this.controller.mouseReleased(e);
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
        this.controller.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setController();
        this.controller.keyReleased(e);
    }
}
