package graphics.ui.View;

import graphics.formes.SCollection;
import graphics.formes.Shape;
import graphics.ui.Visitor.ShapeDraftman;
import graphics.ui.controllers.AbstractController;

import java.awt.*;

/**
 * Définition de la vue d'une forme.
 */
public class ShapesView extends View {
    /**
     * Dessinateur d'une forme.
     */
    private ShapeDraftman draftman;

    /**
     * Constructeur.
     *
     * @param model Forme.
     */
    public ShapesView(Object model) {
        super(model);
        this.draftman = new ShapeDraftman();
    }

    /**
     * Retourne le controller par défaut.
     *
     * @param model Le modèle du controller.
     * @return Le controller associé.
     */
    @Override
    public AbstractController defaultController(Object model) {
        return new AbstractController((Shape) this.getModel());
    }

    /**
     * Rafraichissement de l'écran.
     *
     * @param g Graphics
     */
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
}
