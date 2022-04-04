package graphics.shapes.attributes;

import java.awt.*;

public class ColorAttributes extends Attributes{
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
        return "ColorAttributes";
    }

}
