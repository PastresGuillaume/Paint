package graphics.jeux.NoPaintNoGame.src.Objet;

import javax.swing.*;


public class TuyauRouge extends Decor{
    //private Image imgTuyauRouge  ;
    //private ImageIcon icoTuyauRouge ;

    public TuyauRouge(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y);
        super.icoDecor = new ImageIcon("src/image/tuyauRouge.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }

    /*public Image getImgTuyauRouge() {
        return imgTuyauRouge;
    }*/
}
