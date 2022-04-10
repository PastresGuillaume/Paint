package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SRectangle extends Shape{
    private Rectangle rect;


    /*
   @Override
   public static Double Angle(){
       return (this.angle);
   }

   @Override
   public void ModifAngle (Double angle){
       this.angle = angle;
   }*/

    public SRectangle(Point point, int x, int y ){
        this.rect = new Rectangle(point.x, point.y, x, y);
    }

    @Override
    public Point getLoc() {
        return this.rect.getLocation();
    }

    @Override
    public void setLoc(Point point) {
        this.rect.setLocation(point);
    }

    @Override
    public void translate(int x, int y) {
        this.rect.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return this.rect.getBounds();
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }

    @Override
    public void resize(int width, int height) {
        this.rect.width = width;
        this.rect.height = height;
    }

    public Rectangle getRect(){
        return this.rect;
    }

    @Override
    public void setSize(int i, int p) {
        this.rect.setSize(i, p);
    }

    @Override
    public Point parcourt(double distance){
        Point retour = this.getLoc();
        int delta_x = this.rect.width;
        int delta_y = this.rect.height;
        if (distance>delta_x){
            retour.translate(delta_x, 0);
            distance-=delta_x;
        }
        else{
            retour.translate((int) distance, 0);
            return retour;
        }
        if (distance>delta_y){
            retour.translate(0, delta_y);
            distance-=delta_y;
        }
        else{
            retour.translate(0, (int) distance);
            return retour;
        }
        if (distance>delta_x){
            retour.translate(-delta_x, 0);
            distance-=delta_x;
        }
        else{
            retour.translate((int) -distance, 0);
            return retour;
        }
        if (distance>delta_y){
            retour.translate(0, -delta_y);
            distance-=delta_y;
        }
        else{
            retour.translate(0, (int) -distance);
        }
        return retour;
    }

    @Override
    public double getPerimetre(){
        return 2*(this.rect.width+this.rect.height);
    }

    @Override
    public float perimetre(){
        return(2*rect.height+2*rect.width);
    };
}
