package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;

import java.awt.*;

public class ShapesView extends View {
    private ShapeDraftman draftman;

    public ShapesView(Object model) {
        super(model);
        this.draftman = new ShapeDraftman();
    }

    @Override
    public Controller defaultController(Object model) {
        return new ShapesController(model);
    }

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
