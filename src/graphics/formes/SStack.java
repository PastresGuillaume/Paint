package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class SStack extends Shape{
    private boolean changeShape;
    private Shape shape;
    private SStack nextSStack;
    private double vitesseNext;
    private double distanceNext;
    private double perimetre;

    SStack(Shape shape){
        this.changeShape = true;
        this.shape = shape;
        this.nextSStack = null;
        this.vitesseNext = 0;
        this.distanceNext = 0;
        this.perimetre = this.shape.getPerimetre();
    }

    public void setNextShape(SStack nextShape, double vitesseNext, double distanceNext) {
        nextShape.setChangeShape(false);
        this.nextSStack = nextShape;
        this.vitesseNext = vitesseNext;
        this.distanceNext = distanceNext;
        this.perimetre = this.shape.getPerimetre();
    }

    public void setNextShape(Shape nextShape, double vitesseNext, double distanceNext) {
        SStack newSStack = new SStack(nextShape);
        this.setNextShape(newSStack, vitesseNext, distanceNext);
    }

    public void setChangeShape(boolean changeShape) {
        this.changeShape = changeShape;
    }

    public void changeShape(Shape shape){
        if (this.changeShape){
            this.shape = shape;
            this.perimetre = this.shape.getPerimetre();
        }
    }

    @Override
    public Point getLoc() {
        return this.shape.getLoc();
    }

    @Override
    public void setLoc(Point point) {
        this.shape.setLoc(point);
    }

    @Override
    public void translate(int x, int y) {
        this.shape.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle retour = this.shape.getBounds();
        if (nextSStack !=null){
            retour = retour.union(this.nextSStack.getBounds());
        }
        return retour;
    }

    public SStack getNextSStack() {
        return nextSStack;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitStack(this);
    }

    public Shape getShape() {
        return shape;
    }

    public boolean isChangeShape() {
        return changeShape;
    }

    @Override
    public void run(){
        if (this.nextSStack!=null) {
            this.distanceNext += this.vitesseNext;
            this.distanceNext %= this.perimetre;
            this.nextSStack.setLoc(this.shape.parcourt(this.distanceNext));
        }
    }
}
