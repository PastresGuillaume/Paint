package graphics.ui.controllers;

import graphics.Constantes;
import graphics.formes.CalqueGame;
import graphics.formes.Shape;
import graphics.jeux.Game;
import graphics.jeux.MortPion;
import graphics.ui.View.ModelView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameController extends AbstractController{
    private CalqueGame calqueJeu;

    public GameController(CalqueGame calqueJeu) {
        super(calqueJeu.getContent());
        this.calqueJeu = calqueJeu;
    }

    @Override
    public void setView(ModelView view) {
        super.setView(view);
    }

    @Override
    public void setModel(Shape model) {
        super.setModel(model);
    }

    public void setModel(CalqueGame calqueJeu){
        this.calqueJeu = calqueJeu;
        this.setModel(this.calqueJeu.getContent());
    }

    @Override
    public ModelView getView() {
        return super.getView();
    }

    @Override
    public Shape getModel() {
        return super.getModel();
    }

    public Game getGame(){
        return this.calqueJeu.getGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION:
                int x, y;
                if (e.getX()<10){
                    break;
                }
                if (e.getX()<110){
                    x = 0;
                }
                else if (e.getX()<120) {
                    break;
                }
                else if (e.getX()<220){
                    x = 1;
                }
                else if (e.getX()<230) {
                    break;
                }
                else if (e.getX()<330){
                    x = 2;
                }
                else {
                    break;
                }
                if (e.getY()<10){
                    break;
                }
                if (e.getY()<110){
                    y = 0;
                }
                else if (e.getY()<120) {
                    break;
                }
                else if (e.getY()<220){
                    y = 1;
                }
                else if (e.getY()<230) {
                    break;
                }
                else if (e.getY()<330){
                    y = 2;
                }
                else {
                    break;
                }
                ((MortPion) this.calqueJeu.getGame()).play(x, y);
            default: break;
        }
        int x = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION:
                int x, y;
                if (e.getX()<10){
                    break;
                }
                if (e.getX()<110){
                    x = 0;
                }
                else if (e.getX()<120) {
                    break;
                }
                else if (e.getX()<220){
                    x = 1;
                }
                else if (e.getX()<230) {
                    break;
                }
                else if (e.getX()<30){
                    x = 2;
                }
                else {
                    break;
                }
                if (e.getY()<10){
                    break;
                }
                if (e.getY()<110){
                    y = 0;
                }
                else if (e.getY()<120) {
                    break;
                }
                else if (e.getY()<220){
                    y = 1;
                }
                else if (e.getY()<230) {
                    break;
                }
                else if (e.getY()<30){
                    y = 2;
                }
                else {
                    break;
                }
                ((MortPion) this.calqueJeu.getGame()).play(x, y);
            default: break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        switch (this.calqueJeu.getGame().getId()){
            case Constantes.GAME_ID_MORT_PION: break;
            default: break;
        }
    }
}
