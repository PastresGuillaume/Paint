package graphics.ui;

import graphics.Constantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.KeyStore;

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
        JPanel container = new JPanel();
        JScrollPane scrollPane = new JScrollPane(tab);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(15, 100));
        container.add(tab);
        container.add(scrollPane);
        this.tabs.addTab(tabName, container);
    }

    public void setCommandHelpMenu(){
        JPanel tab = new JPanel();
        String text = """
                Keys Shortcut
                Select All: Ctrl-A
                Supp: Backspace
                """;
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        tab.add(textArea);
        this.newTab("Command", tab);
    }

    public void setMenuHelpMenu(){
        JPanel tab = new JPanel();

        String text = """
                File:
                Save: save current file to specified location
                Import: load file specified
                """;
        JTextArea textArea = new JTextArea(text);
        text = """
                Settings:
                DarkMode: Change UI color to a dark theme
                """;
        textArea.setEditable(false);
        textArea.setOpaque(false);
        tab.add(textArea);
        JTextArea test = new JTextArea(text);
        test.setEditable(false);
        test.setOpaque(false);
        tab.add(test);
        this.newTab("Menu", tab);
    }

}
