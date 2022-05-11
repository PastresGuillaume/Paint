package graphics.ui.controllers;

import graphics.Constantes;
import graphics.formes.GameCalque;
import graphics.formes.Shape;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.View.ModelView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Définition du controller lié aux jeux.
 */
public class GameController extends AbstractController{
    /**
     * Le calque auquel est rattaché le jeu.
     */
    private GameCalque calqueJeu;

    /**
     * Constructeur.
     *
     * @param calqueJeu Le calque auquel est rattaché le jeu.
     */
    public GameController(GameCalque calqueJeu) {
        super(calqueJeu.getContent());
        this.calqueJeu = calqueJeu;
    }

    /**
     * Change la vue du controller.
     *
     * @param view view souhaitée
     */
    @Override
    public void setView(ModelView view) {
        super.setView(view);
    }

    /**
     * Change le modèle.
     *
     * @param model modèle souhaitée
     */
    @Override
    public void setModel(Shape model) {
        super.setModel(model);
    }

    /**
     * Change le modèle.
     *
     * @param calqueJeu Calque souhaité comme model.
     */
    public void setModel(GameCalque calqueJeu){
        this.calqueJeu = calqueJeu;
        this.setModel(this.calqueJeu.getContent());
    }

    /**
     * Renvoie la vue actuel.
     *
     * @return Le modelView actuel.
     */
    @Override
    public ModelView getView() {
        return super.getView();
    }

    /**
     * Renvoie le modèle actuel.
     *
     * @return Le modèle actuel.
     */
    @Override
    public Shape getModel() {
        return super.getModel();
    }

    /**
     * Renvoie le jeu actuel.
     *
     * @return Le jeu actuel.
     */
    public Game getGame(){
        return this.calqueJeu.getGame();
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie.
     *
     * @param e Événement souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default-> {break;}
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur relache la souris.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {
                int x, y;
                if (e.getX() < 10) {
                    break;
                }
                if (e.getX() < 110) {
                    x = 0;
                } else if (e.getX() < 120) {
                    break;
                } else if (e.getX() < 220) {
                    x = 1;
                } else if (e.getX() < 230) {
                    break;
                } else if (e.getX() < 330) {
                    x = 2;
                } else {
                    break;
                }
                if (e.getY() < 10) {
                    break;
                }
                if (e.getY() < 110) {
                    y = 0;
                } else if (e.getY() < 120) {
                    break;
                } else if (e.getY() < 220) {
                    y = 1;
                } else if (e.getY() < 230) {
                    break;
                } else if (e.getY() < 330) {
                    y = 2;
                } else {
                    break;
                }
                ((MortPion) this.calqueJeu.getGame()).play(x, y);
                break;
            }
            default -> {
                break;
            }
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur clique.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {
                int x, y;
                if (e.getX() < 10) {
                    break;
                }
                if (e.getX() < 110) {
                    x = 0;
                } else if (e.getX() < 120) {
                    break;
                } else if (e.getX() < 220) {
                    x = 1;
                } else if (e.getX() < 230) {
                    break;
                } else if (e.getX() < 30) {
                    x = 2;
                } else {
                    break;
                }
                if (e.getY() < 10) {
                    break;
                }
                if (e.getY() < 110) {
                    y = 0;
                } else if (e.getY() < 120) {
                    break;
                } else if (e.getY() < 220) {
                    y = 1;
                } else if (e.getY() < 230) {
                    break;
                } else if (e.getY() < 30) {
                    y = 2;
                } else {
                    break;
                }
                ((MortPion) this.calqueJeu.getGame()).play(x, y);
                break;
            }
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque la souris entre dans l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque la souris sort de l'écran.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque la souris bouge.
     *
     * @param evt Événement souris.
     */
    @Override
    public void mouseMoved(MouseEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur fait un cliqué glissé.
     *
     * @param evt Événement souris.
     */
    @Override
    public void mouseDragged(MouseEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur clique sur une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyTyped(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie sur une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyPressed(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur relache une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyReleased(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION -> {break;}
            default -> {break;}
        }
    }
}
