package graphics.ui.Visitor;

import graphics.formes.Calque;
import graphics.formes.SModel;

public class ModelDraftman extends ShapeDraftman {
    public void visitModel(SModel m){
        for (Calque c : m.getCalques()){
            if( c.isPaint())
                c.accept(this);
        }
    }
}
