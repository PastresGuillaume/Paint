package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SCircle extends Shape {
    private int radius;
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
        return new Rectangle(this.loc.x, this.loc.y, 2*this.radius, 2*this.radius);
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }

    public int getRadius(){
        return this.radius;
    }

    public void setRadius(int r){
        this.radius = r;
    }
}
