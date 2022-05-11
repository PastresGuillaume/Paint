package graphics.attributes;

import graphics.Constantes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.Serial;

/**
 * Définition des attributs de Font.
 */
public class FontAttributes extends Attributes{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 6174642913178746392L;

    /**
     * Définit le style, le font et la taille de la police d'écriture.
     */
    public Font font;

    /**
     * Définit la couleur du texte.
     */
    public Color fontColor;

    /**
     * Constructeur.
     */
    public FontAttributes(){
        this.font = new Font(Constantes.TEXTE_POLICE, Constantes.TEXTE_STYLE, Constantes.TEXTE_TAILLE);
        this.fontColor = Constantes.TEXT_COLOR;
    }

    /**
     * Transforme une instance en instance de String.
     *
     * @return Un String représentant l'instance de ColorAttributes considéré.
     */
    @Override
    public String getId() {
        return Constantes.FONT_ATTRIBUTE;
    }

    /**
     * Calcule le rectangle de taille minimal qui contient le texte.
     *
     * @param s String affiché à l'écran.
     * @return Le rectangle de taille minimale qui contient le texte.
     */
    public Rectangle getBounds(String s){
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        int width = (int)(font.getStringBounds(s, frc).getWidth());
        int height = (int)(font.getStringBounds(s, frc).getHeight());
        return new Rectangle(-2,-height+(int)(this.font.getSize()*0.25),width+4,height);
    }
}
