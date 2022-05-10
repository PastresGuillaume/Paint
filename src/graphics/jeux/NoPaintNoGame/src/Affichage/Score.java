package graphics.jeux.NoPaintNoGame.src.Affichage;

public class Score {
    private final int nombPieceTotal= 10;
    private int nbrPiece;

    //Constructeur
    public Score(){
        this.nbrPiece = 0;
    }

    // Getters

    public int getNombPieceTotal() {
        return nombPieceTotal;
    }

    public int getNbrPiece() {
        return nbrPiece;
    }

    //Setters

    public void setNbrPiece(int nbrPiece) {
        this.nbrPiece = nbrPiece;
    }
}
