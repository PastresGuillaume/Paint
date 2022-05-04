package graphics.ui;

import graphics.Constantes;
import graphics.ui.View.ModelView;
import javax.swing.*;
import java.awt.*;
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
        menuBar.add(settingMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Commands");
        helpItem.addActionListener(e -> this.toggleDisplayHelpMenu());
        helpMenu.add(helpItem);
        menuBar.add(helpMenu);

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
        int selectedFile = this.fileChooser.showOpenDialog(view);
        if (selectedFile == JFileChooser.APPROVE_OPTION){
            Object object = this.saveHandler.loadObject(this.fileChooser.getSelectedFile());
            this.view.setModel(object);
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
}
