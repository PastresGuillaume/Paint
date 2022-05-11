package graphics.jeux.NoPaintNoGame.jeu;

import javax.swing.*;

/**
 * Launcher pour le NPNG
 */
public class LaunchNPNG {
    /**
     * Scene
     */
    public static Scene scene;

    /**
     * Lancement du jeu
     */
    public void noPaintNoGame(){

        // Création de la fenêtre d'application
        JFrame fenetre = new JFrame("No paint No Game" );
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// quand on clique sur la croix rouge on ferme le programme
        fenetre.setSize(800,630);//taille
        fenetre.setLocationRelativeTo(null);//centrée
        fenetre.setResizable(false);//on ne la redimentionne pas
        fenetre.setAlwaysOnTop(true);//fenêtre en premier plan

        //Jpannel où on stocke les elements
        //Instanciation de l'objet scène
        scene = new Scene();

        fenetre.setContentPane(scene); //On associe la scène à la fenêtre de application
        fenetre.setVisible(true);
    }
}
