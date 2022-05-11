package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

/**
 * Définition des rectangles.
 */
public class SRectangle extends Shape {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 23532633816002260L;

    /**
     * Le rectangle en question.
     */
    private Rectangle rect;

    /**
     * Constructeur.
     * 
     * @param point Point supérieur gauche du rectangle.
     * @param x Largeur.
     * @param y Longueur.
     */
    public SRectangle(Point point, int x, int y ){
        this.rect = new Rectangle(point.x, point.y, x, y);
    }

    /**
     * Retourne le coin supérieur gauche du rectangle.
     *
     * @return Le coin supérieur gauche du rectangle.
     */
    @Override
    public Point getLoc() {
        return this.rect.getLocation();
    }

    /**
     * Translate le rectangle pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche du rectangle.
     */
    @Override
    public void setLoc(Point point) {
        this.rect.setLocation(point);
    }

    /**
     * Translate le rectangle.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.rect.translate(dx, dy);
    }

    /**
     * Retourne le rectangle.
     *
     * @return Le rectangle.
     */
    @Override
    public Rectangle getBounds() {
        return this.rect;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un rectangle.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }

    /**
     * Redimensionne le rectangle.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    @Override
    public void resize(int width, int height) {
        this.rect.setSize(new Dimension(width,height));
    }

    /**
     * Retourne le rectangle.
     *
     * @return Le rectangle.
     */
    public Rectangle getRect(){return this.rect;}

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        this.rect.height*=2;
        this.rect.width*=2;
        this.rect.x*=2;
        this.rect.y*=2;
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        this.rect.height/=2;
        this.rect.width/=2;
        this.rect.x/=2;
        this.rect.y/=2;
    }
}
