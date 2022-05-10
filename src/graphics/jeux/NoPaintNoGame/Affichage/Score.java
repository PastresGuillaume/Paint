package graphics.jeux.NoPaintNoGame.Affichage;

public class Score {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            //Création des donnees membres //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int nombPieceTotal= 10;
    private int nbrPiece;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Score(){
        this.nbrPiece = 0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getNombPieceTotal() {
        return nombPieceTotal;
    }

    public int getNbrPiece() {
        return nbrPiece;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Setters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setNbrPiece(int nbrPiece) {
        this.nbrPiece = nbrPiece;
    }
}
