package graphics.ui;

import graphics.Constantes;
import graphics.formes.Calque;
import graphics.formes.GameCalque;
import graphics.formes.SModel;
import graphics.ui.View.ModelView;
import graphics.ui.controllers.GameController;
import graphics.ui.controllers.ShapesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Classe construisant le menu gérant les calques
 */
public class CalqueToolBar extends  AbstractBar{

    /**
     *view associée
     */
    private ModelView view;
    /**
     *la JToolBar associée
     */
    private JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
    /**
     *HashMap regroupant tous les boutons du menu avec comme clé le calque associé et leur fonction
     */
    private HashMap<String,JComponent> buttons = new HashMap<>();

    /**
     * Constructeur
     *
     * @param view view utilisée
     */
    public CalqueToolBar(ModelView view){
        this.view = view;
    }

    /**
     * Renvoie la view
     * @return renvoie la view
     */
    public ModelView getView(){return this.view;}

    /**
     * Renvoie la HashMap composé de tous les boutons du menu
     * @return Renvoie la HashMap composé de tous les boutons du menu
     */
    public HashMap<String, JComponent> getButtons() {return buttons;}

    /**
     * Construit la JToolBar
     * @return La JToolBar construite
     * @throws IOException L'actualisation des icons peut bug.
     */
    public JToolBar createToolBar() throws IOException {
        SModel sModel = (SModel) this.view.getModel();

        JButton addBtn = new JButton(new ImageIcon(Constantes.PATH_IMAGES + "add.png"));
        addBtn.setToolTipText("New Calque");
        addBtn.addActionListener(e -> {
            Calque calque = new Calque();
            sModel.addCalque(calque);
            addButtonCalque(calque,sModel,toolBar);
            try {
                this.view.updateIconCalqueBar(calque);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.buttons.put("add",addBtn);
        toolBar.add(addBtn);

        toolBar.addSeparator();

        for(Calque calque : sModel.getCalques()) {
            addButtonCalque(calque,sModel,toolBar);
            this.view.updateIconCalqueBar(calque);
        }

        toolBar.setOpaque(true);
        toolBar.setMargin(new Insets(10,0,10,0));
        toolBar.setFloatable(false);

        return toolBar;
    }

    /**
     * Rajoute le bouton de sélection et la checkbox de choix si on dessine le calque
     * @param calque le calque impacté par ces boutons
     * @param sModel le modèle
     * @param toolBar la toolbar où sont les boutons
     */
    private void addButtonCalque(Calque calque, SModel sModel, JToolBar toolBar){
        JButton btnNew = new JButton();
        btnNew.setBackground(Constantes.BACKGROUND_COLOR);
        btnNew.setForeground(Constantes.TEXTMENU_COLOR);
        btnNew.setToolTipText("Select " + calque.getName());
        btnNew.addActionListener(e -> {
            sModel.setUse(calque);
            if (calque.isGame()){
                this.getView().setController(new GameController(((GameCalque) calque)));
            }
            else {
                this.getView().setController(new ShapesController(((SModel) this.view.getModel()).getCalqueUse(), this.view));
            }
            ((SModel) this.view.getModel()).unselect();
            this.view.invalidate();
        });
        this.buttons.put(calque.getName() + Constantes.IS_USED_CALQUE,btnNew);
        toolBar.add(btnNew);

        JCheckBox checkBox = new JCheckBox(calque.getName(), true);
        checkBox.setToolTipText("Want to see " + calque.getName() + " ?");
        checkBox.setBackground(Constantes.BACKGROUND_COLOR);
        checkBox.setForeground(Constantes.TEXTMENU_COLOR);
        checkBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
                sModel.setPaint(calque);
            else
                sModel.setNotPaint(calque);
            view.invalidate();
        });
        this.buttons.put(calque.getName() + Constantes.IS_PAINTED_CALQUE,checkBox);
        toolBar.add(checkBox);

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteCalque = new JMenuItem("delete " + calque.getName());
        JMenuItem renameCalque = new JMenuItem("rename " + calque.getName());

        jPopupMenu.setBackground(Constantes.BACKGROUND_COLOR);
        jPopupMenu.setForeground(Constantes.TEXTMENU_COLOR);
        deleteCalque.setBackground(Constantes.BACKGROUND_COLOR);
        deleteCalque.setForeground(Constantes.TEXTMENU_COLOR);
        renameCalque.setBackground(Constantes.BACKGROUND_COLOR);
        renameCalque.setForeground(Constantes.TEXTMENU_COLOR);

        deleteCalque.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null, "Do you want to delete" + calque.getName() + "?");
            if(input == 0) {
                File dir = new File(Constantes.PATH_IMAGES + "icons");
                for (File file : Objects.requireNonNull(dir.listFiles()))
                    if (file.getName().equals(calque.getName() + Constantes.IS_USED_CALQUE + ".png"))
                        file.delete();
                ((SModel) view.getModel()).delCalque(calque);
                toolBar.remove(btnNew);
                toolBar.remove(checkBox);
                toolBar.updateUI();
                view.invalidate();
            }
        });
        jPopupMenu.add(deleteCalque);

        renameCalque.addActionListener(e -> {
            JFrame frame = new JFrame("Input the name of the calque");

            JTextField text = new JTextField();
            text.setBounds(20,40,200,28);

            text.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        String outcome = text.getText();
                        File dir = new File(Constantes.PATH_IMAGES + "icons");
                        for (File file : Objects.requireNonNull(dir.listFiles()))
                            if (file.getName().equals(calque.getName() + Constantes.IS_USED_CALQUE + ".png"))
                                file.delete();
                        ((CalqueToolBar) view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).getButtons().remove(calque.getName() + Constantes.IS_USED_CALQUE);
                        ((CalqueToolBar) view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).getButtons().remove(calque.getName() + Constantes.IS_PAINTED_CALQUE);
                        calque.setName(outcome);
                        btnNew.setToolTipText("Select " + calque.getName());
                        checkBox.setToolTipText("Want to see " + calque.getName() + " ?");
                        checkBox.setText(calque.getName());
                        ((CalqueToolBar) view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).getButtons().put(calque.getName() + Constantes.IS_USED_CALQUE,btnNew);
                        ((CalqueToolBar) view.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).getButtons().put(calque.getName() + Constantes.IS_PAINTED_CALQUE,checkBox);
                        deleteCalque.setText("delete " + outcome);
                        renameCalque.setText("rename " + outcome);
                        toolBar.updateUI();
                        frame.setVisible(false);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });

            frame.add(text);
            frame.setSize(250,150);
            frame.setLayout(null);
            frame.setVisible(true);
        });
        jPopupMenu.add(renameCalque);

        btnNew.setComponentPopupMenu(jPopupMenu);
    }

    /**
     * Change la couleur du menu
     */
    @Override
    public void changeColor() {
        this.toolBar.setBackground(Constantes.MENUBAR_COLOR);
        for(JComponent button : this.buttons.values()) {
            button.setBackground(Constantes.MENUBAR_COLOR);
            button.setForeground(Constantes.TEXTMENU_COLOR);
            if(button.getComponentPopupMenu() != null) {
                for(MenuElement item : button.getComponentPopupMenu().getSubElements()) {
                    ((JMenuItem) item).setBackground(Constantes.MENUBAR_COLOR);
                    ((JMenuItem) item).setForeground(Constantes.TEXTMENU_COLOR);
                }
            }
        }
    }

    /**
     * Reconstruit la toolbar actuelle
     */
    public void refresh()  {
        try {
            this.toolBar.removeAll();
            this.toolBar = this.createToolBar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
