package graphics.ui.View;

import graphics.Constantes;
import graphics.formes.SModel;
import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.controllers.AbstractController;
import graphics.ui.controllers.ModelController;
import graphics.ui.controllers.Controller;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ModelView extends View implements Runnable{
    private ModelDraftman draftman;

    public ModelView(Object model) {
        super(model);
        this.draftman = new ModelDraftman();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this, 0, Constantes.DELTA_REFRESH, Constantes.TIME_UNIT);
    }

    @Override
    public Controller defaultController(Object model) {
        return new ModelController(model, this);
    }

    public void setController(AbstractController c){
        ((ModelController) this.getController()).setController(c);
        super.requestFocusInWindow();
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
    public void run(){
        this.invalidate();
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