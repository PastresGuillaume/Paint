package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor{
    public static ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);
    private Graphics graphics;

    public void setGraphics(Graphics g){
        this.graphics = g;
    }

    @Override
    public void visitRectangle(SRectangle rectangle) {
        Rectangle r = rectangle.getBounds();
        ColorAttributes colorAttributes = (ColorAttributes)rectangle.getAttributes("ColorAttributes");
        if (colorAttributes.filled){
            this.graphics.setColor(colorAttributes.filledColor);
            this.graphics.fillRect(r.x, r.y, r.width, r.height);
        }
        if (colorAttributes.stroked){
            this.graphics.setColor(colorAttributes.strokedColor);
            this.graphics.drawRect(r.x, r.y, r.width, r.height);
        }
        this.drawSelected(rectangle);
    }

    @Override
    public void visitCircle(SCircle circle) {
        Rectangle r = circle.getBounds();
        ColorAttributes colorAttributes = (ColorAttributes)circle.getAttributes("ColorAttributes");
        if (colorAttributes.filled){
            this.graphics.setColor(colorAttributes.filledColor);
            this.graphics.fillOval(r.x, r.y, r.width, r.height);
        }
        if (colorAttributes.stroked){
            this.graphics.setColor(colorAttributes.strokedColor);
            this.graphics.drawOval(r.x, r.y, r.width, r.height);
        }
        this.drawSelected(circle);
    }

    @Override
    public void visitText(SText text) {
        /*
        Rectangle r = text.getBounds();
        ColorAttributes colorAttributes = (ColorAttributes)text.getAttributes("ColorAttributes");
        this.graphics.setColor(colorAttributes.filledColor);
        this.graphics.fillRect(r.x, r.y, r.width, r.height);
        this.graphics.setColor(colorAttributes.strokedColor);
        this.graphics.drawRect(r.x, r.y, r.width, r.height);
         */
        FontAttributes fontAttributes = (FontAttributes)text.getAttributes(("FontAttributes"));
        this.graphics.setColor(fontAttributes.fontColor);
        this.graphics.setFont(fontAttributes.font);
        this.graphics.drawString(text.getText(), text.getLoc().x, text.getLoc().y);
        this.drawSelected(text);
    }

    @Override
    public void visitCollection(SCollection collection) {
        Iterator<Shape> shapeIterator = collection.iterator();
        while(shapeIterator.hasNext()) {
            shapeIterator.next().accept(this);
        }
        this.drawSelected(collection);
    }

    @Override
    public void visitImage(SImage image) {
        Rectangle r = image.getBounds();
        this.graphics.drawImage(image.getImage(), r.x, r.y, r.width, r.height, null);
        this.drawSelected(image);
    }

    public void drawSelected(Shape shape){
        SelectionAttributes selectionAttributes = (SelectionAttributes)shape.getAttributes("SelectionAttributes");
        if (selectionAttributes.isSelected()){
            Rectangle r = shape.getBounds();
            this.graphics.setColor(Color.BLACK);
            this.graphics.drawRect(r.x,r.y,r.width,r.height);
        }
    }
}
