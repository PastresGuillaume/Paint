package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

/**
 * Définition des ellipses.
 */
public class SEllipsis extends Shape{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -5864575391362709945L;

    /**
     * Rectangle dans lequel s'inscrit l'ellipse.
     */
    private Rectangle rect;

    /**
     * Constructeur.
     *
     * @param point Coin supérieur gauche du rectangle dans lequel s'inscrit l'ellipse.
     * @param r le rayon de l'ellipse.
     */
    public SEllipsis(Point point, int r){
        this.rect = new Rectangle(point.x, point.y ,r*2, r*2);
    }

    /**
     * Calcule le coin superieur gauche du rectangle dans lequel s'inscrit l'ellipse.
     *
     * @return Le coin superieur gauche du rectangle dans lequel s'inscrit l'ellipse.
     */
    @Override
    public Point getLoc() {
        return this.rect.getLocation();
    }

    /**
     * Translate l'ellipse pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche de l'ellipse.
     */
    @Override
    public void setLoc(Point point) {
        this.rect.setLocation(point);
    }

    /**
     * Translate l'ellipse.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.rect.translate(dx, dy);
    }

    /**
     * Retourne le rectangle dans lequel s'inscrit l'ellipse.
     *
     * @return Le rectangle dans lequel s'inscrit l'ellipse.
     */
    @Override
    public Rectangle getBounds() {
        return this.rect;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner une ellipse.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitEllipsis(this);
    }

    /**
     * Retourne le rectangle dans lequel s'inscrit l'ellipse.
     *
     * @return Le rectangle dans lequel s'inscrit l'ellipse.
     */
    public Rectangle getRect(){
        return this.rect;
    }

    /**
     * Redimensionne l'ellipse.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    public void resize(int width, int height){
        this.rect.width = width;
        this.rect.height = height;
    }

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
