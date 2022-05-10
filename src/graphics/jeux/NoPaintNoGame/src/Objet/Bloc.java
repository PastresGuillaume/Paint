package graphics.jeux.NoPaintNoGame.src.Objet;

import javax.swing.*;


public class Bloc extends Decor{
    //private Image imgBloc  ;
    //private ImageIcon icoBloc ;

    public Bloc(int largeur, int hauteur, int x, int y) {
        super(largeur, hauteur, x, y);
        super.icoDecor = new ImageIcon("src/image/bloc.jpg");
        super.imgDecor = this.icoDecor.getImage();
    }

    /*public Image getImgBloc() {
        return imgBloc;
    }*/
}
