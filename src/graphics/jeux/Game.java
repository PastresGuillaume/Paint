package graphics.jeux;

import graphics.ui.Visitor.ShapeVisitor;

public interface Game {
    int getId();

    boolean isWin();

    boolean isNull();

    int whoWin();
//    0 -> pas de joueur
//    1 -> joueur 1
//    2 -> joueur 2

    public void accept(ShapeVisitor visitor);
}
