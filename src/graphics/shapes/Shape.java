package graphics.shapes;

import graphics.Constantes;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape implements Runnable{
    private ArrayList<Attributes> attributes;

    Shape(){
        this.attributes = new ArrayList<>();
    }

    public abstract Point getLoc();

    public abstract void setLoc(Point point);

    public abstract void translate(int x, int y);

    public abstract Rectangle getBounds();

    public abstract void accept(ShapeVisitor visitor);

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
        ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
    }

    public void select() {
        ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
    }

    @Override
    public void run(){
    }
}
