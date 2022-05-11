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
                                            //Création des données membres //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Un Clip
     */

    private Clip clip;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Constructeur //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Constructeur de Audio
     *
     * @param son Nom de l'audio
     */
    public Audio(String son){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(Constantes.PATH_SON+"/"+son+".wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                            //Méthodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *lance le son
     */
    public void play(){clip.start();}

    /**
     *arrête le son
     */

    public void stop(){clip.stop();}

    /**
     * lance le son qui a pour nom "son"
     *
     * @param son Nom du son
     */
    public static void playSound(String son){
        Audio s = new Audio(son);
        s.play();
    }
}








