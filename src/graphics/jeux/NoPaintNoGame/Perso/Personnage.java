package graphics.jeux.NoPaintNoGame.Perso;


import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;


import javax.swing.*;
import java.awt.*;

/**
 *
 * Personnage est la classe abstraite qui définit un personnage de javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li> Un boolean isVivant</li>
 * <li> Un int largeur</li>
 * <li> Un int hauteur</li>
 * <li> Un int x</li>
 * <li> Un int y</li>
 * <li> Un boolean marche</li>
 * <li> Un boolean versDroite</li>
 * <li> Un int compteur </li>
 * </ul>
 *
 * Cette classe
 *
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

    /**
     * marche renvoie la bonne image qui correspond au déplacement du personnage
     *
     * @param nom Nom du personnage.
     * @param frequence Nombre d'image dans le cycle
     *
     * @return une image
     */

    public Image marche(String nom, int frequence) {
        String str;
        ImageIcon ico;
        Image img;

        if (this.marche == false || LaunchNPNG.scene.getxPos() <= 0 ||LaunchNPNG.scene.getxPos() > LaunchNPNG.scene.posMAX) {
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

    /**
     *contactAvant détecte s'il y a une collision avec un element du décor et la partie droite de l'image du personnage
     *
     * @param objet Objet du décor.
     *
     * @return un boolean
     */

    public boolean contactAvant(Decor objet) {
        if (this.x + this.largeur < objet.getX() || this.x + this.largeur > objet.getX() + 5 || this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()) {
            return (false);
        } else {


            return (true);
        }
    }

    /**
     *contactDessous détecte s'il y a une collision avec un element du décor et le dessous de l'image du personnage
     *
     * @param objet
     *
     * @return un boolean
     */


    public boolean contactDessous(Decor objet) {
        if (this.x + this.largeur < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur() - 5 || this.y + this.hauteur < objet.getY() || this.y + this.hauteur > objet.getY() + 5) {
            return (false);
        } else {
            return (true);
        }
    }
    /**
     *contactDessus détecte s'il y a une collision avec un element du décor et le haut de l'image du personnage
     *
     * @param objet
     *
     * @return un boolean
     */

    public boolean contactDessus(Decor objet) {
        if (this.x + this.largeur < objet.getX() + 5 || this.x > objet.getX() + objet.getLargeur()  - 5|| this.y < objet.getY() + objet.getHauteur() || this.y > objet.getY() + objet.getHauteur() + 5) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     *contactArriere détecte s'il y a une collision avec un element du décor et la partie gauche de l'image du personnage
     *
     * @param objet
     *
     * @return un boolean
     */


    public boolean contactArriere(Decor objet) {
        if (this.x > objet.getX() + objet.getLargeur() || this.x + this.largeur < objet.getX() + objet.getLargeur() || this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()) {
            return (false);
        } else {
            return (true);
        }
    }
}

