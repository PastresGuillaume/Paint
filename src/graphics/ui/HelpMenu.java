package graphics.ui;

import graphics.Constantes;

import javax.swing.*;
import java.awt.*;

/**
 * Help menu to display tips on how to use our project
 */
public class HelpMenu extends JFrame {
    /**
     * Menu.
     */
    private final JFrame helpMenu;
    /**
     * Boite de dialogue.
     */
    private final JTabbedPane tabs;

    /**
     * Constructeur.
     */
    HelpMenu(){
        this.helpMenu = new JFrame("Help Menu");
        this.helpMenu.setVisible(false);
        JPanel panel = new JPanel();
        this.tabs = new JTabbedPane(SwingConstants.TOP);
        tabs.setOpaque(true);
        panel.add(this.tabs);
        this.helpMenu.setContentPane(panel);
        this.setCommandHelpMenu();
        this.setMenuHelpMenu();
        this.setToolBarHelpMenu();
        this.setCreditsHelpMenu();
        this.helpMenu.setResizable(false);
    }

    /**
     * Display the menu in the center of the current frame
     */
    public void toggleDisplayMenu(){
        this.helpMenu.setSize(Constantes.WINDOW_DIMENSIONS.width*3/4, Constantes.WINDOW_DIMENSIONS.height*3/4);
        int x = (Constantes.WINDOW_DIMENSIONS.width - this.helpMenu.getSize().width)/2;
        int y = (Constantes.WINDOW_DIMENSIONS.height - this.helpMenu.getSize().height)/2;
        this.helpMenu.setLocation(x, y);
        this.tabs.setPreferredSize(new Dimension((int)(this.helpMenu.getWidth()*0.9), (int)(this.helpMenu.getHeight()*0.85)));
        this.helpMenu.setVisible(true);
    }

    /**
     * Add the given tab to the menu with a scrollPane
     * @param tabName name of the new tab
     * @param tab new tab to add
     */
    public void newTab(String tabName, JPanel tab){
        JScrollPane scrollPane = new JScrollPane(tab);
        this.tabs.addTab(tabName, scrollPane);
    }

    /**
     * Create a JTextArea containing the specified text
     * @param text text to add
     * @return JTextArea containing the text
     */
    public JTextArea newTexArea(String text){
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        return textArea;
    }

    /**
     * Rajoute la commande help.
     */
    public void setCommandHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                Keys Shortcut
                Select All: Ctrl-A
                Supp: Backspace
                Enable multiple Selection: Shift
                Enable Shape Resizing
                """;
        tab.add(newTexArea(text));
        this.newTab("Command", tab);
    }

    /**
     * Setter du Menu
     */
    public void setMenuHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                File:
                Save: save current file to specified location
                Import: load file specified
                """;
        tab.add(newTexArea(text));
        text = """
                Settings:
                DarkMode: Change UI color to a dark theme
                Clear: Clear actual project, all shapes and calques are erased
                """;
        tab.add(newTexArea(text));
        text = """
                Games:
                Create a new Calque with the selected game
                """;
        tab.add(newTexArea(text));
        this.newTab("Menu", tab);
    }

    /**
     * Setter de la ToolBar
     */
    public void setToolBarHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                Selector:
                Enable Shapes Selection
                """;
        tab.add(newTexArea(text));
        text = """
                Shapes Creator:
                Create a new shape
                Notice: Right-click to select filled and stroked color
                """;
        tab.add(newTexArea(text));
        this.newTab("ToolBar", tab);
    }

    /**
     * Setter des crédits
     */
    public void setCreditsHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                Project realised by:
                FONTAINE Thomas
                FAYS Matthieu
                PASTRES Guillaume
                YAGHDJIAN Marion
                """;
        tab.add(newTexArea(text));
        this.newTab("Credits", tab);
    }

}
