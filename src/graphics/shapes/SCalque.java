package graphics.shapes;

import graphics.Constantes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SCalque implements Runnable{
    public static int nb_Calque=0;

//    private ArrayList<Shape> content;
    private SCollection content;
    private String name;
    private boolean isPaint;
    private boolean isUsed;

    public SCalque(){
        this.content = new SCollection();
        this.content.addAttributes(new SelectionAttributes());
        this.content.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
        this.name = "Calque "+nb_Calque;
        this.isPaint = true;
        this.isUsed = false;
        SCalque.nb_Calque++;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this, 0, Constantes.DELTA_REFRESH, Constantes.TIME_UNIT);
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

    @Override
    public void run(){
        for (Shape s: this.content.getElement()){
            s.run();
        }
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

    public void accept(ShapeVisitor visitor) {
//        for (Shape s: this.content){
//            s.accept(visitor);
//        }
        this.content.accept(visitor);
    }

    public void add(Shape s){
        this.content.add(s);
    }
}
