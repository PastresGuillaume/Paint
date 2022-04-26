package graphics.formes;

import graphics.Constantes;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.Visitor.GameDraftman;
import graphics.ui.Visitor.ShapeVisitor;


public class GameCalque extends Calque {
    private Game game;

    public GameCalque(int idJeu) {
        super();
        switch (idJeu){
            case Constantes.GAME_ID_MORT_PION:
                game = new MortPion();
                break;
            default:
                break;
        }
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        switch (this.game.getId()){
            case Constantes.GAME_ID_MORT_PION:
                ((GameDraftman) visitor).visitMortPion(((MortPion) this.getGame()));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isGame() {
        return true;
    }
}
