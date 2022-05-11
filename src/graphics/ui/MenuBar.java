package graphics.ui;

import graphics.Constantes;
import graphics.formes.Calque;
import graphics.formes.GameCalque;
import graphics.formes.SModel;
import graphics.jeux.NoPaintNoGame.jeu.LaunchNPNG;
import graphics.ui.View.ModelView;
import graphics.ui.controllers.GameController;
import graphics.ui.controllers.ModelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuBar extends AbstractBar {
    private final ModelView view;
    private final SaveHandler saveHandler;
    private  JMenuBar menuBar;
    private final JFileChooser fileChooser;
    private final HelpMenu helpMenu;
    private boolean isDarkMode = false;


    public MenuBar(ModelView view){
        this.view = view;
        this.setBounds(0, 0, 500, 10);
        this.saveHandler = new SaveHandler();
        this.fileChooser = new JFileChooser();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new FileExtensionFilter());
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.helpMenu = new HelpMenu();
    }

    public ModelView getView(){
        return this.view;
    }

    public JMenuBar createMenuBar(){
        this.menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.setIcon(resizeIcon(new ImageIcon("images\\save.png")));
        saveMenu.addActionListener(e -> this.save());
        fileMenu.add(saveMenu);

        JMenuItem importMenu = new JMenuItem("Import");
        importMenu.setIcon(resizeIcon(new ImageIcon("images\\import.png")));
        importMenu.addActionListener(e -> this.load());
        fileMenu.add(importMenu);
        menuBar.add(fileMenu);

        JMenu settingMenu = new JMenu("Settings");
        JMenuItem modeItem = new JMenuItem("DarkMode");
        modeItem.addActionListener(e -> this.toggleDarkMode());
        settingMenu.add(modeItem);
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> this.clear());
        settingMenu.add(clearItem);
        menuBar.add(settingMenu);

        JMenu gameMenu = new JMenu("Games");
        gameMenu.add(this.createGameItem("Morpion", Constantes.GAME_ID_MORT_PION));
        gameMenu.add(this.noPaintNoGame());
        menuBar.add(gameMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help Menu");
        helpItem.addActionListener(e -> this.toggleDisplayHelpMenu());
        helpMenu.add(helpItem);
        menuBar.add(helpMenu);

        this.menuBar.setMaximumSize(this.menuBar.getMinimumSize());

        return this.menuBar;
    }

    public void toggleDisplayHelpMenu(){
        this.helpMenu.toggleDisplayMenu();
    }

    public void toggleDarkMode(){
        this.isDarkMode = ! this.isDarkMode;
        if (this.isDarkMode){
            Constantes.MENUBAR_COLOR = Constantes.DARKMODE_MENUBAR_COLOR;
            Constantes.TEXTMENU_COLOR = Constantes.DARKMODE_TEXTMENU_COLOR;
            Constantes.BACKGROUND_COLOR = Constantes.DARKMODE_BACKGROUND_COLOR;
        }
        else{
            Constantes.MENUBAR_COLOR = Constantes.BRIGHTMODE_MENUBAR_COLOR;
            Constantes.TEXTMENU_COLOR = Constantes.BRIGHTMODE_TEXTMENU_COLOR;
            Constantes.BACKGROUND_COLOR = Constantes.BRIGHTMODE_BACKGROUND_COLOR;
        }
        for(AbstractBar menu : this.view.getMenus().values())
            menu.changeColor();
    }


    public void save(){
        Object object = (view.getModel());
        int selectedFile = this.fileChooser.showSaveDialog(view);
        if (selectedFile == JFileChooser.APPROVE_OPTION){
            File file = new File(this.fileChooser.getSelectedFile() + Constantes.FILE_EXTENSION);
            this.saveHandler.saveObject(object, file);
        }
    }

    public void load(){
        int operationResult = this.fileChooser.showOpenDialog(view);
        if (operationResult == JFileChooser.APPROVE_OPTION){
            Object object = this.saveHandler.loadObject(this.fileChooser.getSelectedFile());
            this.view.setModel(object);
            ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
        }
    }

    private static Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public void changeColor() {
        this.view.setBackground(Constantes.BACKGROUND_COLOR);
        this.menuBar.setBackground(Constantes.MENUBAR_COLOR);
        for (int i =0; i<this.menuBar.getMenuCount(); i++){
            this.menuBar.getMenu(i).setForeground(Constantes.TEXTMENU_COLOR);
            for (int j=0; j<this.menuBar.getMenu(i).getItemCount(); j++){
                this.menuBar.getMenu(i).getItem(j).setBackground(Constantes.MENUBAR_COLOR);
                this.menuBar.getMenu(i).getItem(j).setForeground(Constantes.TEXTMENU_COLOR);
            }
        }
    }

    public void selectGame(int gameID){
        GameCalque calqueGame = new GameCalque(gameID);
        ((SModel)this.view.getModel()).addCalque(calqueGame);
        ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
        ((SModel)this.view.getModel()).unselect();
        ((SModel) this.view.getModel()).setUse(calqueGame);
        ((ModelController) this.view.getController()).setController(new GameController(calqueGame));
    }

    public JMenuItem createGameItem(String gameName, int gameID){
        JMenuItem gameItem = new JMenuItem(gameName);
        gameItem.addActionListener(e -> this.selectGame(gameID));
        return gameItem;
    }

    public void clear(){
        Calque.nb_Calque = 0;
        this.view.setModel(new SModel());
        ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
    }

    public JMenuItem noPaintNoGame(){
        JMenuItem gameItem = new JMenuItem("NoPaintNoGame");
        gameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LaunchNPNG launchNPNG = new LaunchNPNG();
                launchNPNG.noPaintNoGame();
            }
        });
        return gameItem;
    }
}
