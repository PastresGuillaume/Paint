package graphics.jeux.NoPaintNoGame.Affichage;

/**
 * CompteARebour est la classe qui définit le compte à rebour de javadoc
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 *  <li> Un int PAUSE</li>
 *  <li> Un int compteurTemps</li>
 *  <li> Un String str</li>
 *
 * </ul>
 * CompteARebour est un thread qui détermine le temps qu'il reste au personnage pour finir le jeu.
 *
 */

public class CompteARebour implements Runnable {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                //Création des données membres //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Un int PAUSE
     */
    private final int PAUSE = 1000;
    /**
     * Un int compteurTemps
     */
    private int compteurTemps;
    /**
     * Un String str
     */
    private String str;

    /**
     * Stop
     */
    private boolean STOP=true;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * <b>Constructeur de Decor</b>
     *
     * Créer un thread , qui calcule le temps qu'il reste au personnage pour finir le jeu.
     *
     */
    public CompteARebour() {
        this.compteurTemps = 100;
        this.str = "Temps restant : 100";
        Thread compteARebours = new Thread(this);
        compteARebours.start();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                      //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Getter pour le temps.
     *
     * @return Entier donnant la durée.
     */
    public int getCompteurTemps() {return compteurTemps;}

    /**
     * Retourne le temps restant sous forme de string.
     *
     * @return Temps restant sous forme de string
     */
    public String getStr() {return str;}

    /**
     * Setter pour stop
     * @param STOP Nouvelle valeur de stop
     */
    public void setSTOP(boolean STOP) {
        this.STOP = STOP;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                      //Méthodes//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Lance le thread et affiche le temps qu'il reste à l'écran
     */
    @Override
    public void run(){
        while(STOP){
            //Main.scene.repaint();

            try {Thread.sleep(PAUSE);}catch(InterruptedException e){}
            this.compteurTemps--;
            this.str = "temps restant : "+ this.compteurTemps;
        }

    }
}
