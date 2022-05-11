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

    /**
     * Action réalisée lorsque l'utilisateur appuie.
     *
     * @param e Événement souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Action réalisée lorsque l'utilisateur relache la souris.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Action réalisée lorsque l'utilisateur clique.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Action réalisée lorsque la souris entre dans l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Action réalisée lorsque la souris sort de l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Action réalisée lorsque la souris bouge.
     *
     * @param evt Événement souris.
     */
    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    /**
     * Action réalisée lorsque l'utilisateur fait un cliqué glissé.
     *
     * @param evt Événement souris.
     */
    @Override
    public void mouseDragged(MouseEvent evt) {
    }

    /**
     * Action réalisée lorsque l'utilisateur clique sur une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyTyped(KeyEvent evt) {
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie sur une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyPressed(KeyEvent evt) {
    }

    /**
     * Action réalisée lorsque l'utilisateur relache une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyReleased(KeyEvent evt) {
    }

    /**
     * Construit le JPopupMenu associé à ce controller
     *
     * @param menu Menu.
     * @param modelController Controller.
     */
    public void requestJPopopUpMenu(JPopupMenu menu,ModelController modelController) {
    }
}