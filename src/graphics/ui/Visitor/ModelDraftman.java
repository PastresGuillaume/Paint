package graphics.ui.Visitor;

import graphics.formes.Calque;
import graphics.formes.SModel;

/**
 * Définition du dessinateur pour le modèle.
 */
public class ModelDraftman extends ShapeDraftman {
    /**
     * Fonction de dessin du modèle.
     *
     * @param m Modèle.
     */
    public void visitModel(SModel m){
        for (Calque c : m.getCalques()){
            if(c.isPaint())
                c.accept(this);
        }
    }
}
