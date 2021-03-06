package graphics.jeux.NoPaintNoGame.Perso;

import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;


import javax.swing.*;
import java.awt.*;

/**
        * Champi est la classe qui définit un nuage.
        * Cette classe est caractérisée par les informations suivantes :
        * <ul>
 *  <li>Une Image imgMario</li>
        *  <li>Une ImageIcon icoMario</li>
        *  <li>Un int compteurMort</li>
        *  <li>Un boolean saut</li>
        *  <li>Un int compteurSaut</li>
        *  <li>Un int PAUSE</li>
        *  <li>Un boolean running</li>
        *  <li>Un int compteurSaut</li>
        * </ul>
        *
        *
        */


public class Nuage extends Personnage implements Runnable{
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Données membres//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * image du champ
     */
        private Image imgChamp;
    /**
     * icon du champ
     */
        private ImageIcon icoChamp;

    /**
     * temps d'attente en ms entre 2 tours de boucle
     */
        private final int PAUSE = 15;
    /**
     * dxChamp
     */
        private int dxChamp; // pas de déplacement du nuage
    /**
     * running
     */
        private boolean running;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                    //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * <b>Constructeur de Decor</b>
     *
     * @param largeur Largeur du nuage
     * @param hauteur hauteur du nuage
     * @param x position x
     * @param y position y
     */
        //**** CONSTRUCTEUR	****//
        public Nuage(int largeur, int hauteur , int x, int y) {

            super(largeur, hauteur, x, y,true,true);
            this.dxChamp = 1;
            this.icoChamp = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+ "champArretDroite.jpg");
            this.imgChamp = this.icoChamp.getImage();
            this.running=true;

            Thread chronoChamp = new Thread(this);
            chronoChamp.start();
        }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //**** GETTERS ****//

    /**
     * getter pour imgChamp
     *
     * @return imgChamp
     */
        public Image getImgChamp() {return imgChamp;}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Méthodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //**** METHODES ****//


    /**
     * Permet de déplacer le nuage horizontalement ou verticalement
     */
        public void bouge(){ // D�placement du nuage
            if(super.isVersDroite() == true){this.dxChamp = 2;}
            else{this.dxChamp = -2;}
            super.setX(super.getX() + this.dxChamp);
        }

    /**
     * Active le thread associé au nuage
     *
     */

        @Override
        public void run() {
            try{Thread.sleep(20);} // on attend 20 ms avant d'appeler bouge() pour que tous les objets soient complètement créés
            catch (InterruptedException e){}

            while(true){ // boucle infinie
                this.bouge();
                try{Thread.sleep(PAUSE);}
                catch (InterruptedException e){}
            }
        }
    /**
     * Détecte un contact entre le nuage et un element du décor, si c'est le cas le nuage fait marche arrière
     *
     * @param decor élément du decor
     */
        public void contact(Decor decor) {
            if(super.contactAvant(decor) && this.isVersDroite()){
                super.setVersDroite(false);
                this.dxChamp = -1;
            }else if(super.contactArriere(decor) && !this.isVersDroite()){
                super.setVersDroite(true);
                this.dxChamp = 1;
            }
        }




    }


