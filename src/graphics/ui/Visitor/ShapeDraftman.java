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
//    TODO rotation for all shape
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
        if (rotation == null)
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
        }
        else{
            drawRectangle(r, colorAttributes);
        }

        if(selection.isSelected()){
            drawSelection(r);
        }

    }

    @Override
    public void visitPolygon(SPolygone poly){
        Rectangle rectangle = poly.getBounds();

        ColorAttributes colorAttributes =(ColorAttributes)poly.getAttributes(Constantes.COLOR_ATTRIBUTE);
        SelectionAttributes selection = (SelectionAttributes) poly.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        if (colorAttributes == null)
            colorAttributes = Constantes.DEFAULT_COLOR_ATTRIBUTES;
        if (selection == null)
            selection = new SelectionAttributes();

        RotationAttributes rotation = (RotationAttributes)poly.getAttributes(Constantes.ROTATION_ATTRIBUTES);
        if (poly.getAttributes(Constantes.ROTATION_ATTRIBUTES) == null)
            rotation = Constantes.DEFAULT_ROTATION_ATTRIBUTES;

        if (rotation.angle != 0.){
            Graphics g = this.graphics.create();
            Graphics2D g2d = (Graphics2D) g;
            Rectangle r = poly.getBounds();

            g2d.setTransform(AffineTransform.getRotateInstance(-rotation.angle, (int)(r.x+r.width/2), (int)(r.y+r.height/2)));


            if (colorAttributes.filled) {
                g2d.setColor(colorAttributes.filledColor);
                g2d.drawPolygon(poly.getPoly());
            }
            if (colorAttributes.stroked) {
                g2d.setColor(colorAttributes.strokedColor);
                g2d.drawPolygon(poly.getPoly());
            }

            AffineTransform at = AffineTransform.getRotateInstance(0, (int)(r.x+r.width/2), (int)(r.y+r.height/2));
            java.awt.Shape rotatedRect = at.createTransformedShape(poly.getPoly());
        }
        else {
            if (colorAttributes.filled) {
                this.graphics.setColor(colorAttributes.filledColor);
                this.graphics.drawPolygon(poly.getPoly());
            }
            if (colorAttributes.stroked) {
                this.graphics.setColor(colorAttributes.strokedColor);
                this.graphics.drawPolygon(poly.getPoly());

            }
        }
        if(selection.isSelected())
            drawSelection(rectangle);
    }

    @Override
    public void visitEllipsis(Shape ellipsis) {
        Rectangle rectangle = ellipsis.getBounds();
        ColorAttributes color = (ColorAttributes)ellipsis.getAttributes(Constantes.COLOR_ATTRIBUTE);
        SelectionAttributes selection = (SelectionAttributes) ellipsis.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        RotationAttributes rotation = (RotationAttributes) ellipsis.getAttributes(Constantes.ROTATION_ATTRIBUTES);

        if (color == null)
            color = Constantes.DEFAULT_COLOR_ATTRIBUTES;

        if (selection == null)
            selection = Constantes.DEFAULT_SELECTION_ATTRIBUTES;

        if (rotation == null)
            rotation = Constantes.DEFAULT_ROTATION_ATTRIBUTES;


        if (rotation.angle != 0.){
            Graphics g = this.graphics.create();
            Graphics2D g2d = (Graphics2D) g;

            g2d.setTransform(AffineTransform.getRotateInstance(-rotation.angle, (int)(rectangle.x+rectangle.width/2), (int)(rectangle.y+rectangle.height/2)));

            if(color.filled) {
                g2d.setColor(color.filledColor);
                g2d.fillOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }
            if(color.stroked) {
                g2d.setColor(color.strokedColor);
                g2d.drawOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }

            AffineTransform at = AffineTransform.getRotateInstance(0, (int)(rectangle.x+rectangle.width/2), (int)(rectangle.y+rectangle.height/2));
            java.awt.Shape rotatedRect = at.createTransformedShape(rectangle);
        }
        else {
            if (color.filled) {
                this.graphics.setColor(color.filledColor);
                this.graphics.fillOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }
            if (color.stroked) {
                this.graphics.setColor(color.strokedColor);
                this.graphics.drawOval((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }
        }
        if (selection.isSelected())
            drawSelection(rectangle);
        }

    @Override
    public void visitText(SText text) {
        SelectionAttributes selection = (SelectionAttributes) text.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        if (selection==null)
            selection = Constantes.DEFAULT_SELECTION_ATTRIBUTES;

        ColorAttributes color = (ColorAttributes) text.getAttributes(Constantes.COLOR_ATTRIBUTE);
        if (color==null)
            color = Constantes.DEFAULT_COLOR_ATTRIBUTES;

        FontAttributes font = (FontAttributes) text.getAttributes(Constantes.FONT_ATTRIBUTE);
        if (font==null)
            font = Constantes.DEFAULT_FONT_ATTRIBUTES;

        RotationAttributes rotation = (RotationAttributes) text.getAttributes(Constantes.ROTATION_ATTRIBUTES);
        if (rotation==null)
            rotation = Constantes.DEFAULT_ROTATION_ATTRIBUTES;

        Rectangle rectangle = text.getBounds();

        if (rotation.angle!=0){
            Graphics g = this.graphics.create();
            Graphics2D g2d = (Graphics2D) g;

            g2d.setTransform(AffineTransform.getRotateInstance(-rotation.angle, (int)(rectangle.x+rectangle.width/2), (int)(rectangle.y+rectangle.height/2)));

            g2d.setFont(font.font);

            if(color.filled) {
                g2d.setColor(color.filledColor);
                g2d.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }
            if(color.stroked) {
                g2d.setColor(color.strokedColor);
                g2d.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
            }

            g2d.setColor(font.fontColor);
            g2d.drawString(text.getText(), text.getLoc().x, text.getLoc().y);

            AffineTransform at = AffineTransform.getRotateInstance(0, (int)(rectangle.x+rectangle.width/2), (int)(rectangle.y+rectangle.height/2));
            java.awt.Shape rotatedRect = at.createTransformedShape(rectangle);
        }
        else {
            this.graphics.setFont(font.font);
            drawRectangle(rectangle, color);

            this.graphics.setColor(font.fontColor);
            this.graphics.drawString(text.getText(), text.getLoc().x, text.getLoc().y);
        }
        if(selection.isSelected())
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
        SelectionAttributes selection = (SelectionAttributes) image.getAttributes(Constantes.SELECTION_ATTRIBUTE);
        if(selection != null && selection.isSelected())
            drawSelection(r);
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


//    private void drawRotation(Rectangle rectangle) {
//        Graphics2D g2d = (Graphics2D) this.graphics;
//        AffineTransform oldTransform = g2d.getTransform();
//        g2d.setTransform(AffineTransform.getRotateInstance(0.2));
//        g2d.draw(rectangle);
//        g2d.setTransform(oldTransform);
//    }
}
