package graphics.formes;

import graphics.Constantes;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.Visitor.ShapeVisitor;

public class GameCalque extends Calque {
    private Game game;

    public GameCalque(int idJeu) {
        super();
        switch (idJeu) {
            case Constantes.GAME_ID_MORT_PION -> this.game = new MortPion();
            default -> {}
        }
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        this.game.accept(visitor);
    }

    @Override
    public boolean isGame() {
        return true;
    }
}
