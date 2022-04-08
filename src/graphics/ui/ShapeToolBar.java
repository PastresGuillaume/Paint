package graphics.ui;


import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.controllers.RectangleCreator;
import graphics.ui.controllers.ShapesController;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;

public class ShapeToolBar extends JFrame{

    private ModelView view;
    private final Dimension dimension;

    public ShapeToolBar(ModelView view){
        super( "JToolBar sample" );
        this.view = view;
        this.dimension = new Dimension(25,25);
        this.setSize(300,25);
    }

    public ModelView getView(){
        return this.view;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton btnNew = new JButton( new ImageIcon("images\\rien.jpg") );
        btnNew.setToolTipText( "Everyday i'm drinking" );
        btnNew.setSize(this.dimension);
        btnNew.addActionListener(e -> {
            view.setController(new ShapesController(((SModel)view.getModel()).getModel(), this.getView()));
        });
        toolBar.add( btnNew );

        JButton btnDrawRectangle = new JButton( new ImageIcon("images\\rien.png" ) );
        btnDrawRectangle.setToolTipText( "Everyday i'm drinking" );
        btnDrawRectangle.setSize(this.dimension);
        btnDrawRectangle.addActionListener(e -> {
            view.setController(new RectangleCreator((Shape) view.getModel(), this.view));
        });
        toolBar.add(btnDrawRectangle);

        return toolBar;
    }

}
