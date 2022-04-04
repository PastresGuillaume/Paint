package graphics.shapes.attributes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontAttributes extends Attributes{
    public Font font;
    public Color fontColor;

    @Override
    public String getId() {
        return "FontAttributes";
    }

    public Rectangle getBounds(String s){
        //TODO demander Thomas font obligatoire ?
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        int width = (int)(font.getStringBounds(s, frc).getWidth());
        int height = (int)(font.getStringBounds(s, frc).getHeight());
        return new Rectangle(-2,-height+(int)(this.font.getSize()*0.25),width+4,height);
    }
}
