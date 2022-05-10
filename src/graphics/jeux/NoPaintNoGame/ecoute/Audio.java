package graphics.jeux.NoPaintNoGame.ecoute;


import graphics.Constantes;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    //variable

    private Clip clip;
    /*private boolean Sfond=true;
    private boolean Sgagne=false;


    public static boolean isSfond() {
        return Sfond;
    }

    public static boolean isSgagne() {
        return Sgagne;
    }

    public void setSfond(boolean sfond) {
        Sfond = sfond;
    }

    public void setSgagne(boolean sgagne) {
        Sgagne = sgagne;
    }*/







    //getter

    public Clip getClip() {
        return clip;
        }

    //Methodes
    public void play(){clip.start();}

    public void stop(){clip.stop();}


     public Audio(String son){
        try{
//            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(Constantes.PATH_SON+"saut.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void playSound(String son){
        Audio s = new Audio(son);
        s.play();
    }

     /*public void MusicFond(){
        if(isSfond()){
            Audio.playSound(Constantes.PATH_SON+"fond.wav");
        }
        else{
            if(isSgagne()){Audio.playSound(Constantes.PATH_SON+"gagne.wav");}
             else{ Audio.playSound(Constantes.PATH_SON+"perdu.wav"); }

    }*/

}








