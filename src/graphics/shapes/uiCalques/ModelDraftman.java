package graphics.shapes.uiCalques;

import graphics.shapes.SCalque;
import graphics.shapes.SModel;
import graphics.shapes.ui.ShapeDraftman;

public class ModelDraftman extends ShapeDraftman {
    public void visitModel(SModel m){
        for (SCalque c : m.getCalques()){
            c.accept(this);
        }
    }

    public void visitCalque(SCalque c){
        if (c.isPaint()) {
            c.accept(this);
        }
    }
}
