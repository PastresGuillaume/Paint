package graphics.jeux.NoPaintNoGame.src.Affichage;

import jeu.Main;

public class CompteARebour implements Runnable {
    private final int PAUSE = 1000;
    private int compteurTemps;
    private String str;

    public CompteARebour() {
        this.compteurTemps = 100;
        this.str = "Temps restant : 100";
        Thread compteARebours = new Thread(this);
        compteARebours.start();
    }

    // GETTERS
    public int getCompteurTemps() {return compteurTemps;}
    public String getStr() {return str;}
    //Methodes
    @Override
    public void run(){
        while(true){
            //Main.scene.repaint();

            try {Thread.sleep(PAUSE);}catch(InterruptedException e){}
            this.compteurTemps--;
            this.str = "temps restant : "+ this.compteurTemps;
        }

    }
}
