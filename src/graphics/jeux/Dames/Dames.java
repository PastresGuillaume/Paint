package graphics.jeux.Dames;

import graphics.Constantes;
import graphics.jeux.Game;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Dames implements Game {
    private int[][] board;
    private int couleur;

    public Dames(Dames dames) {
        this.board = new int[8][8];
        for (int i=0; i<8; i++){
            for (int j = 0; j <8; j++){
                this.board[i][j] = dames.board[i][j];
            }
        }
        this.couleur = dames.getCouleur();
    }

    public Dames(int[][] board) {
        this.board = new int[8][8];
        for (int i=0; i<8; i++){
            for (int j = 0; j <8; j++){
                this.board[i][j] = board[i][j];
            }
        }
        couleur = 1;
    }

    public Dames() {
        this.board = new int[8][8];
        for (int i=0; i<8; i++){
            for (int j = 0; j <8; j++){
                this.board[i][j]=0;
            }
        }
    }

    public int getCouleur() {
        return couleur;
    }

    public ArrayList<Coup> coupPossible() {
        ArrayList<Coup> retour = new ArrayList<>();
//        TODO coupPossible
        return retour;
    }

    private boolean coupValidePionSans(@NotNull Coup coup){
        if (this.board[coup.getPosition_x()][coup.getPosition_y()]!=couleur){
            return false;
        }
        if (this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()]!=0){
            return false;
        }
        if (Math.abs(coup.getPosition_x()-coup.getSuivant().getPosition_x())!=1){
            return false;
        }
        if (Math.abs(coup.getPosition_y()-coup.getSuivant().getPosition_y())!=1){
            return false;
        }
        return true;
    }

    private boolean coupValidePionAvec(@NotNull Coup coup){
        if (this.board[coup.getPosition_x()][coup.getPosition_y()]!=couleur){
            return false;
        }
        if (this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()]==0 ||
                this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()]==couleur ||
                this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()]==11*couleur){
            return false;
        }
        if (Math.abs(coup.getPosition_x()-coup.getSuivant().getPosition_x())!=2){
            return false;
        }
        if (Math.abs(coup.getPosition_y()-coup.getSuivant().getPosition_y())!=2){
            return false;
        }
        if (coup.getLength()==1) {
            return true;
        }
        Dames travail = new Dames(this);
        travail.playPrivateSansCreationDames(coup.getFirst());
        return travail.coupValidePionAvec(coup.getSuivant());
    }

    private boolean coupValideDameSans(@NotNull Coup coup){
        if (this.board[coup.getPosition_x()][coup.getPosition_y()]!=11*couleur){
            return false;
        }
        if (coup.getLength()>1){
            return false;
        }
        int delta_x = coup.getSuivant().getPosition_x()-coup.getPosition_x();
        int delta_y = coup.getSuivant().getPosition_y()-coup.getPosition_y();
        if (delta_x==0 || delta_y==0){
            return false;
        }
        int sens_x = delta_x>0?1:-1;
        int sens_y = delta_y>0?1:-1;
        if (sens_x*delta_x!=sens_y*delta_y){
            return false;
        }
        for (int i = coup.getPosition_x()+sens_x; i<coup.getSuivant().getPosition_x(); i++){
            for (int j = coup.getPosition_y()+sens_y; j <coup.getSuivant().getPosition_y(); j++){
                if (this.board[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean coupValideDameAvec(Coup coup){
        if (this.board[coup.getPosition_x()][coup.getPosition_y()]!=11*couleur){
            return false;
        }
//        TODO
        return false;
    }

    public boolean coupValide(@NotNull Coup coup){
        if (this.board[coup.getPosition_x()][coup.getPosition_y()]>10){
            return coupValideDameSans(coup)||coupValideDameAvec(coup);
        }
        return coupValidePionSans(coup)||coupValidePionAvec(coup);
    }

    private void playPrivateSansCreationDames(@NotNull Coup coup){
        int sens_x = coup.getSuivant().getPosition_x()-coup.getPosition_x()>0?1:-1;
        int sens_y = coup.getSuivant().getPosition_y()-coup.getPosition_y()>0?1:-1;
        for (int i = coup.getPosition_x(); i<coup.getSuivant().getPosition_x(); i+=sens_x){
            for (int j = coup.getPosition_y(); j <coup.getSuivant().getPosition_y(); j +=sens_y){
                this.board[i][j]=0;
            }
        }
        this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()] = this.couleur;
        if (coup.getLength()>1) {
            playPrivateSansCreationDames(coup.getSuivant());
        }
    }

    private void playPrivate(@NotNull Coup coup){
        playPrivateSansCreationDames(coup);
        if (coup.getSuivant().getPosition_x()==(couleur==1?0:7)){
            this.board[coup.getSuivant().getPosition_x()][coup.getSuivant().getPosition_y()] = 11*this.couleur;
        }
    }

    public void play(Coup coup){
        if (coup!=null){
            if (this.board[coup.getPosition_x()][coup.getPosition_y()]!=couleur || this.board[coup.getPosition_x()][coup.getPosition_y()]!=11*couleur){
                return;
            }
            if (!coupValide(coup)){
                return;
            }
            playPrivate(coup);
            this.couleur = this.couleur==1?2:1;
        }
    }

    @Override
    public int getId() {
        return Constantes.GAME_ID_DAMES;
    }

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public int whoWin() {
        return 0;
    }
}
