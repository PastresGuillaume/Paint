package graphics.jeux.NoPaintNoGame.src.jeu;

import graphics.jeux.NoPaintNoGame.src.ecoute.Audio;
import graphics.jeux.NoPaintNoGame.src.jeu.Scene;

import javax.swing.*;

public class Main {
    public static Scene scene;


    public static void main(String[] args) {




        // Création de la fenêtre d'application
        JFrame fenetre = new JFrame("No paint No Game" );
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// quand on clique sur la croix rouge on ferme le programe
        fenetre.setSize(800,630);//taille
        fenetre.setLocationRelativeTo(null);//centrée
        fenetre.setResizable(false);//on ne la redimentionne pas
        fenetre.setAlwaysOnTop(true);//fenetre en premier plan




        //Jpannel où on stock les elements
        //Instanciation de l'objet scene
        scene = new Scene();

        fenetre.setContentPane(scene); //On associe la scene à la fenêtre de application
        fenetre.setVisible(true);

    }
}
