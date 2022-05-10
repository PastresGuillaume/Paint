package graphics.jeux.NoPaintNoGame.Perso;


import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.jeu.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Exemple est la classe qui définit un exemple de javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>Description fonctionnelle de l'attribut 1</li>
 * <li>Description fonctionnelle de l'attribut 2</li>
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 * Description complémentaire, sur les attributs statiques par exemple
 * </p>
 * @author nom de l'auteur
 * @version numéro de version
 */


public  abstract class Personnage {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Création des donnees membres//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isVivant;
    private int largeur;
    private int hauteur;
    private int x;
    private int y;
    private boolean marche;
    private boolean versDroite;
    private int compteur;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Personnage(int largeur, int hauteur, int x, int y, boolean marche, boolean versDroite) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.x = x;
        this.y = y;
        this.marche = marche;
        this.versDroite = versDroite;
        this.compteur = 0;

        this.isVivant = true;


    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isVivant() {
        return isVivant;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMarche() {
        return marche;
    }

    public boolean isVersDroite() {
        return versDroite;
    }

    public int getCompteur() {
        return compteur;
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Setters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void setVivant(boolean vivant) {
        this.isVivant = vivant;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMarche(boolean marche) {
        this.marche = marche;
    }

    public void setVersDroite(boolean versDroite) {
        this.versDroite = versDroite;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //METHODES//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Image marche(String nom, int frequence) {
        String str;
        ImageIcon ico;
        Image img;

        if (this.marche == false || Main.scene.getxPos() <= 0 || Main.scene.getxPos() > 5000) {
            if (this.versDroite) {
                str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "ArretDroit.jpg";
            } else {
                str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "ArretGauche.jpg";
            }
        } else {
            this.compteur++;

            if (this.compteur / frequence == 0) {
                if (this.versDroite) {
                    str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "ArretDroit.jpg";
                } else {
                    str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "ArretGauche.jpg";
                }
            } else {
                if (this.versDroite) {
                    str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "MarcheDroit.jpg";
                } else {
                    str = Constantes.PATH_NO_PAINT_NO_GAME + nom + "MarcheGauche.jpg";
                }
            }
            if (this.compteur == 2 * frequence) {
                this.compteur = 0;
            }
        }
        //Affichage de l'image du personnage


        ico = new ImageIcon(str);
        img = ico.getImage();
        return img;


    }






    public boolean contactAvant(Decor objet) {
        if (this.x + this.largeur < objet.getX() || this.x + this.largeur > objet.getX() + 5 || this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()) {
            return (false);
        } else {


            return (true);
        }
    }


    public boolean contactDessous(Decor objet) {
        if (this.x + this.largeur < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur() - 5 || this.y + this.hauteur < objet.getY() || this.y + this.hauteur > objet.getY() + 5) {
            return (false);
        } else {
            return (true);
        }
    }


    public boolean contactDessus(Decor objet) {
        if (this.x + this.largeur < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur()  - 5|| this.y < objet.getY() + objet.getHauteur() || this.y > objet.getY() + objet.getHauteur() + 5) {
            return (false);
        } else {
            return (true);
        }
    }


    public boolean contactArriere(Decor objet) {
        if (this.x > objet.getX() + objet.getLargeur() || this.x + this.largeur < objet.getX() + objet.getLargeur() || this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()) {
            return (false);
        } else {
            return (true);
        }
    }
}

