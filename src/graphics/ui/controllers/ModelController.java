package graphics.ui.controllers;

import graphics.Constantes;
import graphics.formes.Calque;
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

/**
 * Définition du controller du modèle.
 */
public class ModelController extends Controller {
    /**
     * Controller du calque actuel.
     */
    private AbstractController controller;
    /**
     * Menu du clic droit.
     */
    private JPopupMenu menu = new JPopupMenu("Menu");
    /**
     * Est-ce que la touche contrôle est appuyé ?
     */
    private boolean control;

    /**
     * Constructeur.
     *
     * @param newModel Le modèle actuel.
     * @param v La vue actuel.
     */
    public ModelController(Object newModel, View v) {
        super(newModel);
        this.controller = new ShapesController(((SModel)newModel).getCalques().get(0).getContent(),v);
        this.control = false;
    }

    /**
     * Renvoie le controller actuel.
     * @return Le controller actuel.
     */
    public AbstractController getController() {return this.controller;}

    /**
     * Ajuste la vue du controller.
     */
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

    /**
     * Change le controller par actuel.
     *
     * @param c Nouveau controller.
     */
    public void setController(AbstractController c){
        this.controller = c;
        this.setController();
        this.menu = new JPopupMenu("menu");
        c.requestJPopopUpMenu(this.menu, this);
    }

    /**
     * Désélectionne toutes les formes du modèle.
     */
    private void unSelectAll() {
        SModel model = (SModel) this.getModel();
        for (Calque c:model.getCalques()){
            for (Shape s:c.getContent().getElement()){
                s.unselect();
            }
        }
    }

    /**
     * Renvoie le calque en cours d'utilisation.
     *
     * @return Le calque en cours d'utilisation.
     */
    private Calque getCalqueUsed() {
        for (Calque calque : ((SModel) this.getModel()).getCalques()) {
            if (calque.isUsed())
                return calque;
        }
        return null;
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie.
     *
     * @param e Événement souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        setController();
        if(SwingUtilities.isRightMouseButton(e)) {
            if(!Objects.equals(menu, new JPopupMenu()))
                menu.show(this.getView(), e.getX(), e.getY());
        }
        else
            this.controller.mousePressed(e);
    }

    /**
     * Action réalisée lorsque l'utilisateur relâche la souris.
     *
     * @param e Événement souris.
     */
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

    /**
     * Action réalisée lorsque l'utilisateur clique.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        setController();
        this.controller.mouseClicked(e);
    }

    /**
     * Action réalisée lorsque la souris entre dans l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        setController();
        this.controller.mouseEntered(e);
    }

    /**
     * Action réalisée lorsque la souris sort de l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        setController();
        this.controller.mouseExited(e);
    }

    /**
     * Action réalisée lorsque la souris bouge.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        setController();
        this.controller.mouseMoved(e);
    }

    /**
     * Action réalisée lorsque l'utilisateur fait un cliqué glissé.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        setController();
        this.controller.mouseDragged(e);
    }

    /**
     * Action réalisée lorsque l'utilisateur clique sur une touche.
     *
     * @param e Événement clavier.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        setController();
        this.controller.keyTyped(e);
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie sur une touche.
     *
     * @param e Événement clavier.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        setController();
        if (e.getKeyCode()==8){
            ((SModel) this.getModel()).getCalqueUse().getElement().removeIf(Shape::isSelected);
        }
        if (e.getKeyCode()==KeyEvent.VK_META || e.getKeyCode()==KeyEvent.VK_CONTROL) {
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

    /**
     * Action réalisée lorsque l'utilisateur relâche une touche.
     *
     * @param e Événement clavier.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        setController();
        if (e.getKeyCode()==KeyEvent.VK_META) {
            this.control = false;
        }
        this.controller.keyReleased(e);
    }

    /**
     * Action réalisée lorsque l'utilisateur touche à la molette.
     *
     * @param e Événement molette.
     */
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
