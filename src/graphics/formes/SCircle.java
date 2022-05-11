package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

/**
 * Définition de ce qu'est un Cercle.
 */
public class SCircle extends Shape {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 6315456169132488899L;

    /**
     * Rayon du cercle.
     */
    private double radius;

    /**
     * Coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     */
    private Point loc;

    /**
     * Constructeur.
     *
     * @param point Coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     * @param r Rayon du cercle.
     */
    public SCircle(Point point, int r){
        this.radius = r;
        this.loc = new Point(point.x, point.y);
    }

    /**
     * Retourne les coordonnées du coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     *
     * @return Les coordonnées du coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     */
    @Override
    public Point getLoc() {
        return this.loc;
    }

    /**
     * Change les coordonnées du coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     *
     * @param point Nouvelles coordonnées du coin supérieur gauche du rectangle dans lequel s'inscrit le cercle.
     */
    @Override
    public void setLoc(Point point) {
        this.loc.setLocation(point);
    }

    /**
     * Translate le cercle.
     * @param dx Delta de translation selon l'axe x.
     * @param dy Delta de translation selon l'axe y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.loc.translate(dx, dy);
    }

    /**
     * Donne le rectangle dans lequel le cercle s'inscrit lorsqu'il n'y a aucune rotation.
     *
     * @return Le rectangle de taille minimale dans lequel le cercle s'inscrit lorsqu'il n'y a aucune rotation.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.loc.x, this.loc.y, (int)(2*this.radius), (int)(2*this.radius));
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un cercle.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitEllipsis(this);
    }

    /**
     * Redimensionne le cercle.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * Donne le rayon du cercle.
     *
     * @return Le rayon du cercle.
     */
    public int getRadius(){
        return (int)this.radius;
    }

    /**
     * Change le rayon du cercle.
     *
     * @param r Nouveau rayon.
     */
    public void setRadius(int r){
        this.radius = r;
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        this.loc.translate(this.loc.x, this.loc.y);
        this.radius*=2;
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        this.loc.x = this.loc.x/2;
        this.loc.y = this.loc.y/2;
        this.radius/=2;
    }
}
