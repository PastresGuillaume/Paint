package graphics.ui.controllers;

import graphics.Constantes;
import graphics.attributes.SelectionAttributes;
import graphics.formes.*;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import java.awt.event.MouseEvent;
import java.io.File;

public class ImageCreator extends AbstractController{
    private Shape model;
    private final File file;


    public ImageCreator(Shape newModel, ModelView view, File file) {
        super(newModel);
        this.model = newModel;
        this.setView(view);
        this.file = file;
        this.unselectedAll();
    }

    private void unselectedAll(){
        try {
            for (Shape s : ((SModel) this.model).getCalqueUse().getElement())
                s.unselect();
        }
        catch (ClassCastException e){
            for (Shape s : ((SCollection) this.model).getElement())
                s.unselect();
        }
    }

    @Override
    public void setModel(Shape model)
    {
        this.model = model;
    }

    @Override
    public Shape getModel() {return this.model;}

    @Override
    public void mousePressed(MouseEvent e) {
        unselectedAll();
        SImage sImage = new SImage(e.getPoint(), this.file);
        sImage.addAttributes(new SelectionAttributes());
        ((SelectionAttributes) sImage.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
        ((SCollection) this.model).add(sImage);
        this.getView().setController(new ShapesController((Shape) this.getModel(), this.getView()));
    }

}
