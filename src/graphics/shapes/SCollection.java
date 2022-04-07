package graphics.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape{
    private ArrayList<Shape> shapes;

    public SCollection(){
        this.shapes = new ArrayList<>();
    }

    @Override
    public Point getLoc() {
        Iterator<Shape> shapes = this.iterator();
        if (!shapes.hasNext()) return null;

        Point point = shapes.next().getLoc();
        while(shapes.hasNext()) {
            Point point2 = shapes.next().getLoc();
            if (point.x > point2.x){
                point.x = point2.x;
            }
            if (point.y > point2.y){
                point.y = point2.y;
            }
        }
        return point;
    }

    @Override
    public void setLoc(Point point) {
        for (Shape s: this.shapes){
            s.setLoc(point);
        }
    }

    @Override
    public void translate(int x, int y) {
        for (Shape s: this.shapes){
            s.translate(x, y);
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();;
        if (this.shapes.size() != 0){
            bounds = shapes.get(0).getBounds();
            for (Shape shape : this.shapes) {
                bounds = bounds.union(shape.getBounds());
            }
        }
        return bounds;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCollection(this);
    }

    public Iterator<Shape> iterator(){
        return this.shapes.iterator();
    }

    public void add(Shape shape){
        this.shapes.add(shape);
    }

    public ArrayList<Shape> getElement() {
        return this.shapes;
    }

    @Override
    public void unselect(){
        super.unselect();
        for (Shape s: this.shapes){
            s.unselect();
        }
    }

    @Override
    public void select(){
        super.unselect();
        for (Shape s: this.shapes){
            s.select();
        }
    }

    @Override
    public void setSize(int i, int p) {

    }
}
