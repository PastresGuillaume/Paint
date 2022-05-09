package graphics.ui;

import graphics.Constantes;

import javax.swing.*;
import java.awt.*;

public class HelpMenu extends JFrame {
    private final JFrame helpMenu;
    private final JTabbedPane tabs;

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

    public void toggleDisplayMenu(){
        this.helpMenu.setSize(Constantes.WINDOW_DIMENSIONS.width*3/4, Constantes.WINDOW_DIMENSIONS.height*3/4);
        int x = (Constantes.WINDOW_DIMENSIONS.width - this.helpMenu.getSize().width)/2;
        int y = (Constantes.WINDOW_DIMENSIONS.height - this.helpMenu.getSize().height)/2;
        this.helpMenu.setLocation(x, y);
        this.tabs.setPreferredSize(new Dimension((int)(this.helpMenu.getWidth()*0.9), (int)(this.helpMenu.getHeight()*0.85)));
        this.helpMenu.setVisible(true);
    }

    public void newTab(String tabName, JPanel tab){
        JScrollPane scrollPane = new JScrollPane(tab);
        this.tabs.addTab(tabName, scrollPane);
    }

    public JTextArea newTexArea(String text){
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        return textArea;
    }

    public void setCommandHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                Keys Shortcut
                Select All: Ctrl-A
                Supp: Backspace
                """;
        tab.add(newTexArea(text));
        this.newTab("Command", tab);
    }

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

    public void setToolBarHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                To complete
                """;
        tab.add(newTexArea(text));
        this.newTab("ToolBar", tab);
    }

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
