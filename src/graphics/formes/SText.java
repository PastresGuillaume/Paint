package graphics.formes;

import graphics.Constantes;
import graphics.attributes.FontAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;

/**
 * Définition d'un texte.
 */
public class SText extends Shape{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 3460010181915715737L;

    /**
     * Texte en question.
     */
    private String text;

    /**
     * Coin supérieur gauche du texte.
     */
    private Point loc;

    /**
     * Constructeur.
     *
     * @param point Coin supérieur gauche du texte.
     * @param text Texte en question.
     */
    public SText(Point point, String text){
        this.text = text;
        this.loc = new Point(point);
    }

    /**
     * Retourne le coin supérieur gauche du texte.
     *
     * @return Le coin supérieur gauche du texte.
     */
    @Override
    public Point getLoc() {
        return this.loc;
    }

    /**
     * Translate le texte pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche du texte.
     */
    @Override
    public void setLoc(Point point) {
        this.loc.setLocation(point);
    }

    /**
     * Translate le texte.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    @Override
    public void translate(int dx, int dy) {
        this.loc.translate(dx, dy);
    }

    /**
     * Retourne le rectangle dans lequel s'inscrit le texte.
     *
     * @return Le rectangle dans lequel s'inscrit le texte.
     */
    @Override
    public Rectangle getBounds() {
        FontAttributes fontAttributes = (FontAttributes) this.getAttributes(Constantes.FONT_ATTRIBUTE);
        if (fontAttributes==null){
            fontAttributes = Constantes.DEFAULT_FONT_ATTRIBUTES;
        }
        Rectangle rect = fontAttributes.getBounds(this.text);
        rect.translate(this.loc.x,this.loc.y);
        return rect;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un texte.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitText(this);
    }

    /**
     * Redimensionne le texte.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    @Override
    public void resize(int width, int height) {
        //Fixme resize Text when given negative height/width
        FontAttributes fontAttributes = (FontAttributes) this.getAttributes(Constantes.FONT_ATTRIBUTE);
        if (fontAttributes==null){
            fontAttributes = Constantes.DEFAULT_FONT_ATTRIBUTES;
        }
        int newFontSize = fontAttributes.font.getSize()*width/this.getBounds().width;
        fontAttributes.font = fontAttributes.font.deriveFont(fontAttributes.font.getStyle(), newFontSize);
    }

    /**
     * Retourne le texte affiché.
     *
     * @return Le texte affiché.
     */
    public String getText(){
        return this.text;
    }

    /**
     * Modifie le texte.
     *
     * @param t Nouveau texte.
     */
    public void setText(String t){
        this.text = t;
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomIn() {
        this.loc.x*=2;
        this.loc.y*=2;
    }

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    @Override
    public void zoomOut() {
        this.loc.x/=2;
        this.loc.y/=2;
    }
}
