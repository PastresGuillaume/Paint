package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SCircle extends Shape {
    private double radius;
    private Point loc;

    public SCircle(Point point, int r){
        this.radius = r;
        this.loc = new Point(point.x, point.y);
    }

    @Override
    public Point getLoc() {
        return this.loc;
    }

    @Override
    public void setLoc(Point point) {
        this.loc.setLocation(point);
    }

    @Override
    public void translate(int x, int y) {
        this.loc.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.loc.x, this.loc.y, (int)(2*this.radius), (int)(2*this.radius));
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitEllipsis(this);
    }

    @Override
    public void resize(int width, int height) {
        //maybe only scaling
        //TODO see this code
    }

    public void setSize(int i,int p) {
        this.radius = (int) Math.sqrt( i*i + p*p );
    }

    public int getRadius(){
        return (int)this.radius;
    }

    public void setRadius(int r){
        this.radius = r;
    }

    @Override
    public void zoomIn() {
        this.loc.translate(this.loc.x, this.loc.y);
        this.radius*=2;
    }

    @Override
    public void zoomOut() {
        this.loc.x = this.loc.x/2;
        this.loc.y = this.loc.y/2;
        this.radius/=2;
    }
}
