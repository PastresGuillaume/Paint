package graphics.attributes;

import graphics.Constantes;

import java.awt.*;
import java.io.Serial;

public class ColorAttributes extends Attributes{
    @Serial
    private static final long serialVersionUID = 3150342127768320072L;

    public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor){
        this.filled = filled;
        this.stroked = stroked;
        this.filledColor = filledColor;
        this.strokedColor = strokedColor;
    }

    @Override
    public String toString() {
        return "ColorAttributes{" +
                "filled=" + filled +
                ", stroked=" + stroked +
                ", filledColor=" + filledColor +
                ", strokedColor=" + strokedColor +
                '}';
    }

    public boolean filled;
    public boolean stroked;
    public Color filledColor;
    public Color strokedColor;


    @Override
    public String getId() {
        return Constantes.COLOR_ATTRIBUTE;
    }

}
