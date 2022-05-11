package graphics.formes;

import graphics.Constantes;
import graphics.attributes.Attributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Définition de la classe abstraite des formes.
 */
public abstract class Shape implements Serializable {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 1783389611714034615L;

    /**
     * Liste des attributs relatifs à la forme.
     */
    private ArrayList<Attributes> attributes;

    /**
     * Constructeur.
     */
    Shape(){
        this.attributes = new ArrayList<>();
    }

    /**
     * Retourne le coin supérieur gauche du plus petit rectangle dans lequel s'inscrit la forme.
     *
     * @return Le coin supérieur gauche du plus petit rectangle dans lequel s'inscrit la forme.
     */
    public abstract Point getLoc();

    /**
     * Translate la forme pour que son coin en haut à gauche soit aux coordonnées demandées.
     *
     * @param point Le nouveau coin en haut à gauche de la forme.
     */
    public abstract void setLoc(Point point);

    /**
     * Translate la forme.
     *
     * @param dx Delta de translation selon x.
     * @param dy Delta de translation selon y.
     */
    public abstract void translate(int dx, int dy);

    /**
     * Retourne le rectangle dans lequel s'inscrit la forme.
     *
     * @return Le rectangle dans lequel s'inscrit la forme.
     */
    public abstract Rectangle getBounds();

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner la forme en question.
     */
    public abstract void accept(ShapeVisitor visitor);

    /**
     * Redimensionne la forme.
     *
     * @param width Nouvelle largeur.
     * @param height Nouvelle hauteur.
     */
    public abstract void resize(int width, int height);

    /**
     * Ajoute un attribut à la forme.
     *
     * @param attributes Nouvel attribut à ajouter.
     */
    public void addAttributes(Attributes attributes){
        this.attributes.add(attributes);
    }

    /**
     * Retourne l'attribut lié au nom de l'attribut.
     *
     * @param attributeName Nom de l'attribut.
     * @return Attribut portant le nom souhaité s'il existe.
     */
    public Attributes getAttributes(String attributeName){
        for (Attributes attributes : this.attributes){
            if(attributeName.equals(attributes.getId())){
                return attributes;
            }
        }
        return null;
    }

    /**
     * Désélectionne la forme.
     */
    public void unselect() {
        try{
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
        catch (NullPointerException npe){
            this.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).unselect();
        }
    }

    /**
     * Sélectionne la forme.
     */
    public void select() {
        try{
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
        }
        catch (NullPointerException npe){
            this.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
        }
    }

    /**
     * Test si la forme est sélectionnée.
     * @return Un booléen représentant l'état de selection de la forme.
     */
    public boolean isSelected() {
        try{
            return ((SelectionAttributes) this.getAttributes(Constantes.SELECTION_ATTRIBUTE)).isSelected();
        }
        catch (NullPointerException npe){
            return false;
        }
    }

    /**
     * Fais un zoom *2 sur le point de coordonnées (0, 0)
     */
    public abstract void zoomIn();

    /**
     * Fais un zoom /2 sur le point de coordonnées (0, 0)
     */
    public abstract void zoomOut();

    /**
     * Force la translation de la forme.
     *
     * @param dx Delta translation selon x.
     * @param dy Delta translation selon y.
     */
    protected void force_translate(int dx, int dy) {
        this.translate(dx, dy);
    }
}
