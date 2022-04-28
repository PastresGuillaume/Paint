package graphics.ui.View;

import graphics.Constantes;
import graphics.formes.SModel;
import graphics.ui.CalqueToolBar;
import graphics.ui.MenuBar;
import graphics.ui.ShapeToolBar;
import graphics.ui.Visitor.GameDraftman;
import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.controllers.AbstractController;
import graphics.ui.controllers.ModelController;
import graphics.ui.controllers.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ModelView extends View{
//    private ModelDraftman draftman;
    private GameDraftman draftman;
    private HashMap<String, JComponent> menus = new HashMap<>();

    public ModelDraftman getDraftman() {
        return draftman;
    }

    public ModelView(Object model) {
        super(model);
//        this.draftman = new ModelDraftman();
        this.draftman = new GameDraftman();

        this.menus.put(Constantes.CALQUE_TOOL_BAR_ID,new CalqueToolBar(this).createToolBar());
        this.menus.put(Constantes.MENU_BAR_ID,new MenuBar(this).createMenuBar());
        this.menus.put(Constantes.SHAPE_TOOL_BAR_ID,new ShapeToolBar(this).createToolBar());
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

    public HashMap<String, JComponent> getMenus() {return menus;}
}
