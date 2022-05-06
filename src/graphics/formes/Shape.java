package graphics.formes;

import graphics.Constantes;
import graphics.attributes.Attributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1783389611714034615L;
    private ArrayList<Attributes> attributes;

    Shape(){
        this.attributes = new ArrayList<>();
    }

    public abstract Point getLoc();

    public abstract void setLoc(Point point);

    public abstract void translate(int x, int y);

    public abstract Rectangle getBounds();

    public abstract void accept(ShapeVisitor visitor);

    public abstract void resize(int width, int height);

    public void addAttributes(Attributes attributes){
        this.attributes.add(attributes);
    }

    public Attributes getAttributes(String attributeName){
        for (Attributes attributes : this.attributes){
            if(attributeName.equals(attributes.getId())){
                return attributes;
            }
        }
        return null;
    }

    public void unselect() {
        try{
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
        catch (NullPointerException npe){
            this.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
    }

    public void select() {
        try{
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
        }
        catch (NullPointerException npe){
            this.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
        }
    }

    public boolean isSelected() {
        try{
            return ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected();
        }
        catch (NullPointerException npe){
            return false;
        }
    }

    public abstract void zoomIn();

    public abstract void zoomOut();

    protected void force_translate(int dx, int dy) {
        this.translate(dx, dy);
    }
}
