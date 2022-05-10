package graphics.jeux.NoPaintNoGame.Objet;

import graphics.Constantes;

import javax.swing.*;

/**
 * Bloc est la classe qui définit un bloc de javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * Il s'agit d'une classe qui hérite de la classe abstraite Decor. Elle a donc :
 *  <ul>
 * <li>Une largeur pour son image</li>
 * <li>Une hauteur pour son image</li>
 * <li>Une abscisse x </li>
 * <li>Une ordonnée y </li>
 * <li> Une image imgDecor</li>
 * <li> Une icoimage icoDecor</li>
 *  </ul>
 *
 */



public class Bloc extends Decor{



        /**
         * <b>Constructeur de Bloc</b>
         *
         * Il utilise entre autre le constructeur de la classe abstraite Decor.
         *
         * @param largeur
         *     Largeur de son image
         * @param hauteur
         *     Hauteur de son image
         * @param x
         *      Abscisse de son image
         * @param y
         *      Ordonnée de son image
         */


    //Construceur
    ///////////////////////////////////////

    public Bloc(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y,false);
        super.icoDecor = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"bloc.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }

}
