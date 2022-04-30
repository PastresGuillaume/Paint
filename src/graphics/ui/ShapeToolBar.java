package graphics.ui;


import graphics.Constantes;
import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.controllers.RectangleCreator;
import graphics.ui.controllers.ShapesController;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;

public class ShapeToolBar extends AbstractBar{

    private ModelView view;
    private final Dimension dimension;
    private JToolBar toolBar = new JToolBar();

    public ShapeToolBar(ModelView view){
        this.view = view;
        this.dimension = new Dimension(25,25);
        this.setSize(300,25);
    }

    public ModelView getView(){
        return this.view;
    }

    public JToolBar createToolBar() {
        JButton btnNew = new JButton( new ImageIcon("images\\select.png") );
        btnNew.setToolTipText( "Everyday i'm drinking" );
        btnNew.setSize(this.dimension);
        btnNew.addActionListener(e -> view.setController(new ShapesController((Shape) view.getModel(), this.view)));
        toolBar.add( btnNew );

        JButton btnDrawRectangle = new JButton( new ImageIcon("images\\square.png" ) );
        btnDrawRectangle.setToolTipText( "Everyday i'm drinking" );
        btnDrawRectangle.setSize(this.dimension);
        btnDrawRectangle.addActionListener(e -> view.setController(new RectangleCreator((Shape) view.getModel(), this.view)));
        toolBar.add(btnDrawRectangle);

        return toolBar;
    }

    @Override
    public void goDarkMode() {
        this.toolBar.setBackground(Constantes.DARKMODE_MENUBAR_COLOR);
        for(Component component : this.toolBar.getComponents()){
            component.setBackground(Constantes.DARKMODE_MENUBAR_COLOR);
            component.setForeground(Color.WHITE);
        }
    }

    @Override
    public void noDarkMode() {
        this.toolBar.setBackground(Color.WHITE);
        for(Component component : this.toolBar.getComponents()){
            component.setBackground(Color.WHITE);
            component.setForeground(Constantes.DARKMODE_MENUBAR_COLOR);
        }
    }
}
