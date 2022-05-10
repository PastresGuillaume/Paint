package graphics.jeux.NoPaintNoGame.Objet;

import graphics.Constantes;

import javax.swing.*;

public class Piece extends Decor{

    public Piece(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y);
        super.icoDecor = new ImageIcon("src/image/piece.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }



    //getter
}
