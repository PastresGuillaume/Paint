package graphics.ui.Visitor;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.formes.*;
import graphics.formes.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor{
    private Graphics graphics;

    public void setGraphics(Graphics g){
        this.graphics = g;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    private  void drawRectangle(Rectangle rectangle, ColorAttributes color){
        if(color.filled) {
            this.graphics.setColor(color.filledColor);
            this.graphics.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
        if(color.stroked) {
            this.graphics.setColor(color.strokedColor);
            this.graphics.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }

    @Override
    public void visitRectangle(SRectangle rectangle) {
        Rectangle r = rectangle.getBounds();

        ColorAttributes colorAttributes = (ColorAttributes)rectangle.getAttributes(Constantes.COLOR_ATTRIBUTE);
        if (colorAttributes == null)
            colorAttributes = Constantes.DEFAULT_COLOR_ATTRIBUTES;

        SelectionAttributes selection = (SelectionAttributes) rectangle.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        if (selection == null)
            selection = Constantes.DEFAULT_SELECTION_ATTRIBUTES;

        RotationAttributes rotation = (RotationAttributes)rectangle.getAttributes(Constantes.ROTATION_ATTRIBUTES);
        if (rectangle.getAttributes(Constantes.ROTATION_ATTRIBUTES) == null)
            rotation = Constantes.DEFAULT_ROTATION_ATTRIBUTES;

        if (rotation.angle != 0.){
            Graphics g = this.graphics.create();
            Graphics2D g2d = (Graphics2D) g;

            g2d.setTransform(AffineTransform.getRotateInstance(-rotation.angle, (int)(r.x+r.width/2), (int)(r.y+r.height/2)));

            if(colorAttributes.filled) {
                g2d.setColor(colorAttributes.filledColor);
                g2d.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());

            }
            if(colorAttributes.stroked) {
                g2d.setColor(colorAttributes.strokedColor);
                g2d.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
            }

            AffineTransform at = AffineTransform.getRotateInstance(0, (int)(r.x+r.width/2), (int)(r.y+r.height/2));
            java.awt.Shape rotatedRect = at.createTransformedShape(r);
            ((Graphics2D) g).draw(rotatedRect);
        }
        else{
            drawRectangle(r, colorAttributes);
        }

        if(selection.isSelected()){
            drawSelection(r);
        }

    }


    @Override
    public void visitEllipsis(Shape ellipsis) {
        Rectangle rectangle = ellipsis.getBounds();
        ColorAttributes color= (ColorAttributes)ellipsis.getAttributes(Constantes.COLOR_ATTRIBUTE);
        SelectionAttributes selection = (SelectionAttributes) ellipsis.getAttributes(Constantes.SELECTION_ATTRIBUTE);

        if (color == null)
            color = Constantes.DEFAULT_COLOR_ATTRIBUTES;
        if(color.filled) {
            this.graphics.setColor(color.filledColor);
            this.graphics.fillOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
        if(color.stroked) {
            this.graphics.setColor(color.strokedColor);
            this.graphics.drawOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
        if(selection != null && selection.isSelected())
            drawSelection(rectangle);
    }


    @Override
    public void visitText(SText text) {
        SelectionAttributes selection = (SelectionAttributes) text.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        ColorAttributes color= (ColorAttributes) text.getAttributes(Constantes.COLOR_ATTRIBUTE);
        FontAttributes font= (FontAttributes) text.getAttributes(Constantes.FONT_ATTRIBUTE);
        Rectangle rectangle = text.getBounds();

        if(font ==null)
            font = Constantes.DEFAULT_FONT_ATTRIBUTES;
        this.graphics.setFont(font.font);

        if (color == null)
            color = Constantes.DEFAULT_COLOR_ATTRIBUTES;
        drawRectangle(rectangle,color);

        this.graphics.setColor(font.fontColor);
        this.graphics.drawString(text.getText(), text.getLoc().x, text.getLoc().y);

        if(selection != null && selection.isSelected())
            drawSelection(rectangle);
    }

    @Override
    public void visitCollection(SCollection collection) {
        Iterator<Shape> i = collection.iterator();
        SelectionAttributes selection = (SelectionAttributes) collection.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        while (i.hasNext())
            i.next().accept(this);

        if(selection != null && selection.isSelected())
            drawSelection(collection.getBounds());
    }

    @Override
    public void visitImage(SImage image) {
        Rectangle r = image.getBounds();
        this.graphics.drawImage(image.getImage(), r.x, r.y, r.width, r.height, null);
        this.drawSelectedImage(image);
    }

    public void drawSelectedImage(SImage shape){
        SelectionAttributes selectionAttributes = (SelectionAttributes)shape.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        if (selectionAttributes.isSelected()){
            Rectangle r = shape.getBounds();
            this.graphics.setColor(Color.BLACK);
            this.graphics.drawRect(r.x,r.y,r.width,r.height);
            if (Constantes.COLOR_SHAPE_SELECTED.filled){
                this.graphics.setColor(Constantes.COLOR_SHAPE_SELECTED.filledColor);
            }
        }
    }

    private void drawSelection(Rectangle rectangle) {
        if (Constantes.SELECTION_COLOR.filled){
            this.graphics.setColor(Constantes.SELECTION_COLOR.filledColor);
            this.graphics.fillRect((int) rectangle.getX() - Constantes.SIZE_SHAPE_SELECTED, (int) rectangle.getY() - Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED);
            this.graphics.fillRect((int) (rectangle.getX() + rectangle.width), (int) rectangle.getY() + rectangle.height, Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED);
        }
        if (Constantes.SELECTION_COLOR.stroked) {
            this.graphics.setColor(Constantes.SELECTION_COLOR.strokedColor);
            this.graphics.drawRect((int) rectangle.getX() - Constantes.SIZE_SHAPE_SELECTED, (int) rectangle.getY() - Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED);
            this.graphics.drawRect((int) (rectangle.getX() + rectangle.width), (int) rectangle.getY() + rectangle.height, Constantes.SIZE_SHAPE_SELECTED, Constantes.SIZE_SHAPE_SELECTED);
        }
    }


    private void drawRotation(Rectangle rectangle) {
        Graphics2D g2d = (Graphics2D) this.graphics;
        AffineTransform oldTransform = g2d.getTransform();
        g2d.setTransform(AffineTransform.getRotateInstance(0.2));
        g2d.draw(rectangle);
        g2d.setTransform(oldTransform);
    }
}
