package graphics.jeux.NoPaintNoGame.Objet;

import graphics.Constantes;

import javax.swing.*;

public class Piece extends Decor{

    public Piece(int x, int y) {
        super(198, 217, x, y);
        super.icoDecor = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"piece.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }


    //getter
}
