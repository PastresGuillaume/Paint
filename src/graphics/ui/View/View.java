package graphics.ui.View;

import graphics.ui.controllers.Controller;

import javax.swing.JPanel;


public abstract class View extends JPanel {
    private Object model;
    private Controller controller;

    public View(Object model) {
        this.model = model;
        this.controller = defaultController(model);
        this.controller.setView(this);
        this.addMouseListener(this.controller);
        this.addMouseMotionListener(this.controller);
        this.addKeyListener(this.controller);
    }

    public void setModel(Object model) {
        this.model = model;
        this.controller.setModel(model);
    }

    public Object getModel() {
        return this.model;
    }

    public Controller defaultController(Object model)
    {
        return new Controller(model);
    }

    final public Controller getController()
    {
        return this.controller;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void invalidate(){
        this.paintImmediately(getBounds());
    }
}


