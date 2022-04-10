package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SEllipsis extends Shape{
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
    public float perimetre() {
        return 0;
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
}
