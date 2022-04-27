package graphics.formes;

import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Calque implements Serializable {
    @Serial
    private static final long serialVersionUID = -555108164984005640L;

    public static int nb_Calque=0;

    private SCollection content;
    private String name;
    private boolean isPaint;
    private boolean isUsed;

    public Calque(){
        this.content = new SCollection();
        this.content.addAttributes(new SelectionAttributes());
        this.content.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
        this.name = "Calque "+nb_Calque;
        this.isPaint = true;
        this.isUsed = false;
        Calque.nb_Calque++;
    }

    public void use(){
        this.isUsed = true;
    }

    public void notUse(){
        this.isUsed = false;
    }

    public void setPaint(boolean isPaint){
        this.isPaint = isPaint;
    }

    public boolean isPaint(){
        return this.isPaint;
    }

    public boolean isUsed(){
        return this.isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SCollection getContent() {
        return content;
    }

    public void setContent(SCollection content) {
        this.content = content;
    }

    public void accept(ShapeVisitor visitor) {
        this.content.accept(visitor);
    }

    public void add(Shape s){
        this.content.add(s);
    }

    public void zoomIn(){
        for (Shape shape : this.getContent().getElement()) {
            shape.zoomIn();
        }
    }

    public void zoomOut(){
        for (Shape shape : this.getContent().getElement()) {
            shape.zoomOut();
        }
    }

    public void force_translate(int dx, int dy) {
        this.content.force_translate(dx, dy);
    }

    public boolean isGame(){
        return false;
    }


}
