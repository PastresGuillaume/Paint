package graphics.jeux.NoPaintNoGame.jeu;
//import com.company.Main;

//import Audio.Audio;
import graphics.jeux.NoPaintNoGame.ecoute.Audio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {



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
                Audio.playSound("son/saut.wav");
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