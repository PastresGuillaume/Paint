package graphics.ui;

import graphics.ui.View.ModelView;

import javax.swing.*;

public class PopupHelpMenu extends JFrame{
    private final JPopupMenu popupMenu;
    private boolean showMenu = false;

    PopupHelpMenu(){
        this.popupMenu = new JPopupMenu("Test");
        JLabel l = new JLabel("message de test");
        this.popupMenu.add(l);
        this.popupMenu.setPopupSize(300, 50);
        this.popupMenu.setVisible(false);
    }

    public void toggleDisplayMenu(){
        this.showMenu = !this.showMenu;
        this.popupMenu.setVisible(this.showMenu);
        //TODO à adapter selon la taille de l'écran
        this.popupMenu.setLocation(150, 150);
    }
}
