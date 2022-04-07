package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.ui.controllers.AbstractController;
import graphics.shapes.ui.controllers.ShapesController;
import graphics.ui.Controller;
import graphics.ui.View;

import java.awt.*;

public class ShapesView extends View {

    private ShapeDraftman draftman;
    private Shape model;
    private AbstractController controller;

    public ShapesView(Shape model) {
        super(model);
        this.draftman = new ShapeDraftman();
        this.model = model;
        this.controller = (AbstractController) defaultController(this.model);
        this.controller.setView(this);
        this.addMouseListener(this.controller);
        this.addMouseMotionListener(this.controller);
        this.addKeyListener(this.controller);
    }

    @Override
    public Controller defaultController(Shape model) {
        return new ShapesController(model,this);
    }

    public void setController(AbstractController controller) {
        this.removeMouseListener(this.controller);
        this.removeMouseMotionListener(this.controller);
        this.removeKeyListener(this.controller);
        this.controller = controller;
        super.addMouseListener(controller);
        super.addMouseMotionListener(controller);
        super.addKeyListener(controller);
        super.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        SCollection model = (SCollection)this.getModel();
        if (model == null){
            return;
        }
        this.draftman.setGraphics(g);
        model.accept(draftman);
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
