package graphics.jeux.NoPaintNoGame.src.jeu;
//import com.company.Main;

//import Audio.Audio;
import graphics.jeux.NoPaintNoGame.src.ecoute.Audio;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {



    //appuie sur la touche
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (Main.scene.getxPos() == -1) {
                Main.scene.setxPos(0);
                Main.scene.setxFond1(-50);
                Main.scene.setxFond2(1250);
            }
            Main.scene.mario.setVersDroite(true);
            Main.scene.mario.setMarche(true);
            Main.scene.setDx(1);
        }

// mario saute
            if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
                Main.scene.mario.setSaut(true);
                Audio.playSound("/son/saut.wav");
             }


           /* Main.scene.mario.setMarche(true);
            Main.scene.mario.setVersDroite(false);
            Main.scene.setDx(1);*/

        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(Main.scene.getxPos()==5000){
                Main.scene.setxPos(5000);
                Main.scene.setxFond1(-50);
                Main.scene.setxPos(1250);
            }
            Main.scene.setDx(-1);
            Main.scene.mario.setVersDroite(false);
            Main.scene.mario.setMarche(true);

        }

    }





    //relache la touche
    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.setDx(0);
        Main.scene.mario.setMarche(false);


        /*Main.scene.mario.setMarche(false);*/
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}