package graphics.ui.controllers;

import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Classe abstraite définissant la forme génénale des contrôleurs
 */
public class AbstractController extends Controller {

    /**
     * Model stocké dans le contrôleur
     */
    private Shape model;

    /**
     * la view du modèle mvc
     */
    private ModelView view;

    /**
     * constructeur
     *
     * @param newModel modèle actif
     */
    public AbstractController(Shape newModel) {
        super(newModel);
    }

    /**
     * Fixer la view
     *
     * @param view view souhaitée
     *
     */
    public void setView(ModelView view) {
        this.view = view;
    }

    /**
     * fixer le modèle
     *
     * @param model modèle souhaitée
     */
    public void setModel(Shape model) {
        this.model = model;
    }

    /**
     * avoir la view de ce controller
     *
     * @return renvoie la vue de ce controller
     */
    @Override
    public ModelView getView() {
        return this.view;
    }

    /**
     * avoir le modèle de ce controller
     *
     * @return renvoie lr modèle de ce controller
     */
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

    /**
     * Construit le JPopupMenu associé à ce controller
     */
    public void requestJPopopUpMenu(JPopupMenu menu,ModelController modelController) {
    }
}