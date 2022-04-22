package graphics.ui.View;

import graphics.formes.SModel;
import graphics.ui.Visitor.GameDraftman;
import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.controllers.AbstractController;
import graphics.ui.controllers.ModelController;
import graphics.ui.controllers.Controller;

import java.awt.*;

public class ModelView extends View{
//    private ModelDraftman draftman;
    private GameDraftman draftman;

    public ModelDraftman getDraftman() {
        return draftman;
    }

    public ModelView(Object model) {
        super(model);
//        this.draftman = new ModelDraftman();
        this.draftman = new GameDraftman();
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
}
