package graphics.ui.controllers;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.formes.*;
import graphics.formes.Shape;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Créateur de rectangle
 */
public class EllipsisCreator extends AbstractController{

    /**
     * modèle du contrôleur
     */
    private Shape model;
    /**
     *localisation d'un clique de souris
     */
    private Point locCreation ;
    /**
     *couleur de remplissage de la figure
     */
    private Color filledColor = Constantes.COLOR_INVISIBLE;
    /**
     *couleur des contours de la figure
     */
    private Color strokedColor = Constantes.DEFAULT_COLOR_ADD;
    /**
     *son JPopupMenu
     */
    final JPopupMenu menu = new JPopupMenu("Color Menu");

    /**
     *Contructeur
     *
     * @param newModel modèle pour ce contrôleur
     * @param view view à associer à ce contrôleur
     */
    public EllipsisCreator(Shape newModel, ModelView view) {
        super(newModel);
        this.model = newModel;
        this.setView(view);
        this.locCreation = new Point();
        unselectedAll();
    }

    /**
     * Change le modèle.
     *
     * @param model modèle souhaité
     */
    @Override
    public void setModel(Shape model)
    {
        this.model = model;
    }

    /**
     * Renvoie le modèle actuel.
     *
     * @return Le modèle actuel.
     */
    @Override
    public Shape getModel() {return this.model;}

    /**
     * Action réalisée lorsque l'utilisateur appuie.
     *
     * @param e Événement souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        unselectedAll();
        if(SwingUtilities.isRightMouseButton(e))
        {
            menu.show(this.getView() , e.getX(), e.getY());
        }
        else {
            this.locCreation = e.getPoint();
            SEllipsis circle = new SEllipsis(e.getPoint(), 10);
            circle.addAttributes(new ColorAttributes(true, true, filledColor, strokedColor));
            circle.addAttributes(new SelectionAttributes());
            ((SelectionAttributes) circle.getAttributes(Constantes.SELECTION_ATTRIBUTE)).select();
            ((SCollection) this.model).add(circle);
        }
    }

    /**
     * Action réalisée lorsque l'utilisateur relâche la souris.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.locCreation = new Point();
        unselectedAll();
    }

    /**
     * Action réalisée lorsque l'utilisateur clique.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.locCreation = new Point();
        unselectedAll();
    }

    /**
     * Action réalisée lorsque l'utilisateur fait un cliqué glissé.
     *
     * @param e Événement souris.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
            int width = (int) (e.getPoint().getX() - this.locCreation.getX());
            int height = (int) (e.getPoint().getY() - this.locCreation.getY());
            this.changeSize(width, height);
    }

    /**
     * Action réalisée lorsque l'utilisateur appuie sur une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyPressed(KeyEvent evt){
    }

    /**
     * Action réalisée lorsque l'utilisateur relâche une touche.
     *
     * @param evt Événement clavier.
     */
    @Override
    public void keyReleased(KeyEvent evt){
    }

    /**
     *désélectionne toutes les Shapes
     */
    private void unselectedAll(){
        try {
            for (Shape s : ((SModel) this.model).getCalqueUse().getElement())
                s.unselect();
        }
        catch (ClassCastException e){
            for (Shape s : ((SCollection) this.model).getElement())
                s.unselect();
        }
    }

    /**
     *Change la taille de l'ellipse en création
     *
     * @param i abscisses du d'un coin du rectangle créant l'ellipse, dont le coin opposé est aux coordonnées locCreation
     * @param p ordonnées du d'un coin du rectangle créant l'ellipse, dont le coin opposé est aux coordonnées locCreation
     */
    private void changeSize(int i,int p) {
        for (Shape s : ((SCollection) this.model).getElement()) {
            SelectionAttributes selectionAttributes = (SelectionAttributes) s.getAttributes(Constantes.SELECTION_ATTRIBUTE);
            if (selectionAttributes != null && selectionAttributes.isSelected()) {
                Rectangle rect = ((SEllipsis) s).getRect();
                if (i < 0 && p < 0) {
                    rect.setLocation(new Point(this.locCreation.x + i,this.locCreation.y +p));
                }
                else if (i < 0) {
                    rect.setLocation((new Point(this.locCreation.x + i,this.locCreation.y)));
                }
                else if (p < 0) {
                    rect.setLocation((new Point(this.locCreation.x,this.locCreation.y + p)));
                }
                rect.setSize(Math.abs(i), Math.abs(p));
            }
        }
    }

    /**
     *Construit le JPopUpMenu associé
     *
     * @param menu le menu JPopUpMenu à construire
     * @param modelController le modelcontroller utilisé
     */
    @Override
    public void requestJPopopUpMenu(JPopupMenu menu,ModelController modelController) {
        JMenu color = new JMenu("Color");
        JMenuItem strokedColorMenu = new JMenuItem("Stroked color");
        JMenuItem filledColorMenu = new JMenuItem("Filled color", new ImageIcon("graphics\\shapes\\ui\\image\\chat.jpg" ));

        strokedColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            Color strokedColorPrevious = this.strokedColor;
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            strokedColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (strokedColorMenu == null)
                strokedColor = strokedColorPrevious;
            myFrame.setTitle("Select your stroked color");
        });
        filledColorMenu.addActionListener(e -> {
            JFrame myFrame = new JFrame();
            Color filledColorPrevious = this.filledColor;
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myFrame.setSize(500, 300);
            myFrame.setLocationRelativeTo(null);
            myFrame.setLayout(new BorderLayout());
            filledColor = JColorChooser.showDialog(myFrame, "Pick a Color", Color.GREEN);
            if (filledColor == null)
                filledColor = filledColorPrevious;
            myFrame.setTitle("Select your filled color");
        });

        color.add(strokedColorMenu);
        color.add(filledColorMenu);

        menu.add(color);
    }
}
