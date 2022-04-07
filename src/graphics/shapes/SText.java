package graphics.shapes;

import graphics.Constantes;
import graphics.shapes.attributes.FontAttributes;

import java.awt.*;

public class SText extends Shape{
    private String text;
    private Point loc;

    public SText(Point point, String text){
        this.text = text;
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
        this.loc.x += x;
        this.loc.y += y;
    }

    @Override
    public Rectangle getBounds() {
        FontAttributes fontAttributes = (FontAttributes) this.getAttributes(Constantes.FONT_ATTRIBUTE);
        Rectangle rect = fontAttributes.getBounds(this.text);
        rect.translate(this.loc.x,this.loc.y);
        return rect;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitText(this);
    }

    @Override
    public void setSize(int i, int p) {

    }

    public String getText(){
        return this.text;
    }

    public void setText(String t){
        this.text = t;
    }
}
