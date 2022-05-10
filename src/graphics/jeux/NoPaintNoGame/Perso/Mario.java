package graphics.jeux.NoPaintNoGame.Perso;

import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.Objet.Piece;
import graphics.jeux.NoPaintNoGame.jeu.Main;

import javax.swing.*;
import java.awt.*;

public class Mario extends Personnage {
    private Image imgMario;
    private ImageIcon icoMario;
    private int compteurMort;
    private boolean saut; // vrai si il saute


//getters


//setters


    private int compteurSaut;


    //Constructeur//

    public Mario(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y, false, true);// 28 large sur 50 de haut*/
        this.icoMario = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"personnage.jpg");// quand on créer le fichier jar , le chemin change)
        this.imgMario = this.icoMario.getImage();
        this.compteurSaut = 0;
        this.compteurMort = 0;
        this.saut= false;

    }

    //Getter

    public Image getImgMario() {
        return imgMario;
    }
    public boolean isSaut() {
        return saut;
    }
    //setter
    public void setSaut(boolean saut) {
        this.saut = saut;
    }

    //Méthodes

    public Image saute() {
        ImageIcon ico;
        Image img;
        String str;

        this.compteurSaut++;
        //Montée du saut
        if (this.compteurSaut <= 70) { // Saut est appelé plein de fois, mario Monte, tant que ça ne dépasse pas 35
            if (this.getY() > Main.scene.getHauteurPlafond()) {
                this.setY(this.getY() - 5);
            } // ordonnées mesurée de haut en bas d'où le - 4 même s'il monte
            else {
                this.compteurSaut = 71;
            }
            if (this.isVersDroite()) {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageSautDroit.jpg";
            } else {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageSautGauche.jpg";
            }
        }

        //Retombé du saut
        else if (this.getY() < Main.scene.marioY) {
            this.setY(this.getY() + 1); // mario redescend jusqu'à ce que ses pieds touchent le sol
            if (this.isVersDroite()) {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageSautDroit.jpg";
            } else {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageSautGauche.jpg";
            }
        }

        //Saut terminé

        else {
            if (this.isVersDroite()) {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageArretDroit.jpg";
            } else {
                str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageArretGauche.jpg";
            }

            setSaut(false);
            this.compteurSaut = 0;

        }

        //Affichage de l'image du personnage
        ico = new ImageIcon(str);
        img = ico.getImage();
        return img;

    }

    public boolean contactPiece(Piece piece) {
        if (this.contactArriere(piece) || this.contactAvant(piece) || this.contactDessous(piece) || this.contactDessus(piece)) {
            return (true);
        } else {
            return (false);
        }

    }

    /*public boolean proche(Decor decor) {
        if (Main.scene.marioY == decor.getY()) {
            return (true);
        } else {
            return (false);
        }


    }*/

    public boolean proche(Decor objet){
        if((this.getX() > objet.getX() - 10 && this.getX() < objet.getX() + objet.getLargeur() + 10)
                || (this.getX() + this.getLargeur() > objet.getX() - 10 && this.getX() + this.getLargeur() < objet.getX() + objet.getLargeur() + 10)){return true;}
        else{return false;}
    }

    public Image meurt() {
        String str = null;
        ImageIcon ico;
        Image img;

        if (this.compteurMort > 100) {
            str = Constantes.PATH_NO_PAINT_NO_GAME+"personnageMort.jpg";
            this.setY(this.getY() - 1);
        }


        //ico =new ImageIcon(getClass().getResource(str));
        ico = new ImageIcon(str);
        img = ico.getImage();
        return img;

    }

    @Override
    public boolean isVivant() {
        return super.isVivant();
    }





    public void contact(Decor decor) {
        //Contact hotizontal
        if ((super.contactAvant(decor) && this.isVersDroite() || super.contactArriere(decor) && !this.isVersDroite())) {

            Main.scene.setDx(0);
            this.setMarche(false);
        }

        // contact avec un objet en dessous
        if (super.contactDessous(decor) && this.saut) {
            Main.scene.setMarioY(decor.getY()-this.getHauteur());
            Main.scene.setYsol(decor.getY()-this.getHauteur());
            this.setSaut(false);

            //this.setMarche(true);
        }
        // Mario saute sur un objet

        /*if (super.contactDessous(decor)){
            if(this.saut) {
                Main.scene.setMarioY(decor.getY()-this.getHauteur());
                Main.scene.setYsol(decor.getY()-this.getHauteur());
                this.setSaut(false);
                //this.setMarche(true);
            } // Mario saute sur un objet
            else{
                Main.scene.setMarioY(Main.scene.marioY0);
            }
        }*/

        else if (!super.contactDessous(decor)) {
            Main.scene.setYsol(Main.scene.marioY);//altitude du sol initial

            if (!this.saut) {
                this.setY(Main.scene.marioY);



            }//altitude initiale de mario
        }

        // contact avec un objet au dessus
        if (super.contactDessus(decor)) {
            Main.scene.setHauteurPlafond(decor.getY() + decor.getHauteur()); //le plafond devient le dessous de l'objet
        } else if (!contactDessus(decor) && !this.saut) {
            Main.scene.setHauteurPlafond(0);//altitud einitiale (ciel)
        }

    }





    public void fly(Decor decor){
        if(!super.contactDessous(decor) && !this.saut && this.getY() < Main.scene.marioY0){
            this.setY( Main.scene.marioY0);

        }
        else{

        }

    }
}

