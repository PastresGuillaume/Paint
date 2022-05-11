package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

public class SPolygone extends Shape {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -88192407312194124L;

    /**
     * Polygone en question.
     */
    private Polygon poly;

    /**
     * Constructeur.
     *
     * @param xpoints Vecteur des coordonnées x de tous les points du polygone.
     * @param ypoints Vecteur des coordonnées y de tous les points du polygone.
     * @param npoints Nombre de point du polygone.
     */
    public SPolygone(int[] xpoints, int[] ypoints, int npoints){this.poly = new Polygon(xpoints,ypoints,npoints);}

    /**
     * Ajoute un point au polygone.
     *
     * @param x Coordonnée x du nouveau point.
     * @param y Coordonnée y du nouveau point.
     */
    public void SPolygoneAjoutPoint(int x, int y){
        this.poly.addPoint(x,y);
        this.poly.npoints+=1;
    }

    /**
     * Retourne le premier point constituant le polygone.
     *
     * @return Le premier point constituant le polygone.
     */
    @Override
    public Point getLoc() {
        Point point = new Point(this.poly.xpoints[0] , this.poly.ypoints[0]);
        return (point);
    }

    /**
     * Renvoie le polygone.
     *
     * @return Le polygone.
     */
    public Polygon getPoly() {
        return poly;
    }

    /**
     * Translate le polygone pour que son premier point soit aux coordonnées demandées.
     *
     * @param point Les nouvelles coordonnées du premier point.
     */
    @Override
    public void setLoc(Point point) {
        this.poly.translate(point.x-this.poly.xpoints[0], point.y-this.poly.ypoints[0]);
    }

    /**
     * Renvoie la liste des abscisses.
     *
     * @return La liste des abscisses.
     */
    public int[] GetxPoints(){
        return(this.poly.xpoints);
    }

    /**
     * Renvoie la liste des ordonnées.
     *
     * @return La liste des ordonnées.
     */
    public int[] GetyPoints(){
        return(this.poly.ypoints);
    }

    /**
     * Retourne le nombre de point du polygone.
     *
     * @return Le nombre de point du polygone.
     */
    public int GetnPoints(){
        return(this.poly.npoints);
    }

    /**
     * Translate le polygone.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.poly.translate(dx, dy);
    }

    /**
     * Retourne le rectangle dans lequel s'inscrit le polygone.
     *
     * @return Le rectangle dans lequel s'inscrit le polygone.
     */
    @Override
    public Rectangle getBounds() {
        return this.poly.getBounds();
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un polygone.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitPolygon(this);
    }

    /**
     * La redimension n'est pas implémenté pour les polygones.
     *
     * @param width Type : int
     * @param height Type : int
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        for (int length = 0; length<this.poly.npoints; length++) {
            this.poly.xpoints[length] *= 2;
            this.poly.ypoints[length] *= 2;
        }
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        for (int length = 0; length<this.poly.npoints; length++) {
            this.poly.xpoints[length] /= 2;
            this.poly.ypoints[length] /= 2;
        }
    }
}
