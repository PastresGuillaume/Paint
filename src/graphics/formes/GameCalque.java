package graphics.formes;

import graphics.Constantes;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.Visitor.ShapeVisitor;

/**
 * Définition du calque relatif au jeu.
 */
public class GameCalque extends Calque {
    /**
     * Le jeu lié au calque.
     */
    private Game game;

    /**
     * Constructeur
     *
     * @param idJeu Identifiant du jeu.
     */
    public GameCalque(int idJeu) {
        super();
        switch (idJeu) {
            case Constantes.GAME_ID_MORT_PION -> this.game = new MortPion();
            default -> {}
        }
    }

    /**
     * Renvoie le jeu lié au calque.
     *
     * @return Le jeu lié au calque.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Opération d'affichage.
     *
     * @param visitor Le dessinateur qui sait dessiner un calque de jeu.
     */
    @Override
    public void accept(ShapeVisitor visitor) {
        this.game.accept(visitor);
    }

    /**
     * Dit que ce calque est un calque de jeu.
     *
     * @return Vrai, car ce calque est un calque lié à un jeu.
     */
    @Override
    public boolean isGame() {
        return true;
    }
}
