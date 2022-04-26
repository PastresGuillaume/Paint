package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SRectangle extends Shape{
    private Rectangle rect;

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

    public Rectangle getRect(){return this.rect;}

    @Override
    public void setSize(int i, int p) {
        this.rect.setSize(i, p);
    }

    @Override
    public void zoomIn() {
        this.rect.height*=2;
        this.rect.width*=2;
        this.rect.x*=2;
        this.rect.y*=2;
    }

    @Override
    public void zoomOut() {
        this.rect.height/=2;
        this.rect.width/=2;
        this.rect.x/=2;
        this.rect.y/=2;
    }
}
