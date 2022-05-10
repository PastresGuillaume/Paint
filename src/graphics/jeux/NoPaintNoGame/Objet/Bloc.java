package graphics.jeux.NoPaintNoGame.Objet;

import graphics.Constantes;

import javax.swing.*;


public class Bloc extends Decor{
    //private Image imgBloc  ;
    //private ImageIcon icoBloc ;

    public Bloc(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y);
        super.icoDecor = new ImageIcon(Constantes.PATH_NO_PAINT_NO_GAME+"bloc.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }

    /*public Image getImgBloc() {
        return imgBloc;
    }*/
}
