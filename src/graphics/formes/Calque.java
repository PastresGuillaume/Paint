package graphics.formes;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Calque implements Runnable{
    public static int nb_Calque=0;

    private SCollection content;
    private String name;
    private boolean isPaint;
    private boolean isUsed;
    private ScheduledExecutorService thread;

    public Calque(){
        this.content = new SCollection();
        this.content.addAttributes(new SelectionAttributes());
        this.content.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
        this.name = "Calque "+nb_Calque;
        this.isPaint = true;
        this.isUsed = false;
        Calque.nb_Calque++;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this, 0, Constantes.DELTA_REFRESH, Constantes.TIME_UNIT);
    }

    public void setThread(ScheduledExecutorService thread) {
        this.thread = thread;
    }

    public void kill(){
        this.thread.shutdown();
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
        for (graphics.formes.Shape s: this.content.getElement()){
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
        this.content.accept(visitor);
    }

    public void add(Shape s){
        this.content.add(s);
    }


}
