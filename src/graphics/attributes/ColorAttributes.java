package graphics.attributes;

import graphics.Constantes;

import java.awt.*;
import java.io.Serial;

/**
 * Définition des attributs de couleurs.
 */
public class ColorAttributes extends Attributes{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 3150342127768320072L;

    /**
     * Constructeur
     *
     * @param filled L'intérieur est dessiné ?
     * @param stroked Le contour est dessiné ?
     * @param filledColor La couleur de l'intérieur.
     * @param strokedColor La couleur de l'extérieur.
     */
    public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor){
        this.filled = filled;
        this.stroked = stroked;
        this.filledColor = filledColor;
        this.strokedColor = strokedColor;
    }

    /**
     * Transforme une instance en instance de String.
     *
     * @return Un String représentant l'instance de ColorAttributes considéré.
     */
    @Override
    public String toString() {
        return "ColorAttributes{" +
                "filled=" + filled +
                ", stroked=" + stroked +
                ", filledColor=" + filledColor +
                ", strokedColor=" + strokedColor +
                '}';
    }

    /**
     * L'intérieur est dessiné ?
     */
    public boolean filled;

    /**
     * Le contour est dessiné ?
     */
    public boolean stroked;

    /**
     * La couleur de l'intérieur.
     */
    public Color filledColor;

    /**
     * La couleur de l'extérieur.
     */
    public Color strokedColor;

    /**
     * Donne l'identifiant de la classe ColorAttributes.
     *
     * @return L'identifiant de la classe ColorAttributes.
     */
    @Override
    public String getId() {
        return Constantes.COLOR_ATTRIBUTE;
    }

}
