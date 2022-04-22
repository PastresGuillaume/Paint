package graphics.formes;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.Visitor.ShapeVisitor;

import java.awt.*;

public class CalqueGame extends Calque {
    private Game game;

    public CalqueGame(int idJeu) {
        super();
        if (idJeu == Constantes.GAME_ID_MORT_PION) {
            game = new MortPion();
        }
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        this.convert();
        super.accept(visitor);
    }

    public void convert(){
        SCollection content = new SCollection();
        content.addAttributes(this.getContent().getAttributes(Constantes.SELECTION_ATTRIBUTE));
        content.addAttributes(this.getContent().getAttributes(Constantes.COLOR_ATTRIBUTE));

        switch (this.game.getId()){
            case Constantes.GAME_ID_MORT_PION:
                SRectangle horizHaut = new SRectangle(new Point(10, 110), 320, 10);
                horizHaut.addAttributes(Constantes.PLATEAU_MORT_PION_COLOR);
                horizHaut.addAttributes(Constantes.PLATEAU_MORT_PION_SELECTION);

                SRectangle horizBas = new SRectangle(new Point(10, 220), 320, 10);
                horizBas.addAttributes(Constantes.PLATEAU_MORT_PION_COLOR);
                horizBas.addAttributes(Constantes.PLATEAU_MORT_PION_SELECTION);

                SRectangle vertgauche = new SRectangle(new Point(110, 10), 10, 320);
                vertgauche.addAttributes(Constantes.PLATEAU_MORT_PION_COLOR);
                vertgauche.addAttributes(Constantes.PLATEAU_MORT_PION_SELECTION);

                SRectangle vertDroit = new SRectangle(new Point(220, 10), 10, 320);
                vertDroit.addAttributes(Constantes.PLATEAU_MORT_PION_COLOR);
                vertDroit.addAttributes(Constantes.PLATEAU_MORT_PION_SELECTION);

                content.add(horizHaut);
                content.add(horizBas);
                content.add(vertgauche);
                content.add(vertDroit);

                int[][] board = ((MortPion) this.getGame()).getBoard();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == 1) {
                            SRectangle r1 = new SRectangle(new Point(10 + 110 * i + 5, 10 + 110 * j - 5 + 50), 90, 10);
                            r1.addAttributes(Constantes.J1_MORT_PION_COLOR);
                            r1.addAttributes(Constantes.J1_MORT_PION_SELECTION);
                            r1.addAttributes(new RotationAttributes(Math.toRadians(-45)));
                            content.add(r1);

                            SRectangle r2 = new SRectangle(new Point(10 + 110 * i + 5, 10 + 110 * j - 5 + 50), 90, 10);
                            r2.addAttributes(Constantes.J1_MORT_PION_COLOR);
                            r2.addAttributes(Constantes.J1_MORT_PION_SELECTION);
                            r2.addAttributes(new RotationAttributes(Math.toRadians(45)));
                            content.add(r2);
                        } else if (board[i][j] == 2) {
                            SRectangle r3 = new SRectangle(new Point(10 + 110 * i + 5, 10 + 110 * j - 5 + 50), 90, 10);
                            r3.addAttributes(Constantes.J2_MORT_PION_COLOR);
                            r3.addAttributes(Constantes.J2_MORT_PION_SELECTION);
                            r3.addAttributes(new RotationAttributes(Math.toRadians(-45)));
                            content.add(r3);

                            SRectangle r4 = new SRectangle(new Point(10 + 110 * i + 5, 10 + 110 * j - 5 + 50), 90, 10);
                            r4.addAttributes(Constantes.J2_MORT_PION_COLOR);
                            r4.addAttributes(Constantes.J2_MORT_PION_SELECTION);
                            r4.addAttributes(new RotationAttributes(Math.toRadians(45)));
                            content.add(r4);
                        }
                    }
                }
                if (this.game.isNull()){
                    SText texte = new SText(new Point(350, 175), "Partie nulle");
                    texte.addAttributes(Constantes.MORT_PION_NULL_COLOR);
                    texte.addAttributes(Constantes.MORT_PION_NULL_SELECTION);
                    texte.addAttributes(new FontAttributes());
                    content.add(texte);
                }
                else {
                    if (this.game.whoWin() == 1) {
                        SText texte = new SText(new Point(350, 175), "J1 gagne la partie");
                        texte.addAttributes(new ColorAttributes(true, true, Constantes.J1_MORT_PION_COLOR.filledColor, Constantes.J1_MORT_PION_COLOR.strokedColor));
                        texte.addAttributes(new SelectionAttributes());
                        texte.addAttributes(new FontAttributes());
                        content.add(texte);
                    } else if (this.game.whoWin() == 2) {
                        SText texte = new SText(new Point(350, 175), "J2 gagne la partie");
                        texte.addAttributes(new ColorAttributes(true, true, Constantes.J2_MORT_PION_COLOR.filledColor, Constantes.J2_MORT_PION_COLOR.strokedColor));
                        texte.addAttributes(new SelectionAttributes());
                        texte.addAttributes(new FontAttributes());
                        content.add(texte);
                    } else {
                        if (((MortPion) this.game).getCouleur() == 1) {
                            SText texte = new SText(new Point(350, 175), "Tour de J1");
                            texte.addAttributes(new ColorAttributes(true, true, Constantes.J1_MORT_PION_COLOR.filledColor, Constantes.J1_MORT_PION_COLOR.strokedColor));
                            texte.addAttributes(new SelectionAttributes());
                            texte.addAttributes(new FontAttributes());
                            content.add(texte);
                        } else {
                            SText texte = new SText(new Point(350, 175), "Tour de J2");
                            texte.addAttributes(new ColorAttributes(true, true, Constantes.J2_MORT_PION_COLOR.filledColor, Constantes.J2_MORT_PION_COLOR.strokedColor));
                            texte.addAttributes(new SelectionAttributes());
                            texte.addAttributes(new FontAttributes());
                            content.add(texte);
                        }
                    }
                }
                break;
            default:
                break;
        }
        this.setContent(content);
    }

    @Override
    public boolean isGame() {
        return true;
    }
}
