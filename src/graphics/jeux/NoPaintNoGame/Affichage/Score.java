package graphics.jeux.NoPaintNoGame.Affichage;

/**
 * Score est la classe qui définit le score de javadoc.
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 *  <li> private final int nombPieceTotal</li>
 *  <li> Un int nbrPiece</li>
 *
 * </ul>
 * Le score est le nombre de pièces récupérées par le joueur.
 *
 */
public class Score {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            //Création des données membres //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Un int nombPieceTotal
     * C'est le nombre de pièces qu'il y a au total dans le jeu
     */
    private final int nombPieceTotal= 10;
    /**
     * Un int nbrPiece
     * c'est le nombre de pièces que le joueur a ramassé
     */
    private int nbrPiece;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                    //Constructeur//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructeur
     */
    public Score(){
        this.nbrPiece = 0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Getters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Getter pour le nombre de pièces total.
     * @return nombre de pièces total
     */
    public int getNombPieceTotal() {
        return nombPieceTotal;
    }

    /**
     * Getter pour le nombre de pièces.
     * @return nombre de pièces
     */
    public int getNbrPiece() {
        return nbrPiece;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Setters//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Setter pour le nombre de pièces
     *
     * @param nbrPiece Nouveau nombre
     */
    public void setNbrPiece(int nbrPiece) {
        this.nbrPiece = nbrPiece;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                        //Méthode//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Incrémente le nombre de pièces de 1
     */
    public void compteurPiece(){
        if(getNbrPiece()<getNombPieceTotal()){
            setNbrPiece(getNbrPiece()+1);
        }
        else{}

    }

}
