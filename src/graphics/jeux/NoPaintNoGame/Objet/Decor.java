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
 * Cette classe abstraite definit un decor qui peut être une pièce ,un bloc ou un tuyauRouge, qui sera dessiné dans la scène.
 * De plus ,grâce à la méthode contact ,le personnage pourra détecter ces éléments de décors et ne pas renter dedans lorsqu'il se déplace.
 *
 */


public abstract class Decor {
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                             //Création des données membres//
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
    * imgDecor
    */
   protected Image imgDecor  ;

   /**
    * icoDecor
    */
   protected ImageIcon icoDecor ;

   /**
    * bPiece
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

   /**
    * Constructeur.
    *
    * @param largeur Largeur du décor.
    * @param hauteur Hauteur du décor.
    * @param x Position x.
    * @param y Position y.
    * @param f Est-ce que c'est une pièce ?
    */
   public Decor(int largeur, int hauteur, int x, int y,boolean f) {
      this.largeur = largeur;
      this.hauteur = hauteur;
      this.x = x;
      this.y = y;
      this.bPiece=f;
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                      //Getters//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /**
    * Retourne le largueur du décor.
    *
    * @return La largueur du décor.
    */
   public int getLargeur() {return largeur;}

   /**
    * Retourne la hauteur du décor.
    *
    * @return La hauteur du décor.
    */
   public int getHauteur() {return hauteur;}

   /**
    * Getter de x.
    *
    * @return x
    */
   public int getX() {return x;}

   /**
    * Getter de y.
    *
    * @return y
    */
   public int getY() {return y;}

   /**
    * Getter pour l'image du décor
    *
    * @return L'image du décor
    */
   public Image getImgDecor() { return imgDecor; }

   /**
    * Getter pour l'icon du décor.
    * @return L'icon du décor.
    */
   public ImageIcon getIcoDecor() { return icoDecor; }

   /**
    * Est-ce qu'il y a des pièces ?
    * @return booléen
    */
   public boolean isbPiece() { return bPiece; }

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                      //Setters//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   /**
    * Setter pour l'image du décor.
    * @param imgDecor Nouvelle image.
    */
   public void setImgDecor(Image imgDecor) {this.imgDecor = imgDecor; }

   /**
    * Setter pour l'icon du décor.
    * @param icodecor Nouvelle icon.
    */
   public void setIcoDecor(ImageIcon icodecor) {this.icoDecor = icodecor; }

   /**
    * Setter pour la largeur.
    * @param largeur Nouvelle largeur.
    */
   public void setLargeur(int largeur) {this.largeur = largeur;}

   /**
    * Setter pour la hauteur.
    * @param hauteur Nouvelle hauteur.
    */
   public void setHauteur(int hauteur) {this.hauteur = hauteur;}

   /**
    * Setter pour x
    * @param x Nouveau x.
    */
   public void setX(int x) {this.x = x;}

   /**
    * Setter pour y
    * @param y Nouveau y.
    */
   public void setY(int y) {this.y = y;}

   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                      //Méthodes//
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /**
    * deplacement() permet de déplacer un élément du décor.
    * Cette méthode est utilisée pour donner l'illusion que le personnage se déplace:
    * Les abscisses du fond et les éléments du décor sont modifiés à la même vitesse, puis un thread repaint le tout .
    * Seul l'abscisse du personnage ne change pas, ce qui donne l'illusion qu'il bouge.
    *
    */


   public void deplacement(){
      if(LaunchNPNG.scene.getxPos() >=0){
         this.x = this.x - LaunchNPNG.scene.getDx();
      }
   }
}
