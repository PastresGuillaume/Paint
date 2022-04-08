package graphics.ui.View;

import graphics.formes.SCollection;
import graphics.formes.Shape;
import graphics.ui.Visitor.ShapeDraftman;
import graphics.ui.controllers.AbstractController;

import java.awt.*;

public class ShapesView extends View {
    private ShapeDraftman draftman;

    public ShapesView(Object model) {
        super(model);
        this.draftman = new ShapeDraftman();
    }

    @Override
    public AbstractController defaultController(Object model) {
        return new AbstractController((Shape) this.getModel());
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
