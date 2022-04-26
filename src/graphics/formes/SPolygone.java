package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SPolygone extends Shape {
    private Polygon poly;

    /*
    un polygon est composé de :
    private int npoints;
    private int[] xpoints;
    private int[] ypoints;
    */

    public void SPolygon(int[] xpoints, int[] ypoints, int npoints){this.poly = new Polygon(xpoints,ypoints,npoints); }

    public void SPolygonAjoutPoint(int x,int y){
        this.poly.addPoint(x,y);
        poly.npoints=+1;
    }

    @Override
    public Point getLoc() {
        Point point = new Point(this.poly.xpoints[0] , this.poly.ypoints[0]);
        return (point);
    }

    @Override
    public void setLoc(Point point) {
        //maybe only scaling
        //TODO see this code
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
    public void accept(ShapeVisitor visitor) {visitor.visitPolygon(this.poly);

    }

    @Override
    public void resize(int width, int height) {
        //maybe only scaling
        //TODO see this code
    }

    @Override
    public void zoomIn() {

    }

    @Override
    public void zoomOut() {

    }
}
