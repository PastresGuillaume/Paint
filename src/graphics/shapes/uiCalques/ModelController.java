package graphics.shapes.uiCalques;

import graphics.shapes.SCalque;
import graphics.shapes.SModel;
import graphics.shapes.Shape;
import graphics.shapes.ui.ShapesController;
import graphics.ui.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModelController extends Controller {
    private ArrayList<ShapesController> controllers;

    public ModelController(Object newModel) {
        super(newModel);
        controllers = new ArrayList<>();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    private void unSelectAll() {
        SModel model = (SModel) this.getModel();
        for (SCalque c:model.getCalques()){
            for (Shape s:c.getContent()){
                s.unselect();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }
}
