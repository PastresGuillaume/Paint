package graphics.ui.Visitor;

import graphics.formes.*;
import graphics.formes.Shape;

import java.awt.*;

public interface ShapeVisitor {
    void visitRectangle(SRectangle rectangle);

    void visitEllipsis(Shape ellipsis);

    void visitText(SText text);

    void visitPolygon(SPolygone Poly);

    void visitCollection(SCollection collection);

    void visitImage(SImage image);


}
