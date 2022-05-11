package graphics.ui.Visitor;

import graphics.formes.*;
import graphics.formes.Shape;

/**
 * Définition du ShapeVisitor
 */
public interface ShapeVisitor {
    /**
     * Traçage d'un rectangle.
     *
     * @param rectangle Rectangle à dessiner.
     */
    void visitRectangle(SRectangle rectangle);

    /**
     * Traçage d'une ellipse.
     *
     * @param ellipsis Ellipse à dessiner.
     */
    void visitEllipsis(Shape ellipsis);

    /**
     * Traçage d'un texte.
     *
     * @param text Texte à dessiner.
     */
    void visitText(SText text);

    /**
     * Traçage d'un polygone.
     *
     * @param Poly Polygone à dessiner.
     */
    void visitPolygon(SPolygone Poly);

    /**
     * Traçage d'une collection.
     *
     * @param collection Collection à dessiner.
     */
    void visitCollection(SCollection collection);

    /**
     * Traçage d'une image.
     *
     * @param image Image à dessiner.
     */
    void visitImage(SImage image);
}
