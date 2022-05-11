package graphics.jeux.NoPaintNoGame.Perso;


import graphics.Constantes;
import graphics.jeux.NoPaintNoGame.Objet.Decor;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;


import javax.swing.*;
import java.awt.*;

/**
 *
 * Personnage est la classe abstraite qui définit un personnage.
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
 */


public  abstract class Personnage {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                         //Création des données membres//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Est-ce qu'on est vivant ?
     */
    public boolean isVivant;
    /**
     * largeur du personnage
     */
    private int largeur;
    /**
     * hauteur du personnage
     */
    private int hauteur;
    /**
     * x
     */
    private int x;
    /**
     * y
     */
    private int y;
    /**
     * Est-ce qu'on marche ?
     */
    private boolean marche;
    /**
     * Est-ce qu'on va vers la droite ?
     */
    private boolean versDroite;
    /**
     * compteur
     */
    private int compteur;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructeur.
     *
     * @param largeur Largeur du perso
     * @param hauteur Hauteur du perso
     * @param x Position x
     * @param y Position y
     * @param marche Est-ce qu'on marche ?
     * @param versDroite Est-ce qu'on va vers la droite ?
     */
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

    /**
     * Est-ce que je suis encore en vie ?
     * @return Est-ce que je suis encore en vie ?
     */
    public boolean isVivant() {
        return isVivant;
    }

    /**
     * Getter pour la largeur
     *
     * @return La largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Getter pour la hauteur
     *
     * @return La hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Getter pour x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter pour y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Est-ce qu'on marche ?
     *
     * @return Est-ce qu'on marche ?
     */
    public boolean isMarche() {
        return marche;
    }

    /**
     * Est-ce qu'on va vers la droite ?
     *
     * @return Est-ce qu'on va vers la droite ?
     */
    public boolean isVersDroite() {
        return versDroite;
    }

    /**
     * Getter pour le compteur
     *
     * @return Le compteur
     */
    public int getCompteur() {
        return compteur;
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                         //Setters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Setter pour vivant
     *
     * @param vivant Nouvelle valeur de vivant
     */
    public void setVivant(boolean vivant) {
        this.isVivant = vivant;
    }

    /**
     * Setter pour la x
     *
     * @param x Nouvelle x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter pour la y
     *
     * @param y Nouvelle y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setter pour la marche
     *
     * @param marche Nouvelle marche
     */
    public void setMarche(boolean marche) {
        this.marche = marche;
    }

    /**
     * Setter pour versDroite
     *
     * @param versDroite Nouvelle valeur pour versDroite
     */
    public void setVersDroite(boolean versDroite) {
        this.versDroite = versDroite;
    }

    /**
     * Setter pour le compteur.
     *
     * @param compteur nouveau compteur
     */
    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Méthodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Renvoie la bonne image qui correspond au déplacement du personnage
     *
     * @param nom Nom du personnage.
     * @param frequence Nombre d'images dans le cycle
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
     *Détecte s'il y a une collision avec un élément du décor et la partie droite de l'image du personnage
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
     *Détecte s'il y a une collision avec un élément du décor et le dessous de l'image du personnage
     *
     * @param objet Objet du décor.
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
     *Détecte s'il y a une collision avec un élément du décor et le haut de l'image du personnage
     *
     * @param objet Objet du décor.
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
     *Détecte s'il y a une collision avec un élément du décor et la partie gauche de l'image du personnage
     *
     * @param objet Objet du décor.
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

    /**
     * déplacement
     */
    public void deplacement(){
        if( LaunchNPNG.scene.getxPos() >= 0){this.x = this.x -  LaunchNPNG.scene.getDx();}
    }

}

