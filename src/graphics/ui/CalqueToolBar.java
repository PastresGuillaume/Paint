package graphics.ui;

import graphics.Constantes;
import graphics.formes.Calque;
import graphics.formes.GameCalque;
import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;
import graphics.ui.controllers.AbstractController;
import graphics.ui.controllers.GameController;
import graphics.ui.controllers.ModelController;
import graphics.ui.controllers.ShapesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CalqueToolBar extends  JFrame{

    private ModelView view;
    private final Dimension dimension;

    public CalqueToolBar(ModelView view){
        super( "JToolBar for Calque" );
        this.view = view;
        this.dimension = new Dimension(25,25);
    }

    public ModelView getView(){
        return this.view;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        SModel sModel = (SModel) this.view.getModel();
        JScrollPane jScrollPane = new JScrollPane();

        JButton saveBtn = new JButton(new ImageIcon("images\\add.png"));
        saveBtn.setToolTipText("New Calque");
        saveBtn.setSize(this.dimension);
        saveBtn.addActionListener(e -> {
            Calque calque = new Calque();
            sModel.addCalque(calque);
            addButtonCalque(calque,sModel,toolBar);
        });
        toolBar.add(saveBtn);

        JButton addGameBtn = new JButton(new ImageIcon("images\\mortPion.png"));
        addGameBtn.setToolTipText("New mort pion");
        addGameBtn.setSize(this.dimension);
        addGameBtn.addActionListener(e -> {
            Calque calque = new GameCalque(Constantes.GAME_ID_MORT_PION);
            sModel.addCalque(calque);
            addButtonCalque(calque,sModel,toolBar);
        });
        toolBar.add(addGameBtn);

        toolBar.addSeparator();

        for(Calque calque : sModel.getCalques()) {
            addButtonCalque(calque,sModel,toolBar);
        }

        toolBar.setOpaque(true);
        toolBar.setMargin(new Insets(10,0,10,0));
        toolBar.setFloatable(false);

        return toolBar;
    }

    private void addButtonCalque(Calque calque, SModel sModel, JToolBar toolBar){
        JButton btnNew = new JButton(new ImageIcon("images\\rien.jpg"));
        btnNew.setToolTipText(calque.getName());
        btnNew.setSize(this.dimension);
        btnNew.addActionListener(e -> {
            sModel.setUse(calque);
            ((ModelController)this.getView().getController()).getController().setModel(calque.getContent());
            if (calque.isGame()){
                view.setController(new GameController(((GameCalque) calque)));
            }
            else {
                view.setController(new ShapesController((Shape) view.getModel(), this.view));
            }
            ((SModel) this.view.getModel()).unselect();
            this.view.invalidate();
        });
        toolBar.add(btnNew);

        JCheckBox checkBox = new JCheckBox(calque.getName(), true);
        checkBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED)
                sModel.setPaint(calque);
            else
                sModel.setNotPaint(calque);
            view.invalidate();
        });
        toolBar.add(checkBox);
    }
}
