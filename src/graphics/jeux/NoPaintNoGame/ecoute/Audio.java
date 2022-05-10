package graphics.jeux.NoPaintNoGame.ecoute;


import graphics.Constantes;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
/**
 * Audio est la classe qui définit un son dans la javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 *  <li> Un Clip clip PAUSE</li>
 * </ul>
 *
 * Audio est la classe qui permet d'inclure du son
 */

public class Audio {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Création des donnees membres //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Un Clip clip
     */

    private Clip clip;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * <b>Constructeur de Audio</b>
     *
     * @param son
     *
     */


    public Audio(String son){
        try{
//            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(Constantes.PATH_SON+"son.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *play lance le son
     */
    public void play(){clip.start();}

    /**
     *play arrete le son
     */

    public void stop(){clip.stop();}

    /**
     *playSound lance le son qui a pour nom "son"
     *
     * @param son
     */

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








