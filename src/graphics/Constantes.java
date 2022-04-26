package graphics;

import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;

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

    public final static int DELTA_REFRESH = 25;

    public final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    public final static String TEXTE_POLICE = "TimesRoman";

    public final static int TEXTE_STYLE = Font.PLAIN;

    public final static int TEXTE_TAILLE = 20;

    public final static Color TEXT_COLOR = Color.BLACK;

    public final static Color COLOR_INVISIBLE = new Color(0, 0, 0, 1);

    public final static Color DEFAULT_COLOR_ADD_RECTANGLE = Color.BLACK;

    public static final String ROTATION_ATTRIBUTES = "RotationAttributes";

    public final static RotationAttributes DEFAULT_ROTATION_ATTRIBUTES = new RotationAttributes(0.);

    public final static Cursor SE_RESIZE_CURSOR = new Cursor(Cursor.SE_RESIZE_CURSOR);

    public final static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    public final static ColorAttributes SELECTION_COLOR = new ColorAttributes(false, true, Color.BLACK, Color.BLACK);

    public final static int DEFAULT_DEPLACEMENT_HORIZONTAL = 300;

    public final static int DEFAULT_DEPLACEMENT_VERTICAL = 300;

    public final static int GAME_ID_MORT_PION = 0;

    public final static ColorAttributes PLATEAU_MORT_PION_COLOR = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    public final static ColorAttributes PLATEAU_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    public final static ColorAttributes J1_MORT_PION_COLOR = new ColorAttributes(true, true, Color.BLUE, Color.BLUE);

    public final static ColorAttributes J1_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.BLUE, Color.BLUE);

    public final static ColorAttributes J2_MORT_PION_COLOR = new ColorAttributes(true, true, Color.RED, Color.RED);

    public final static ColorAttributes J2_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.RED, Color.RED);

    public final static ColorAttributes MORT_PION_NULL_COLOR = new ColorAttributes(false, false, Color.WHITE, Color.WHITE);

    public final static SelectionAttributes MORT_PION_NULL_SELECTION = new SelectionAttributes();

    public final static int GAME_ID_DAMES = 1;
}
