package graphics.shapes;

import graphics.Constantes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SCalque extends Shape{
    public static int nb_Calque=0;

    private ArrayList<Shape> content;
    private String name;
    private boolean isPaint;
    private boolean isUsed;

    public SCalque(){
        this.content = new ArrayList<>();
        this.name = "Calque "+nb_Calque;
        this.isPaint = true;
        this.isUsed = false;
        SCalque.nb_Calque++;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this, 0, Constantes.DELTA_REFRESH, Constantes.TIME_UNIT);
    }

    public void used(){
        this.isUsed = true;
    }

    public void notUsed(){
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
        for (Shape s: this.content){
            s.run();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Shape> getContent() {
        return content;
    }

    @Override
    public Point getLoc() {
        return new Point(0, 0);
    }

    @Override
    public void setLoc(Point point) {
    }

    @Override
    public void translate(int x, int y) {
        for (Shape s: this.content){
            s.translate(x, y);
        }
    }

    @Override
    public Rectangle getBounds() {
        Iterator<Shape> i = this.content.iterator();
        if (i.hasNext()) {
            Rectangle retour = i.next().getBounds();
            while (i.hasNext()){
                retour = retour.union(i.next().getBounds());
            }
            return retour;
        }
        return null;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        for (Shape s: this.content){
            s.accept(visitor);
        }
    }
}
