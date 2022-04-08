package graphics.attributes;

import graphics.Constantes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontAttributes extends Attributes{
    public Font font;
    public Color fontColor;

    public FontAttributes(){
        this.font = new Font(Constantes.TEXTE_POLICE, Constantes.TEXTE_STYLE, Constantes.TEXTE_TAILLE);
        this.fontColor = Constantes.TEXT_COLOR;
    }


    @Override
    public String getId() {
        return Constantes.FONT_ATTRIBUTE;
    }

    public Rectangle getBounds(String s){
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        int width = (int)(font.getStringBounds(s, frc).getWidth());
        int height = (int)(font.getStringBounds(s, frc).getHeight());
        return new Rectangle(-2,-height+(int)(this.font.getSize()*0.25),width+4,height);
//        ca marche donc touche pas stp
    }
}
