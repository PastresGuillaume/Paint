package graphics.jeux.NoPaintNoGame.Objet;

import graphics.Constantes;

import javax.swing.*;


public class TuyauRouge extends Decor{
    //private Image imgTuyauRouge  ;
    //private ImageIcon icoTuyauRouge ;

    public TuyauRouge(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y);
        super.icoDecor = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"tuyauRouge.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }

    /*public Image getImgTuyauRouge() {
        return imgTuyauRouge;
    }*/
}
