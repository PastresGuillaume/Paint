package graphics.jeux.NoPaintNoGame.Perso;

import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.Objet.Piece;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;


import javax.swing.*;
import java.awt.*;

/**
 * Mario est la classe qui définit le personnagede javadoc que le joueur utilise
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 *  <li>Une Image imgMario</li>
 *  <li>Une ImageIcon icoMario</li>
 *  <li>Un int compteurMort</li>
 *  <li>Un boolean saut</li>
 *  <li>Un int compteurSaut</li>
 * </ul>
 * Mario est le personnage que le joueur va utiliser dans NoPaintNoGame.
 *
 */


public class Mario extends Personnage {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Création des donnees membres//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Une Image imgMario
     */
    private Image imgMario;

    /**
     *Une ImageIcon icoMario
     */
    private ImageIcon icoMario;

    /**
     *Un int compteurMort
     */
    private int compteurMort;

    /**
     *Un boolean saut
     */
    private boolean saut; // vrai si il saute
    /**
     *Un int compteurSaut
     */
    private int compteurSaut;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * <b>Constructeur de Decor</b>
 *
 * @param largeur
 * @param hauteur
 * @param x
 * @param y
 *
 */

    public Mario(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y, false, true);// 28 large sur 50 de haut*/
        this.icoMario = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"personnage.jpg");// quand on créer le fichier jar , le chemin change)
        this.imgMario = this.icoMario.getImage();
        this.compteurSaut = 0;
        this.compteurMort = 0;
        this.saut= false;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Image getImgMario() {
        return imgMario;
    }
    public boolean isSaut() {
        return saut;
    }

    @Override
    public boolean isVivant() { return super.isVivant(); }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Setters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setSaut(boolean saut) {
        this.saut = saut;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //METHODES//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 *saute() permet de faire sauter Mario; c'est à dire de déplacer son image vers le haut jusqu'à une hauteur maximale appellée HauteurPlafond.
 * Puis de faire retomber l'image sur le sol ( ou sur un objet en cas de contact)
 *
 * @return une image
 */
    public Image saute() {
        ImageIcon ico;
        Image img;
        String str;

        this.compteurSaut++;
        //Montée du saut
        if (this.compteurSaut <= 70) { // Saut est appelé plein de fois, mario Monte, tant que ça ne dépasse pas 35
            if (this.getY() > LaunchNPNG.scene.getHauteurPlafond()) {
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
        else if (this.getY() < LaunchNPNG.scene.marioY) {
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
    /**
     *contactPiece détecte s'il y a un contact avec une piece
     *
     * @param decor
     *
     * @return un boolean
     */

    public boolean contactPiece(Decor decor) {
        if (this.contactArriere(decor) || this.contactAvant(decor) || this.contactDessous(decor) || this.contactDessus(decor)) {
            return (true);
        } else {
            return (false);
        }

    }


    /**
     *proche détecte s'il y a un element du decor proche du joueur
     *
     * @param objet
     * c'est un element du décor
     *
     * @return un boolean
     */
    public boolean proche(Decor objet){
        if((this.getX() > objet.getX() - 10 && this.getX() < objet.getX() + objet.getLargeur() + 10)
                || (this.getX() + this.getLargeur() > objet.getX() - 10 && this.getX() + this.getLargeur() < objet.getX() + objet.getLargeur() + 10)){return true;}
        else{return false;}
    }

    /**
     *meurt retourne l'image de mario s'il meurt
     *
     * @return une image
     */

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

    /**
     * Contact détecte s'il y a une collision entre un élément du decor et mario.
     * Si c'est le cas ,empèche mario d'avancer en modifiant son boolean de marche
     *
     * @param decor
     * c'est un element du décor
     *
     */

    public void contact(Decor decor, int i) {


            //Contact hotizontal
            if ((super.contactAvant(decor) && this.isVersDroite() || super.contactArriere(decor) && !this.isVersDroite())) {
                if(decor.isbPiece()) { LaunchNPNG.scene.tabDecor.remove(decor);}
                else{LaunchNPNG.scene.setDx(0);
                    this.setMarche(false);}

            }

            // contact avec un objet en dessous
            if (super.contactDessous(decor) && this.saut) {
                if(decor.isbPiece()) { LaunchNPNG.scene.tabDecor.remove(decor);}
                else{
                    LaunchNPNG.scene.setMarioY(decor.getY() - this.getHauteur());
                    LaunchNPNG.scene.setYsol(decor.getY() - this.getHauteur());
                    this.setSaut(false);
                }


                //this.setMarche(true);
            } else if (!super.contactDessous(decor)) {

                if(decor.isbPiece()) { LaunchNPNG.scene.tabDecor.remove(decor);}
                else{
                    LaunchNPNG.scene.setYsol(LaunchNPNG.scene.marioY);//altitude du sol initial

                    if (!this.saut) {
                        this.setY(LaunchNPNG.scene.marioY);
                }



                }//altitude initiale de mario
            }

            // contact avec un objet au dessus
            if (super.contactDessus(decor) && !decor.isbPiece()) {

                LaunchNPNG.scene.setHauteurPlafond(decor.getY() + decor.getHauteur()); //le plafond devient le dessous de l'objet

            }
            else if (!contactDessus(decor) && !this.saut && !decor.isbPiece()) {

                LaunchNPNG.scene.setHauteurPlafond(0);//altitud einitiale (ciel)
            }
        }





    /**
     * Fly permet de ramener mario sur le sol après qu'il soit monté sur un objet
     *
     * @param decor
     * c'est un element du décor
     *
     */


    public void fly(Decor decor){
        if(!super.contactDessous(decor) && !this.saut && this.getY() < LaunchNPNG.scene.marioY0){
            this.setY( LaunchNPNG.scene.marioY0);

        }
        else{

        }

    }

    public void noFly(Decor decor){
        if(!super.contactDessus(decor) && !this.saut && this.getY() < LaunchNPNG.scene.marioY0){
            this.setY( LaunchNPNG.scene.marioY0);

        }
        else{

        }

    }
}

