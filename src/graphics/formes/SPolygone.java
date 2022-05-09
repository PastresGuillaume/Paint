package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

public class SPolygone extends Shape {
    @Serial
    private static final long serialVersionUID = -88192407312194124L;

    private Polygon poly;

    /*
    un polygon est composé de :
    private int npoints;
    private int[] xpoints;
    private int[] ypoints;
    */

    public void SPolygon(int[] xpoints, int[] ypoints, int npoints){this.poly = new Polygon(xpoints,ypoints,npoints);}

    public void SPolygonAjoutPoint(int x,int y){
        this.poly.addPoint(x,y);
        this.poly.npoints+=1;
    }

    @Override
    public Point getLoc() {
        Point point = new Point(this.poly.xpoints[0] , this.poly.ypoints[0]);
        return (point);
    }

    @Override
    public void setLoc(Point point) {
        this.poly.translate(point.x-this.poly.xpoints[0], point.y-this.poly.ypoints[0]);
    }

    public int[] GetxPoints(){
        return(this.poly.xpoints);
    }

    public int[] GetyPoints(){
        return(this.poly.ypoints);
    }

    public int GetnPoints(){
        return(this.poly.npoints);
    }

    @Override
    public void translate(int x, int y) {
        this.poly.translate(x,y);
    }

    @Override
    public Rectangle getBounds() {
        return this.poly.getBounds();
    }

    @Override
    public void accept(ShapeVisitor visitor) {visitor.visitPolygon(this);

    }

    @Override
    public void resize(int width, int height) {
        //maybe only scaling
        //TODO see this code
    }

    @Override
    public void zoomIn() {
        for (int length = 0; length<this.poly.npoints; length++) {
            this.poly.xpoints[length] *= 2;
            this.poly.ypoints[length] *= 2;
        }
    }

    @Override
    public void zoomOut() {
        for (int length = 0; length<this.poly.npoints; length++) {
            this.poly.xpoints[length] /= 2;
            this.poly.ypoints[length] /= 2;
        }
    }
}
