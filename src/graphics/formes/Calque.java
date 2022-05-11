package graphics.formes;

import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Définition de ce qu'est un calque.
 */
public class Calque implements Serializable {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -555108164984005640L;

    /**
     * Nombre de calques créé depuis le dernier import ou la dernière réinitialisation.
     */
    public static int nb_Calque=0;

    /**
     * Contenu du calque.
     */
    private SCollection content;

    /**
     * Nom du calque.
     */
    private String name;

    /**
     * Est-ce que le calque est dessiné ?
     */
    private boolean isPaint;

    /**
     * Est-ce que le calque est actuellement le calque en cours d'usage ?
     */
    private boolean isUsed;

    /**
     * Constructeur.
     */
    public Calque(){
        this.content = new SCollection();
        this.content.addAttributes(new SelectionAttributes());
        this.content.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
        this.name = "Calque "+nb_Calque;
        this.isPaint = true;
        this.isUsed = false;
        Calque.nb_Calque++;
    }

    /**
     * Dit au calque qu'il est le calque en cours d'utilisation.
     */
    public void use(){
        this.isUsed = true;
    }

    /**
     * Dit au calque qu'il n'est pas le calque en cours d'utilisation.
     */
    public void notUse(){
        this.isUsed = false;
    }

    /**
     * Change l'affichage du calque.
     * @param isPaint Est-ce que le calque est affiché ?
     */
    public void setPaint(boolean isPaint){
        this.isPaint = isPaint;
    }

    /**
     * Est-ce que le calque est affiché ?
     * @return Est-ce que le calque est affiché ?
     */
    public boolean isPaint(){
        return this.isPaint;
    }

    /**
     * Est-ce que le calque est utilisé actuellement ?
     * @return Est-ce que le calque est actuellement ?
     */
    public boolean isUsed(){
        return this.isUsed;
    }

    /**
     * Donne le nom du calque.
     * @return Donne le nom du calque.
     */
    public String getName() {
        return name;
    }

    /**
     * Change le nom du calque.
     * @param name Nom souhaité pour le calque.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère la collection de formes contenue dans le calque.
     * @return Collection de formes contenue dans le calque.
     */
    public SCollection getContent() {
        return content;
    }

    /**
     * Change la totalité du contenu du calque.
     *
     * @param content Collection qui va devenir le nouveau contenu.
     */
    public void setContent(SCollection content) {
        this.content = content;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un calque.
     */
    public void accept(ShapeVisitor visitor) {
        this.content.accept(visitor);
    }

    /**
     * Ajoute une forme au contenu du calque.
     *
     * @param s Forme à ajouter au calque.
     */
    public void add(Shape s){
        this.content.add(s);
    }

    /**
     * Multiplie par 2 la taille de toutes les formes du calque.
     */
    public void zoomIn(){
        for (Shape shape : this.getContent().getElement()) {
            shape.zoomIn();
        }
    }

    /**
     * Divise par 2 la taille de toutes les formes du calque.
     */
    public void zoomOut(){
        for (Shape shape : this.getContent().getElement()) {
            shape.zoomOut();
        }
    }

    /**
     * Force la translation de toutes les formes du calque.
     * @param dx Valeur du déplacement en x.
     * @param dy Valeur du déplacement en y.
     */
    public void force_translate(int dx, int dy) {
        this.content.force_translate(dx, dy);
    }

    /**
     * Dit que ce calque n'est pas un calque de jeu.
     *
     * @return Faux, car ce calque n'est pas un calque lié à un jeu.
     */
    public boolean isGame(){
        return false;
    }
}
