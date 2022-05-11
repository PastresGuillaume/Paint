package graphics.jeux.NoPaintNoGame.jeu;

import graphics.jeux.NoPaintNoGame.ecoute.Audio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Clavier est une interface qui permet d'utiliser le clavier pour exécuter certaines commandes.
 * Elle hérite de KeyListener, qui est elle-même une interface.
 *
 */

public class Clavier implements KeyListener {

    /**
     *keyPressed permet de déclencher des fonctions lorsqu'on presse les flèches droite et gauche ou lorsque l'on appuie sur la barre espace
     *
     * @param e
     * c'est un KeyEvent , c'est à dire une commande du clavier
     *
     */

    //appuie sur la touche
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (LaunchNPNG.scene.getxPos() == -1) {
                LaunchNPNG.scene.setxPos(0);
                LaunchNPNG.scene.setxFond1(-50);
                LaunchNPNG.scene.setxFond2(1250);
            }
            LaunchNPNG.scene.mario.setVersDroite(true);
            if(!LaunchNPNG.scene.mario.isSaut()){
                LaunchNPNG.scene.mario.setMarche(true);
            }

            LaunchNPNG.scene.setDx(1);
        }

    // mario saute
            if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
                LaunchNPNG.scene.mario.setSaut(true);
                Audio.playSound("saut");
             }


        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(LaunchNPNG.scene.getxPos()== LaunchNPNG.scene.posMAX){
                LaunchNPNG.scene.setxPos(LaunchNPNG.scene.posMAX);
                LaunchNPNG.scene.setxFond1(-50);
                LaunchNPNG.scene.setxPos(1250);
            }
            LaunchNPNG.scene.setDx(-1);
            LaunchNPNG.scene.mario.setVersDroite(false);
            LaunchNPNG.scene.mario.setMarche(true);

        }

    }



    /**
     *keyPressed permet de déclencher des fonctions lorsque l'on relâche les flèches droite et gauche ou lorsque l'on appuie sur la barre espace
     *
     * @param e
     * c'est un KeyEvent , c'est à dire une commande du clavier
     *
     */

    //relâche la touche
    @Override
    public void keyReleased(KeyEvent e) {
        LaunchNPNG.scene.setDx(0);
        LaunchNPNG.scene.mario.setMarche(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}