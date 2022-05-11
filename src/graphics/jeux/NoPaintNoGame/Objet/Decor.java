package graphics.jeux.NoPaintNoGame.Objet;

import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;

import javax.swing.*;
import java.awt.*;

/**
 * Decor est la classe abstaite qui définit un decor de javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>Une largeur pour son image</li>
 * <li>Une hauteur pour son image</li>
 * <li>Une abscisse x pour son image</li>
 * <li>Une ordonnée y pour son image</li>
 * <li> Une image imgDecor</li>
 * <li> Une icoimage icoDecor</li>
 * <li> Un boolean piece </li>
 * </ul>
 * Cette classe abstraite definit un decor qui peut etre une piece , un bloc ou un tuyauRouge, qui sera dessiné dans la scène.
 * De plus , grace à la méthode contact, Le personnage pourra détecter ces éléments de décors et ne pas renter dedans lorsqu'il se déplace.
 *
 */


public abstract class Decor {
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //Création des donnees membres//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /**
    * largeur de l'image imgDecor
    */
   private int largeur;

   /**
    * Hauteur de l'image imgDecor
    */
   private int hauteur;

   /**
    * Abscisse x pour son image
    */
   private int x;

   /**
    * Ordonnée y pour son image
    */
   private int y;

   /**
    * Description de l'attribut 1
    */
   protected Image imgDecor  ;

   /**
    * Description de l'attribut 1
    */
   protected ImageIcon icoDecor ;

   /**
    * Description de l'attribut 1
    */
   protected boolean bPiece ;


   /**
    * <b>Constructeur de Decor</b>
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


   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //Constructeur//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   public Decor(int largeur, int hauteur, int x, int y,boolean f) {
      this.largeur = largeur;
      this.hauteur = hauteur;
      this.x = x;
      this.y = y;
      this.bPiece=f;
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //getters//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public int getLargeur() {return largeur;}

   public int getHauteur() {return hauteur;}

   public int getX() {return x;}

   public int getY() {return y;}
   public Image getImgDecor() { return imgDecor; }

   public ImageIcon getIcoDecor() { return icoDecor; }

   public boolean isbPiece() { return bPiece; }

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //setters//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   public void setImgDecor(Image imgDecor) {this.imgDecor = imgDecor; }

   public void setIcoDecor(ImageIcon icodecor) {this.icoDecor = icodecor; }

   public void setLargeur(int largeur) {this.largeur = largeur;}

   public void setHauteur(int hauteur) {this.hauteur = hauteur;}

   public void setX(int x) {this.x = x;}

   public void setY(int y) {this.y = y;}

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //Methodes//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /**
    * deplacement() permet de déplacer un élément du décor.
    * Cette méthode est utilisée pour donner l'illusion que le personnage se déplace:
    * Les abscisses du fond et des éléments du décor sont modifiées à la même vitesse , puis un thread repaint le tout .
    * Seul l'abscisse du personnage ne change pas, ce qui donne l'illusion qu'il bouge.
    *
    */


   public void deplacement(){
      if(LaunchNPNG.scene.getxPos() >=0){
         this.x = this.x - LaunchNPNG.scene.getDx();
      }
   }
}
