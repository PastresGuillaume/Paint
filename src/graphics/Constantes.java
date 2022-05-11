package graphics;

import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;

import java.awt.*;

/**
 * Définition de toutes les constantes paramétrable dans l'application.
 */
public class Constantes {
    /**
     * Nom de l'attribut Font.
     */
    public final static String FONT_ATTRIBUTE = "FontAttributes";

    /**
     * Nom de l'attribut de selection.
     */
    public final static String SELECTION_ATTRIBUTE = "SelectionAttributes";

    /**
     * Nom de l'attribut de couleur.
     */
    public final static String COLOR_ATTRIBUTE = "ColorAttributes";

    /**
     * Nom de l'attribut de rotation.
     */
    public static final String ROTATION_ATTRIBUTES = "RotationAttributes";

    /**
     * Attribut de couleur par défaut.
     */
    public final static ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    /**
     * Attribut de sélection par défaut.
     */
    public final static SelectionAttributes DEFAULT_SELECTION_ATTRIBUTES = new SelectionAttributes();

    /**
     * Attribut de Font par défaut.
     */
    public final static FontAttributes DEFAULT_FONT_ATTRIBUTES = new FontAttributes();

    /**
     * Taille des poignées sur les objets sélectionnées.
     */
    public final static int SIZE_SHAPE_SELECTED = 10;

    /**
     * Police par défaut.
     */
    public final static String TEXTE_POLICE = "TimesRoman";

    /**
     * Style d'écriture par défaut.
     */
    public final static int TEXTE_STYLE = Font.PLAIN;

    /**
     * Taille d'écriture par défaut.
     */
    public final static int TEXTE_TAILLE = 20;

    /**
     * Couleur du texte par défaut.
     */
    public final static Color TEXT_COLOR = Color.BLACK;

    /**
     * Couleur transparente.
     */
    public final static Color COLOR_INVISIBLE = new Color(0, 0, 0, 1);

    /**
     * Couleur par défaut de contour des formes créées.
     */
    public final static Color DEFAULT_COLOR_ADD = Color.BLACK;

    /**
     * Rotation par défaut des formes.
     */
    public final static RotationAttributes DEFAULT_ROTATION_ATTRIBUTES = new RotationAttributes(0.);

    /**
     * Curseur pour la redimension.
     */
    public final static Cursor SE_RESIZE_CURSOR = new Cursor(Cursor.SE_RESIZE_CURSOR);

    /**
     * Curseur par défaut.
     */
    public final static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    /**
     * Couleur des poignées sur les objets sélectionnées.
     */
    public final static ColorAttributes SELECTION_COLOR = new ColorAttributes(false, true, Color.BLACK, Color.BLACK);

    /**
     * Delta de déplacement selon l'axe x.
     */
    public final static int DEFAULT_DEPLACEMENT_HORIZONTAL = 300;

    /**
     * Delta de déplacement selon l'axe y.
     */
    public final static int DEFAULT_DEPLACEMENT_VERTICAL = 300;

    /**
     * Identifiant du mort pion.
     */
    public final static int GAME_ID_MORT_PION = 0;

    /**
     * Couleur des cases noires du mort pion.
     */
    public final static ColorAttributes PLATEAU_MORT_PION_COLOR = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    /**
     * Attribut de selection des cases du mort pion.
     */
    public final static ColorAttributes PLATEAU_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.BLACK, Color.BLACK);

    /**
     * Couleur du joueur 1.
     */
    public final static ColorAttributes J1_MORT_PION_COLOR = new ColorAttributes(true, true, Color.BLUE, Color.BLUE);

    /**
     * Attribut de selection des pions du joueur 1.
     */
    public final static ColorAttributes J1_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.BLUE, Color.BLUE);

    /**
     * Couleur du joueur 2.
     */
    public final static ColorAttributes J2_MORT_PION_COLOR = new ColorAttributes(true, true, Color.RED, Color.RED);

    /**
     * Attribut de selection des pions du joueur 2.
     */
    public final static ColorAttributes J2_MORT_PION_SELECTION = new ColorAttributes(true, true, Color.RED, Color.RED);

    /**
     * Couleur du texte d'une partie nulle de mort pion.
     */
    public final static ColorAttributes MORT_PION_NULL_COLOR = new ColorAttributes(false, false, Color.WHITE, Color.WHITE);

    /**
     * Attribut de sélection du texte d'une partie nulle de mort pion.
     */
    public final static SelectionAttributes MORT_PION_NULL_SELECTION = new SelectionAttributes();

    /**
     * Couleur de fond en mode dark.
     */
    public final static Color DARKMODE_BACKGROUND_COLOR = new Color(80, 80, 80);

    /**
     * Couleur de fond du menuBar en mode dark.
     */
    public final static Color DARKMODE_MENUBAR_COLOR = new Color(60, 60, 60);

    /**
     * Couleur de fond du textMenu en mode dark.
     */
    public final static Color DARKMODE_TEXTMENU_COLOR = new Color(200, 200, 200);

    /**
     * Couleur de fond en mode clair.
     */
    public final static Color BRIGHTMODE_BACKGROUND_COLOR = new Color(220, 220, 220);

    /**
     * Couleur de fond du menuBar en mode clair.
     */
    public final static Color BRIGHTMODE_MENUBAR_COLOR = new Color(200, 200, 200);

    /**
     * Couleur de fond du textMenu en mode clair.
     */
    public final static Color BRIGHTMODE_TEXTMENU_COLOR = new Color(0, 0, 0);

    public static Color BACKGROUND_COLOR;

    public static Color MENUBAR_COLOR;

    public static Color TEXTMENU_COLOR;

    public final static String FILE_EXTENSION = ".ser";

    public final static String MENU_BAR_ID = "MenuBar";

    public final static String CALQUE_TOOL_BAR_ID = "CalqueToolBar";

    public final static String SHAPE_TOOL_BAR_ID = "ShapeToolBar";

    public final static String SHAPE_TOOL_BAR_LOC = BorderLayout.PAGE_END;

    public final static String CALQUE_TOOL_BAR_LOC = BorderLayout.EAST;

    public final static String IS_PAINTED_CALQUE = "isPainted";

    public final static String IS_USED_CALQUE = "isUsed";

    public final static int WIDTH_ICON = 50;

    public final static int HEIGHT_ICON = 50;

    public static final Dimension WINDOW_DIMENSIONS = new Dimension(300, 300);

    public static final int MAXIMUM_IMAGE_SIZE = 500;

    /**
     * Séparateur de chemin selon le système d'exploitation.
     */
    public static final String PATH_SEPARATOR = (System.getProperty("os.name").toLowerCase().contains("mac"))?"/":"\\";

    /**
     * Chemin vers le dossier contenant les images.
     */
    public static final String PATH_IMAGES = "images"+PATH_SEPARATOR;

    /**
     * Chemin vers le dossier contenant les icons.
     */
    public static final String PATH_ICONS = "images"+PATH_SEPARATOR+"icons"+PATH_SEPARATOR;

    /**
     * Chemin vers le dossier contenant les images du jeu NPNG.
     */
    public static final String PATH_NO_PAINT_NO_GAME = PATH_IMAGES+PATH_SEPARATOR+"NoPaintNoGame"+PATH_SEPARATOR;

    /**
     * Chemin vers le dossier contenant les sons.
     */
    public static final String PATH_SON = "son"+PATH_SEPARATOR;
}
