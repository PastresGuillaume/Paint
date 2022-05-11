package graphics.ui;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.formes.*;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Editor  extends JFrame {
    ModelView mview;
    SModel model;

    public Editor() throws IOException {
        super("Shapes Editor");

        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                File dir = new File(Constantes.PATH_ICONS);
                    for (File file : Objects.requireNonNull(dir.listFiles()))
                        if (!file.isDirectory())
                            file.delete();
                System.exit(0);
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Constantes.WINDOW_DIMENSIONS.setSize(e.getComponent().getSize());
            }
        });

        this.buildModel();

        this.mview = new ModelView(this.model);
        this.mview.setPreferredSize(new Dimension(300,300));
        this.getContentPane().add(this.mview, java.awt.BorderLayout.CENTER);

        this.setJMenuBar(((MenuBar) mview.getMenus().get(Constantes.MENU_BAR_ID)).createMenuBar());
        this.add(((CalqueToolBar)mview.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).createToolBar(),Constantes.CALQUE_TOOL_BAR_LOC);
        this.add(((ShapeToolBar) mview.getMenus().get(Constantes.SHAPE_TOOL_BAR_ID)).createToolBar(),Constantes.SHAPE_TOOL_BAR_LOC);

//        this.add(((MenuBar) mview.getMenus().get(Constantes.MENU_BAR_ID)).createMenuBar(),Constantes.MENU_BAR_LOC);
//        this.add(((CalqueToolBar)mview.getMenus().get(Constantes.CALQUE_TOOL_BAR_ID)).createToolBar(),Constantes.CALQUE_TOOL_BAR_LOC);
    }


    private void buildModel()
    {
        this.model = new SModel();

        SImage i1 = new SImage(new Point(50,50), 250, 250,"mini.jpg");
        i1.addAttributes(new SelectionAttributes());
        this.model.add(i1);
//
//        SImage i2 = new SImage(new Point(100,100),600, "galaxia.png");
//        i2.addAttributes(new SelectionAttributes());
//        this.model.add(i2);
//
        SRectangle r = new SRectangle(new Point(10,30),200,100);
        r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
        r.addAttributes(new SelectionAttributes());
        //r.addAttributes(new RotationAttributes(0.2));
        this.model.add(r);
//
//
        SEllipsis c = new SEllipsis(new Point(100,100),10);
        c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
        c.addAttributes(new SelectionAttributes());
        //c.addAttributes(new RotationAttributes(0.2));
        this.model.add(c);
//
//        SText t= new SText(new Point(100,100),"hello");
//        t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
//        t.addAttributes(new FontAttributes());
//        t.addAttributes(new SelectionAttributes());
//        t.addAttributes(new RotationAttributes(0.2));
//        this.model.add(t);
//
//        SCollection sc = new SCollection();
//        sc.addAttributes(new SelectionAttributes());
//        r= new SRectangle(new Point(20,30),30,30);
//        r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
//        r.addAttributes(new SelectionAttributes());
//        sc.add(r);
//        c = new SEllipsis(new Point(150,100),20);
//        c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
//        c.addAttributes(new SelectionAttributes());
//        sc.add(c);
//        this.model.add(sc);
//
//        Calque c3 = new Calque();
//
//        SRectangle ajout2 = new SRectangle(new Point(200, 200), 100, 100);
//        ajout2.addAttributes(new SelectionAttributes());
//        ajout2.addAttributes(new ColorAttributes(true, true, Color.GREEN, Color.BLUE));
//        c3.add(ajout2);

        int[] xpoints = {100, 100, 300, 200};
        int[] ypoints = {100, 300, 300, 250};
        int npoints = 4;
        SPolygone p = new SPolygone(xpoints, ypoints, npoints);
        p.addAttributes(new SelectionAttributes());
        p.addAttributes(new ColorAttributes(true, true, Color.BLUE, Color.BLUE));
        p.addAttributes(new RotationAttributes(0.2));
        this.model.add(p);

//        p.addAttributes(new RotationAttributes(0.2));

        //this.model.addCalque(c3);

//        GameCalque q = new GameCalque(Constantes.GAME_ID_MORT_PION);
//        GameCalque q = new GameCalque(Constantes.GAME_ID_DAMES);
//        ((Dames) q.getGame()).interdit(0, 1, 11);
//        ((Dames) q.getGame()).interdit(0, 3, 22);
//        this.model.addCalque(q);
        //this.model.add(new SRectangle(new Point(-50, -50), 100, 100));
        //this.model.add(new SRectangle(new Point(50, 50), 100, 100));
        //this.model.setUse(1);


        //CalqueGame q = new CalqueGame(Constantes.GAME_ID_MORT_PION);
        //((MortPion) q.getGame()).play(1, 1);

//        this.model.addCalque(q);
//        this.model.setUse(1);
    }


    public static void main(String[] args) throws IOException {
        Editor self = new Editor();
        self.pack();
        self.setVisible(true);
    }
}
