package graphics.formes;

import graphics.Constantes;
import graphics.attributes.FontAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

public class SText extends Shape{
    @Serial
    private static final long serialVersionUID = 3460010181915715737L;

    private String text;
    private Point loc;

    public SText(Point point, String text){
        this.text = text;
        this.loc = new Point(point);
    }

    @Override
    public Point getLoc() {
        return this.loc;
    }

    @Override
    public void setLoc(Point point) {
        this.loc.setLocation(point);
    }

    @Override
    public void translate(int x, int y) {
        this.loc.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        FontAttributes fontAttributes = (FontAttributes) this.getAttributes(Constantes.FONT_ATTRIBUTE);
        if (fontAttributes==null){
            fontAttributes = Constantes.DEFAULT_FONT_ATTRIBUTES;
        }
        Rectangle rect = fontAttributes.getBounds(this.text);
        rect.translate(this.loc.x,this.loc.y);
        return rect;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitText(this);
    }

    @Override
    public void resize(int width, int height) {
        //Fixme resize Text when given negative height/width
        FontAttributes fontAttributes = (FontAttributes) this.getAttributes(Constantes.FONT_ATTRIBUTE);
        if (fontAttributes==null){
            fontAttributes = Constantes.DEFAULT_FONT_ATTRIBUTES;
        }
        int newFontSize = fontAttributes.font.getSize()*width/this.getBounds().width;
        fontAttributes.font = fontAttributes.font.deriveFont(fontAttributes.font.getStyle(), newFontSize);
    }

    public String getText(){
        return this.text;
    }

    public void setText(String t){
        this.text = t;
    }

    @Override
    public void zoomIn() {
        this.loc.x*=2;
        this.loc.y*=2;
    }

    @Override
    public void zoomOut() {
        this.loc.x/=2;
        this.loc.y/=2;
    }
}
