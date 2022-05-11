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

/**
 * Définition de la vue du modèle.
 */
public class ModelView extends View{
    /**
     * Dessinateur.
     */
    private GameDraftman draftman;
    /**
     * HashMap de tous les menus avec comme clé leur ID
     */
    private HashMap<String, AbstractBar> menus = new HashMap<>();

    /**
     * Getter du draftman.
     *
     * @return Le draftman.
     */
    public ModelDraftman getDraftman() {
        return draftman;
    }

    /**
     * Constructeur.
     *
     * @param model Le modèle actuel.
     */
    public ModelView(Object model) {
        super(model);
        this.draftman = new GameDraftman();

        this.menus.put(Constantes.CALQUE_TOOL_BAR_ID,new CalqueToolBar(this));
        this.menus.put(Constantes.MENU_BAR_ID,new MenuBar(this));
        this.menus.put(Constantes.SHAPE_TOOL_BAR_ID,new ShapeToolBar(this));
    }

    /**
     * Controller par défaut.
     *
     * @param model Model actuel.
     * @return Controller par défaut.
     */
    @Override
    public Controller defaultController(Object model) {
        return new ModelController(model, this);
    }

    /**
     * Change le controller actuel.
     *
     * @param c Controller souhaité.
     */
    public void setController(AbstractController c){
        ((ModelController) this.getController()).setController(c);
        super.requestFocusInWindow();
    }

    /**
     * Fonction de rafraichissement de l'écran.
     *
     * @param g Graphics.
     */
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

    /**
     * Getter pour la HashMap classant les menus
     *
     * @return Menu.
     */
    public HashMap<String, AbstractBar> getMenus() {return menus;}

    /**
     * Met à jour l'icone dans la calqueBar du calque choisi
     * @param calque calque à mettre à jour
     * @throws IOException
     */
    public void updateIconCalqueBar(Calque calque) throws IOException {
        BufferedImage bi = new BufferedImage(Constantes.WINDOW_DIMENSIONS.width,Constantes.WINDOW_DIMENSIONS.height,BufferedImage.TYPE_INT_ARGB);
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
        CalqueToolBar calqueToolBar = (CalqueToolBar) this.menus.get(Constantes.CALQUE_TOOL_BAR_ID);
        ((JButton) calqueToolBar.getButtons().get(calque.getName() + Constantes.IS_USED_CALQUE)).setIcon(new ImageIcon(toChange.getImage().getScaledInstance(Constantes.WIDTH_ICON, Constantes.HEIGHT_ICON, Image.SCALE_DEFAULT)));
    }
}
