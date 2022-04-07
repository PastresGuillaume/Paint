package graphics.shapes.uiCalques;

import graphics.shapes.SModel;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;
import graphics.ui.Controller;
import graphics.ui.View;

import java.awt.*;
import java.util.ArrayList;

public class ModelView extends View {
    private ModelDraftman draftman;

    public ModelView(Object model) {
        super(model);
        this.draftman = new ModelDraftman();
    }

    @Override
    public Controller defaultController(Object model) {
        return new ModelController(model, this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        SModel model = (SModel) this.getModel();
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
