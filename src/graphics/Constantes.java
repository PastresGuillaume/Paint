package graphics;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Constantes {
    public final static String FONT_ATTRIBUTE = "FontAttributes";

    public final static String SELECTION_ATTRIBUTE = "SelectionAttributes";

    public final static String COLOR_ATTRIBUTE = "ColorAttributes";

    public final static ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    public final static SelectionAttributes DEFAULT_SELECTION_ATTRIBUTES = new SelectionAttributes();

    public final static FontAttributes DEFAULT_FONT_ATTRIBUTES = new FontAttributes();

    public final static int SIZE_SHAPE_SELECTED = 10;

    public final static ColorAttributes COLOR_SHAPE_SELECTED = new ColorAttributes(false, true, Color.BLACK, Color.BLACK);

    public final static int DELTA_REFRESH = 50;

    public final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    //Salut c'est moi
}
