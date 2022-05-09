package graphics.ui;

import graphics.Constantes;
import graphics.formes.Shape;
import graphics.ui.controllers.*;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;

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
        JButton btnNew = new JButton( new ImageIcon(Constantes.PATH_IMAGES + "select.png") );
        btnNew.setToolTipText( "Selection" );
        btnNew.setSize(this.dimension);
        btnNew.addActionListener(e -> view.setController(new ShapesController((Shape) view.getModel(), this.view)));
        toolBar.add( btnNew );

        JButton btnDrawRectangle = new JButton( new ImageIcon(Constantes.PATH_IMAGES + "square.png" ) );
        btnDrawRectangle.setToolTipText( "Draw rectangle" );
        btnDrawRectangle.setSize(this.dimension);
        btnDrawRectangle.addActionListener(e -> view.setController(new RectangleCreator((Shape) view.getModel(), this.view)));
        toolBar.add(btnDrawRectangle);

        JButton btnDrawCircle = new JButton( new ImageIcon(Constantes.PATH_IMAGES + "circle.png" ) );
        btnDrawCircle.setToolTipText( "Draw circle" );
        btnDrawCircle.setSize(this.dimension);
        btnDrawCircle.addActionListener(e -> view.setController(new EllipsisCreator((Shape) view.getModel(), this.view)));
        toolBar.add(btnDrawCircle);

        JButton btnDrawImage = new JButton(new ImageIcon((new ImageIcon(Constantes.PATH_IMAGES + "picture.png")).getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
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
