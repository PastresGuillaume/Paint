package graphics.formes;

import graphics.Constantes;
import graphics.attributes.Attributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

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

    public abstract float perimetre();

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

    @Override
    public void run(){

    }

    public void setSize(int abs, int abs1) {
        //TODO Thomas
    }

    public boolean isSelected() {
        try{
            return ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected();
        }
        catch (NullPointerException npe){
            return false;
        }
    }

    public Point parcourt(double distance){
        return null;
    }

    public double getPerimetre(){
        return 0;
    }

}
