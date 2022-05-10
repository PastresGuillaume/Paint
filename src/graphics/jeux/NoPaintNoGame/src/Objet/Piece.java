package graphics.jeux.NoPaintNoGame.src.Objet;

import javax.swing.*;

public class Piece extends Decor{

    public Piece(int x, int y) {
        super(198, 217, x, y);
        super.icoDecor = new ImageIcon("src/image/piece.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }


    //getter
}
