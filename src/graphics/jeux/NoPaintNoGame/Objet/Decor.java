package graphics.jeux.NoPaintNoGame.Objet;

import graphics.jeux.NoPaintNoGame.jeu.Main;

import javax.swing.*;
import java.awt.*;

public abstract class Decor {
   private int largeur;
   private int hauteur;
   private int x;
   private int y;
   protected Image imgDecor  ;
   protected ImageIcon icoDecor ;

   public Decor(int largeur, int hauteur, int x, int y) {
      this.largeur = largeur;
      this.hauteur = hauteur;
      this.x = x;
      this.y = y;
   }

   //GETTERS
   public int getLargeur() {return largeur;}

   public int getHauteur() {return hauteur;}

   public int getX() {return x;}

   public int getY() {return y;}
   public Image getImgDecor() { return imgDecor; }

   public ImageIcon getIcoDecor() { return icoDecor; }
   //SETTERS


   public void setImgDecor(Image imgDecor) {this.imgDecor = imgDecor; }

   public void setIcoDecor(ImageIcon icodecor) {this.icoDecor = icodecor; }

   public void setLargeur(int largeur) {this.largeur = largeur;}

   public void setHauteur(int hauteur) {this.hauteur = hauteur;}

   public void setX(int x) {this.x = x;}

   public void setY(int y) {this.y = y;}

   //Méthode
   public void deplacement(){
      if(Main.scene.getxPos() >=0){
         this.x = this.x -Main.scene.getDx();
      }
   }
}
