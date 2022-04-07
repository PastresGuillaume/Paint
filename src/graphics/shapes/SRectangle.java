package graphics.shapes;

import java.awt.*;

public class SRectangle extends Shape{
    private Rectangle rect;

    public SRectangle(Point point, int x, int y){
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
    public void setSize(int i, int p) {this.rect = new Rectangle(this.rect.getLocation(), new Dimension(i,p));}

    public Rectangle getRect(){
        return this.rect;
    }
}
