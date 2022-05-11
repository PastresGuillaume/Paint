package graphics.jeux;

import graphics.Constantes;
import graphics.ui.Visitor.GameDraftman;
import graphics.ui.Visitor.ShapeVisitor;

/**
 * Définition du mort pion.
 */
public class MortPion implements Game{
    /**
     * Plateau.
     */
    private int[][] board;
    /**
     * Couleur du joueur actuel.
     */
    private int couleur;

    /**
     * Renvoie le plateau.
     *
     * @return Le plateau.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Renvoie la couleur du joueur actuel.
     *
     * @return La couleur du joueur actuel.
     */
    public int getCouleur() {
        return couleur;
    }

    /**
     * Constructeur.
     */
    public MortPion(){
        couleur = 1;
        board = new int[3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                board[i][j] = 0;
            }
        }
    }

    /**
     * Est-ce que la partie est gagnée ?
     *
     * @return Est-ce que la partie est gagnée ?
     */
    @Override
    public boolean isWin() {
        for (int i=0; i<3; i++){
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return true;
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return true;
        }
        if (board[1][1] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return true;
        if (board[1][1] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2])
            return true;
        return false;
    }

    /**
     * Est-ce que la partie est nulle ?
     *
     * @return Est-ce que la partie est nulle ?
     */
    @Override
    public boolean isNull() {
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (board[i][j]==0){
                    return false;
                }
            }
        }
        return !isWin();
    }

    /**
     * Qui a gagnée ? Si aucun gagnant alors 0
     *
     * @return Une valeur entière désignant le vainqueur.
     */
    @Override
    public int whoWin() {
        for (int i=0; i<3; i++){
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        if (board[1][1] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[1][1];
        if (board[1][1] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2])
            return board[1][1];
        return 0;
    }

    /**
     * Renvoie l'identifiant du jeu.
     *
     * @return Identifiant du jeu.
     */
    @Override
    public int getId() {
        return Constantes.GAME_ID_MORT_PION;
    }

    /**
     * Joue un coup dans la couleur du joueur actuel.
     *
     * @param x coordonnée x depuis en haut à gauche.
     * @param y coordonnée y depuis en haut à gauche.
     */
    public void play(int x, int y){
        if (this.isWin() || this.isNull())
            return;
        if (board[x][y]==0){
            board[x][y] = couleur;
            couleur = couleur==1?2:1;
        }
    }

    /**
     * Fonction de dessin.
     *
     * @param visitor Dessinateur.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        ((GameDraftman) visitor).visitMortPion(this);
    }
}
