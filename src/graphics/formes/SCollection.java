package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Définition de ce qu'est une collection de forme.
 */
public class SCollection extends Shape{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -1732110102692478729L;

    /**
     * Contenu de la collection.
     */
    private ArrayList<Shape> shapes;

    /**
     * Constructeur.
     */
    public SCollection(){
        this.shapes = new ArrayList<>();
    }

    /**
     * Donne le coin en haut à gauche du plus petit rectangle dans lequel s'inscrit toutes les formes de la collection.
     *
     * @return Le coin en haut à gauche du plus petit rectangle dans lequel s'inscrit toutes les formes de la collection.
     */
    @Override
    public Point getLoc() {
        Iterator<Shape> shapes = this.iterator();
        if (!shapes.hasNext()) return null;

        Point point = shapes.next().getLoc();
        while(shapes.hasNext()) {
            Point point2 = shapes.next().getLoc();
            if (point.x > point2.x){
                point.x = point2.x;
            }
            if (point.y > point2.y){
                point.y = point2.y;
            }
        }
        return point;
    }

    /**
     * Translate la collection pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche de la collection.
     */
    @Override
    public void setLoc(Point point) {
        Point actual = this.getLoc();
        this.translate(point.x-actual.x, point.y-actual.y);
    }

    /**
     * Translate toutes les formes de la collection.
     *
     *  @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        for (Shape s: this.shapes){
            s.translate(dx, dy);
        }
    }

    /**
     * Calcul le plus petit rectangle dans lequel s'inscrit toutes les formes de la collection.
     *
     * @return Le plus petit rectangle dans lequel s'inscrit toutes les formes de la collection.
     */
    @Override
    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle();
        if (this.shapes.size() != 0){
            bounds = shapes.get(0).getBounds();
            for (Shape shape : this.shapes) {
                bounds = bounds.union(shape.getBounds());
            }
        }
        return bounds;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner une collection.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCollection(this);
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
     * Renvoie un itérateur sur les formes de la collection.
     *
     * @return Un itérateur sur les formes de la collection.
     */
    public Iterator<Shape> iterator(){
        return this.shapes.iterator();
    }

    /**
     * Ajoute une forme à la collection.
     *
     * @param shape Forme à ajouter.
     */
    public void add(Shape shape){this.shapes.add(shape); }

    /**
     * Retourne la liste des formes de la collection.
     *
     * @return La liste des formes de la collection.
     */
    public ArrayList<Shape> getElement() {
        return this.shapes;
    }

    /**
     * Désélectionne toutes les formes de la collection et la collection.
     */
    @Override
    public void unselect(){
        super.unselect();
        for (Shape s: this.shapes){
            s.unselect();
        }
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        for (Shape shape : this.getElement()) {
            shape.zoomIn();
        }
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        for (Shape shape : this.getElement()) {
            shape.zoomOut();
        }
    }

    /**
     * Force la translation de la collection de dx, dy.
     *
     * @param dx Delta translation selon x.
     * @param dy Delta translation selon y.
     */
    @Override
    public void force_translate(int dx, int dy) {
        for (Shape shape : this.getElement()) {
            shape.force_translate(dx, dy);
        }
    }
}
