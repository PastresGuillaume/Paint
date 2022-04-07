package graphics.shapes.uiCalques;

import graphics.shapes.SCalque;
import graphics.shapes.SModel;
import graphics.shapes.Shape;
import graphics.shapes.ui.ShapesController;
import graphics.ui.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ModelController extends Controller {
    private ShapesController controller;

    public ModelController(Object newModel) {
        super(newModel);
        this.controller = new ShapesController(((SModel)newModel).getCalques().get(0).getContent());
    }

    private void setControllers(){
        this.controller.setView(this.getView());
        for (SCalque calque:((SModel)this.getModel()).getCalques()){
            if (calque.isUsed()){
                this.controller.setModel(calque.getContent());
                return;
            }
        }
    }

    private void unSelectAll() {
        SModel model = (SModel) this.getModel();
        for (SCalque c:model.getCalques()){
            for (Shape s:c.getContent().getElement()){
                s.unselect();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setControllers();
        this.controller.mousePressed(e);
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        setControllers();
        this.controller.mouseReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setControllers();
        this.controller.mouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setControllers();
        this.controller.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setControllers();
        this.controller.mouseExited(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setControllers();
        this.controller.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setControllers();
        this.controller.mouseDragged(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        setControllers();
        this.controller.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setControllers();
        this.controller.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setControllers();
        this.controller.keyReleased(e);
    }
}
