package graphics.ui.controllers;

import graphics.ui.View.View;

import java.awt.event.*;

/**
 * Définition du controller de base.
 */
public class Controller implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {
    /**
     * Model actuel.
     */
    private Object model;
    /**
     * Vue du modèle.
     */
    private View view;

    /**
     * Constructeur.
     *
     * @param newModel Modèle.
     */
    public Controller(Object newModel) {
        model = newModel;
    }

    /**
     * Change la vue.
     *
     * @param view Vue souhaitée.
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Retourne la vue.
     *
     * @return La vue.
     */
    public View getView() {
        return this.view;
    }

    /**
     * Change le modèle.
     *
     * @param model modèle souhaitée
     */
    public void setModel(Object model) {
        this.model = model;
    }

    /**
     * Renvoie le modèle actuel.
     *
     * @return Le modèle actuel.
     */
    public Object getModel() {
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
     * Action réalisée lorsque l'utilisateur relâche la souris.
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
     * Action réalisée lorsque l'utilisateur relâche une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyReleased(KeyEvent evt) {
    }

    /**
     * Action réalisée lorsque l'utilisateur touche à la molette.
     *
     * @param e Événement molette.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
