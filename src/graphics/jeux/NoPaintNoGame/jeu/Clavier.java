package graphics.jeux.NoPaintNoGame.jeu;
//import com.company.Main;

//import Audio.Audio;
import graphics.jeux.NoPaintNoGame.ecoute.Audio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Clavier est une interface qui permet de d'utiliser le clavier pour exécuter certaines commandes.
 * Elle hérite de KeyListener, qui est elle même une interface.
 *
 */

public class Clavier implements KeyListener {

    /**
     *keyPressed permet de déclancher des fonctions lorsqu'on presse les flèches droites et gauches ou lorsqu'on appuie sur la barre espace
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
//                TODO remettre
//                Audio.playSound(Constantes.PATH_SON+"saut.wav");
                Audio.playSound("saut");
             }


           /* Main.scene.mario.setMarche(true);
            Main.scene.mario.setVersDroite(false);
            Main.scene.setDx(1);*/

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
     *keyPressed permet de déclancher des fonctions lorsqu'on relache les flèches droites et gauches ou lorsqu'on appuie sur la barre espace
     *
     * @param e
     * c'est un KeyEvent , c'est à dire une commande du clavier
     *
     */

    //relache la touche
    @Override
    public void keyReleased(KeyEvent e) {
        LaunchNPNG.scene.setDx(0);
        LaunchNPNG.scene.mario.setMarche(false);


        /*Main.scene.mario.setMarche(false);*/
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}