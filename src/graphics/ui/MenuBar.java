package graphics.ui;

import graphics.Constantes;
import graphics.formes.*;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame {
    private ModelView view;
    private SaveHandler saveHandler;
    private  JMenuBar menuBar;


    public MenuBar(ModelView view){
        super( "Menu Bar" );
        this.view = view;
        this.setBounds(0, 0, 500, 10);
        this.saveHandler = new SaveHandler();
    }

    public ModelView getView(){
        return this.view;
    }

    public JMenuBar createMenuBar(){
        this.menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        //TODO à terminer la save en donnant directement la view
        saveMenu.addActionListener(e -> this.save(((SModel)view.getModel())));
        fileMenu.add(saveMenu);

        JMenuItem importMenu = new JMenuItem("Import");
        importMenu.addActionListener(e -> this.load());
        fileMenu.add(importMenu);
        menuBar.add(fileMenu);

        JMenu settingMenu = new JMenu("Settings");
        JMenuItem modeItem = new JMenuItem("DarkMode");
        modeItem.addActionListener(e -> this.toggleDarkMode());
        settingMenu.add(modeItem);
        menuBar.add(settingMenu);
        return this.menuBar;
    }

    public void toggleDarkMode(){
        this.view.setBackground(Constantes.DARKMODE_BACKGROUND_COLOR);
        this.menuBar.setBackground(Constantes.DARKMODE_MENUBAR_COLOR);
        for (int i =0; i<this.menuBar.getMenuCount(); i++){
            this.menuBar.getMenu(i).setForeground(Constantes.DARKMODE_TEXTMENU_COLOR);
            for (int j=0; j<this.menuBar.getMenu(i).getItemCount(); j++){
                this.menuBar.getMenu(i).getItem(j).setForeground(Constantes.DARKMODE_TEXTMENU_COLOR);
            }
        }
    }

    public void save(Object object){
        this.saveHandler.saveObject(object);
    }

    public void load(){
        Object object = this.saveHandler.loadObject();
        this.view.setModel(object);

    }
}
