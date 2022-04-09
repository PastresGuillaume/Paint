package graphics.ui.Visitor;

import graphics.formes.*;

public interface ShapeVisitor {
    void visitRectangle(SRectangle rectangle);

    void visitCircle(SCircle circle);

    void visitText(SText text);

    void visitCollection(SCollection collection);

    void visitImage(SImage image);

    void visitStack(SStack stack);
}
