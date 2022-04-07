package graphics.shapes.ui;


import graphics.shapes.Shape;
import graphics.shapes.ui.controllers.RectangleCreator;
import graphics.shapes.ui.controllers.ShapesController;
import graphics.shapes.uiCalques.ModelController;
import graphics.shapes.uiCalques.ModelView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ShapeToolBar extends JFrame{

    private ShapesView view;
    private final Dimension dimension;

    public ShapeToolBar(ShapesView view){
        super( "JToolBar sample" );
        this.view = view;
        this.dimension = new Dimension(25,25);
        this.setSize(300,25);
    }

    public ShapesView getView(){
        return this.view;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton btnNew = new JButton( new ImageIcon("images\\rien.jpg") );
        btnNew.setToolTipText( "Everyday i'm drinking" );
        btnNew.setSize(this.dimension);
        btnNew.addActionListener(e -> {
            view.setController( new ShapesController((Shape) view.getModel(),view));
            view.invalidate();
        });
        toolBar.add( btnNew );

        JButton btnDrawRectangle = new JButton( new ImageIcon("images\\rien.png" ) );
        btnDrawRectangle.setToolTipText( "Everyday i'm drinking" );
        btnDrawRectangle.setSize(this.dimension);
        btnDrawRectangle.addActionListener(e -> {
            view.setController(new RectangleCreator((Shape) view.getModel(),view));
            view.invalidate();
        });
        toolBar.add( btnDrawRectangle);

        return toolBar;
    }

}
