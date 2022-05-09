package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

public class SEllipsis extends Shape{
    @Serial
    private static final long serialVersionUID = -5864575391362709945L;

    private Rectangle rect;

    public SEllipsis(Point point, int r){
        this.rect = new Rectangle(point.x, point.y ,r*2, r*2);
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
        visitor.visitEllipsis(this);
    }

    public Rectangle getRect(){
        return this.rect;
    }

    public void resize(int width, int height){
        this.rect.width = width;
        this.rect.height = height;
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
