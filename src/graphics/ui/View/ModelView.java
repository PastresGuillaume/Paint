package graphics.ui.View;

import graphics.Constantes;
import graphics.formes.Calque;
import graphics.formes.SModel;
import graphics.ui.AbstractBar;
import graphics.ui.CalqueToolBar;
import graphics.ui.MenuBar;
import graphics.ui.ShapeToolBar;
import graphics.ui.Visitor.GameDraftman;
import graphics.ui.Visitor.ModelDraftman;
import graphics.ui.controllers.AbstractController;
import graphics.ui.controllers.ModelController;
import graphics.ui.controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ModelView extends View{
//    private ModelDraftman draftman;
    private GameDraftman draftman;
    private HashMap<String, AbstractBar> menus = new HashMap<>();

    public ModelDraftman getDraftman() {
        return draftman;
    }

    public ModelView(Object model) {
        super(model);
//        this.draftman = new ModelDraftman();
        this.draftman = new GameDraftman();

        this.menus.put(Constantes.CALQUE_TOOL_BAR_ID,new CalqueToolBar(this));
        this.menus.put(Constantes.MENU_BAR_ID,new MenuBar(this));
        this.menus.put(Constantes.SHAPE_TOOL_BAR_ID,new ShapeToolBar(this));
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

    public HashMap<String, AbstractBar> getMenus() {return menus;}

    public void updateIconCalqueBar(Calque calque) throws IOException {
        BufferedImage bi = new BufferedImage(300,300,BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        SModel sModel = ((SModel) this.getModel());
        ArrayList<String> arrayListPainted= new ArrayList<>();
        for(Calque calque1 : sModel.getCalques()) {
            if (calque1.isPaint())
                arrayListPainted.add(calque1.getName());
            sModel.setNotPaint(calque1);
        }
        sModel.setPaint(calque);
        paintComponent(ig2);

        ImageIO.write(bi, "PNG", new File("images\\icons\\" + calque.getName() + Constantes.IS_USED_CALQUE + ".png"));

        for(String nameCalque : arrayListPainted)
            sModel.setPaint(nameCalque);
        ImageIcon toChange = new ImageIcon("images\\icons\\" + calque.getName() + Constantes.IS_USED_CALQUE +".png");
        ((JButton) ((CalqueToolBar) this.menus.get(Constantes.CALQUE_TOOL_BAR_ID)).getButtons().get(calque.getName() + Constantes.IS_USED_CALQUE)).setIcon(new ImageIcon(toChange.getImage().getScaledInstance(Constantes.WIDTH_ICON, Constantes.HEIGHT_ICON, Image.SCALE_DEFAULT)));
    }
}
