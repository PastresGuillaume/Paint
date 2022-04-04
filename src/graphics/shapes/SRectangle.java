package graphics.shapes;

import java.awt.*;

public class SRectangle extends Shape{
    private Rectangle rect;

    public SRectangle(Point point, int x, int y){
        this.rect = new Rectangle(point.x, point.y, x, y);
    }

    @Override
    public Point getLoc() {
        return new Point(this.rect.x, this.rect.y);
    }

    @Override
    public void setLoc(Point point) {
        this.rect = new Rectangle(point.x, point.y);
    }

    @Override
    public void translate(int x, int y) {
        this.rect.x += x;
        this.rect.y += y;
    }

    @Override
    public Rectangle getBounds() {
        return this.rect.getBounds();
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }

    public Rectangle getRect(){
        return this.rect;
    }
}
