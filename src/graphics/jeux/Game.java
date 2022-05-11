package graphics.jeux;

import graphics.ui.Visitor.ShapeVisitor;

/**
 * Définition de l'interface Game.
 */
public interface Game {
    /**
     * Identifiant du jeu.
     *
     * @return Identifiant du jeu.
     */
    int getId();

    /**
     * Est-ce que la partie est gagnée ?
     *
     * @return Est-ce que la partie est gagnée ?
     */
    boolean isWin();

    /**
     * Est-ce que la partie est nulle ?
     *
     * @return Est-ce que la partie est nulle ?
     */
    boolean isNull();

    /**
     * Qui a gagnée ? Si aucun gagnant alors 0
     *
     * @return Une valeur entière désignant le vainqueur.
     */
    int whoWin();
//    0 -> pas de joueur
//    1 -> joueur 1
//    2 -> joueur 2

    /**
     * Fonction de dessin.
     *
     * @param visitor Dessinateur.
     */
    public void accept(ShapeVisitor visitor);
}
