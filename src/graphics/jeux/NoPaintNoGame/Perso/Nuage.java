package graphics.jeux.NoPaintNoGame.Perso;

import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;


import javax.swing.*;
import java.awt.*;

/**
        * Champi est la classe qui définit un champignon javadoc .
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

        private Image imgChamp;
        private ImageIcon icoChamp;

        private final int PAUSE = 15; // temps d'attente en ms entre 2 tours de boucle
        private int dxChamp; // pas de d�placement du champignon
        private boolean running;

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
        public Image getImgChamp() {return imgChamp;}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //**** METHODES ****//


    /**
     * bouge() permet de déplacer le champignon horizontalement ou verticalement
     */
        public void bouge(){ // D�placement du champignon
            if(super.isVersDroite() == true){this.dxChamp = 2;}
            else{this.dxChamp = -2;}
            super.setX(super.getX() + this.dxChamp);

            /*
            if(this.getX()>LaunchNPNG.scene.tuyauRouge1X){
                this.dxChamp = -2;
            System.out.println(this.getX());

            }
            else if (this.getX()< LaunchNPNG.scene.mario.getX()){
                this.dxChamp = -2;
                //System.out.println(this.getX());
            }*/

            //super.setX(super.getX() + this.dxChamp);
        }

    /**
     * run() active le thread associé au champignon
     *
     */

        @Override
        public void run() {
            try{Thread.sleep(20);} // on attend 20 ms avant d'appeler bouge pour que tous les objets soient compl�tement cr��s
            catch (InterruptedException e){}

            while(true){ // boucle infinie
                this.bouge();
                try{Thread.sleep(PAUSE);}
                catch (InterruptedException e){}
            }
        }
    /**
     *contact détecte un contact entre le champignon et un element du décor, si c'est le cas le champignon fait marche arrière
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


