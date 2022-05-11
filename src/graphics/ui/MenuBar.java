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

/**
 * JMenuBar Class
 */
public class MenuBar extends AbstractBar {
    /**
     * Vue du modèle.
     */
    private final ModelView view;
    /**
     * Management des sauvegardes.
     */
    private final SaveHandler saveHandler;
    /**
     * Barre de menu.
     */
    private  JMenuBar menuBar;
    /**
     * Choix de fichier.
     */
    private final JFileChooser fileChooser;
    /**
     * Menu d'aide.
     */
    private final HelpMenu helpMenu;
    /**
     * Est-ce que l'on est en darkMode ?
     */
    private boolean isDarkMode = false;

    /**
     * Instance the MenuBar: position, fileHandler, fileChooser and helpMenu
     * @param view to set
     */
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

    /**
     *
     * @return view
     */
    public ModelView getView(){
        return this.view;
    }

    /**
     * Création d'une barre de menu.
     *
     * @return La barre de menu.
     */
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

    /**
     * Inverse l'affichage du menu.
     */
    public void toggleDisplayHelpMenu(){
        this.helpMenu.toggleDisplayMenu();
    }

    /**
     * Change colors used for displaying menus
     */
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

    /**
     * Save the current state of the SModel in the specified place with a JFileChooser dialog
     */
    public void save(){
        Object object = (view.getModel());
        int selectedFile = this.fileChooser.showSaveDialog(view);
        if (selectedFile == JFileChooser.APPROVE_OPTION){
            File file = new File(this.fileChooser.getSelectedFile() + Constantes.FILE_EXTENSION);
            this.saveHandler.saveObject(object, file);
        }
    }

    /**
     * Load the save chosen with a JFileChooser dialog
     */
    public void load(){
        int operationResult = this.fileChooser.showOpenDialog(view);
        if (operationResult == JFileChooser.APPROVE_OPTION){
            Object object = this.saveHandler.loadObject(this.fileChooser.getSelectedFile());
            this.view.setModel(object);
            ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
        }
    }

    /**
     * Resize the given icon to the right size
     * @param icon to resize
     * @return the icon resized
     */
    private static Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * change background and foreground colors of items in the menuBar
     */
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

    /**
     * Actions realised after clicking on the matching button:
     * Create a new GameCalque for the specified game and set it for playing
     * @param gameID game id
     */
    public void selectGame(int gameID){
        GameCalque calqueGame = new GameCalque(gameID);
        ((SModel)this.view.getModel()).addCalque(calqueGame);
        ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
        ((SModel)this.view.getModel()).unselect();
        ((SModel) this.view.getModel()).setUse(calqueGame);
        ((ModelController) this.view.getController()).setController(new GameController(calqueGame));
    }

    /**
     * Create a JMenuItem for the specified game
     * @param gameName name of the game to display
     * @param gameID id of the game
     * @return JMenuItem containing the actionListener to start the specified game
     */
    public JMenuItem createGameItem(String gameName, int gameID){
        JMenuItem gameItem = new JMenuItem(gameName);
        gameItem.addActionListener(e -> this.selectGame(gameID));
        return gameItem;
    }

    /**
     * Erase all calque and set the count back to 0
     */
    public void clear(){
        Calque.nb_Calque = 0;
        this.view.setModel(new SModel());
        ((CalqueToolBar)this.view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).refresh();
    }

    /**
     * Ajoute le jeu NPNG au menu
     *
     * @return Un item de menu.
     */
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
