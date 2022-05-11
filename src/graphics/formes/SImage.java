package graphics.formes;

import graphics.Constantes;
import graphics.ui.Visitor.ShapeVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Définition de la classe relative aux images.
 */
public class SImage extends Shape{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -5222809445146307472L;

    /**
     * Rectangle dans lequel s'inscrit l'image.
     */
    private Rectangle rect;

    /**
     * L'image à proprement parler.
     */
    private BufferedImage image;

    /**
     * Constructeur.
     *
     * @param point Coin supérieur gauche de l'image.
     * @param width Largeur de l'image.
     * @param height Hauteur de l'image.
     * @param file Fichier contenant l'image.
     */
    public SImage(Point point, int width, int height, File file){
        try {
            this.image = ImageIO.read(file);
            this.rect = new Rectangle(point.x, point.y, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur.
     * @param point Coin supérieur gauche de l'image.
     * @param file Fichier contenant l'image.
     */
    public SImage(Point point, File file){
        try {
            this.image = ImageIO.read(file);
            int width = this.image.getWidth();
            int height = this.image.getHeight();
            if (width> Constantes.MAXIMUM_IMAGE_SIZE && width>height){
                height = height*Constantes.MAXIMUM_IMAGE_SIZE/width;
                width = Constantes.MAXIMUM_IMAGE_SIZE;
            }
            else if(height>Constantes.MAXIMUM_IMAGE_SIZE && height>width){
                width = width*Constantes.MAXIMUM_IMAGE_SIZE/height;
                height = Constantes.MAXIMUM_IMAGE_SIZE;
            }
            this.rect = new Rectangle(point.x, point.y, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur.
     *
     * @param point Coin supérieur gauche de l'image.
     * @param width Largeur de l'image.
     * @param height Hauteur de l'image.
     * @param path Chemin menant vers l'image.
     */
    public SImage(Point point, int width, int height, String path){
        this(point, width, height,new File("./images/"+path));
    }

    /**
     * Constructeur.
     * @param point Coin supérieur gauche de l'image.
     * @param path Chemin menant vers l'image.
     */
    public SImage(Point point, String path){
        this(point, new File("./images/"+path));
    }

    /**
     * Retourne le coin supérieur gauche du plus petit rectangle dans lequel s'inscrit l'image.
     *
     * @return Le coin supérieur gauche du plus petit rectangle dans lequel s'inscrit l'image.
     */
    @Override
    public Point getLoc() {
        return this.rect.getLocation();
    }

    /**
     * Translate l'image pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche de l'image.
     */
    @Override
    public void setLoc(Point point) {
        this.rect.setLocation(point);
    }

    /**
     * Translate l'image.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.rect.translate(dx, dy);
    }

    /**
     * Retourne le rectangle dans lequel l'image s'inscrit.
     *
     * @return Le rectangle dans lequel l'image s'inscrit.
     */
    @Override
    public Rectangle getBounds() {
        return this.rect.getBounds();
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner une image.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitImage(this);
    }

    /**
     * Redimensionne l'image.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    @Override
    public void resize(int width, int height) {
        this.rect.width = width;
        this.rect.height = height;
    }

    /**
     * Retourne l'image.
     *
     * @return L'image.
     */
    public BufferedImage getImage(){
        return this.image;
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

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeObject(this.rect);
        ImageIO.write(this.image, "png", oos);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        this.rect = (Rectangle)ois.readObject();
        this.image = ImageIO.read(ois);
    }

}
