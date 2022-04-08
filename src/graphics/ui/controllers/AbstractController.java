package graphics.ui.controllers;

import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AbstractController extends Controller {

    private Shape model;
    private ModelView view;

    public AbstractController(Shape newModel) {
        super(newModel);
    }

    public void setView(ModelView view) {
        this.view = view;
    }

    public void setModel(Shape model) {
        this.model = model;
    }

    @Override
    public ModelView getView() {
        return this.view;
    }

    @Override
    public Shape getModel() {
        return this.model;
    }

    @Override
    public void mousePressed(MouseEvent e) {
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