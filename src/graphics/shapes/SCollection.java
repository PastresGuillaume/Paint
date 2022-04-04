package graphics.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape{
    private ArrayList<Shape> shapes;

    public SCollection(){
        this.shapes = new ArrayList<Shape>();
    
    }

    @Override
    public Point getLoc() {
        Point point = new Point(-1, -1);
        Iterator<Shape> shapes = this.iterator();
        while(shapes.hasNext()) {
            Shape shape = shapes.next();
            Point point2 = shape.getLoc();
            if (point.x ==-1 ){
                point.x = point2.x;
                point.y = point2.y;
            }
            else if (point.x > point2.x){
                point.x = point2.x;
            }
            else if (point.y > point2.y){
                point.y = point2.y;
            }
        }
        return point;
    }


    @Override
    public void setLoc(Point point) {
    }

    @Override
    public void translate(int x, int y) {
        Iterator<Shape> iterator = this.iterator();
        while (iterator.hasNext()){
            iterator.next().translate(x, y);
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();;
        if (this.shapes.size() != 0){
            bounds = shapes.get(0).getBounds();
            for (Shape shape : this.shapes) {
                bounds = new Rectangle(bounds.union(shape.getBounds()));
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
}
