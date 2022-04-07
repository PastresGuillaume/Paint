package graphics.shapes;

import java.awt.*;

public class SCircle extends Shape{
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
        this.loc.x = point.x;
        this.loc.y = point.y;
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

    @Override
    public void setSize(int i, int p) {

    }

    public int getRadius(){
        return this.radius;
    }

    public void setRadius(int r){
        this.radius = r;
    }
}
