package graphics.ui;


import graphics.Constantes;
import graphics.formes.SModel;
import graphics.formes.Shape;
import graphics.ui.controllers.ImageCreator;
import graphics.ui.controllers.RectangleCreator;
import graphics.ui.controllers.ShapesController;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ShapeToolBar extends AbstractBar{

    private ModelView view;
    private final Dimension dimension;
    private JToolBar toolBar = new JToolBar();
    private final JFileChooser fileChooser;

    public ShapeToolBar(ModelView view){
        this.view = view;
        this.dimension = new Dimension(25,25);
        this.setSize(300,25);
        this.fileChooser = new JFileChooser();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new ImageFileExtensionFilter());
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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

        JButton btnDrawImage = new JButton(new ImageIcon((new ImageIcon("images\\picture.png")).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
        btnDrawImage.setToolTipText("Draw Image");
        btnDrawImage.setSize(this.dimension);
        btnDrawImage.addActionListener(e -> {
            int operationResult = fileChooser.showOpenDialog(view);
            if (operationResult == JFileChooser.APPROVE_OPTION){
                view.setController(new ImageCreator((Shape) view.getModel(), view, fileChooser.getSelectedFile()));
            }
        });
        toolBar.add(btnDrawImage);

        return toolBar;
    }

    @Override
    public void changeColor() {
        this.toolBar.setBackground(Constantes.MENUBAR_COLOR);
        for(Component component : this.toolBar.getComponents()){
            component.setBackground(Constantes.MENUBAR_COLOR);
            component.setForeground(Constantes.TEXTMENU_COLOR);
        }
    }
}
