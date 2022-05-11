package graphics.ui.View;

import graphics.ui.controllers.Controller;

import javax.swing.JPanel;

/**
 * Définition générale d'une vue.
 */
public abstract class View extends JPanel {
    /**
     * Le model sur lequel s'appuie la vue.
     */
    private Object model;
    /**
     * Le controller associé à la vue.
     */
    private Controller controller;

    /**
     * Constructeur.
     *
     * @param model Modèle du MVC.
     */
    public View(Object model) {
        this.model = model;
        this.controller = defaultController(model);
        this.controller.setView(this);
        this.addMouseListener(this.controller);
        this.addMouseMotionListener(this.controller);
        this.addMouseWheelListener(this.controller);
        this.addKeyListener(this.controller);
    }

    /**
     * Change le modèle.
     *
     * @param model Modèle souhaité.
     */
    public void setModel(Object model) {
        this.model = model;
        this.controller.setModel(model);
    }

    /**
     * Retourne le modèle.
     *
     * @return Le modèle.
     */
    public Object getModel() {
        return this.model;
    }

    /**
     * Retourne le controller par défaut.
     *
     * @param model Le modèle du controller.
     * @return Le controller associé.
     */
    public Controller defaultController(Object model)
    {
        return new Controller(model);
    }

    /**
     * Retourne le controller actuel.
     *
     * @return Le controller actuel.
     */
    final public Controller getController() {
        return this.controller;
    }

    /**
     * Une vue est intéressée par les clics.
     * @return true.
     */
    @Override
    public boolean isFocusable() {
        return true;
    }

    /**
     * Fonction de rafraichissement.
     */
    @Override
    public void invalidate(){
        this.paintImmediately(getBounds());
    }
}


