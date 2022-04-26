package graphics.ui;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.attributes.FontAttributes;
import graphics.attributes.RotationAttributes;
import graphics.attributes.SelectionAttributes;
import graphics.formes.*;
import graphics.jeux.MortPion;
import graphics.ui.View.ModelView;

import javax.swing.*;
import java.awt.*;

public class Editor  extends JFrame {
    ModelView mview;
    SModel model;

    public Editor()
    {
        super("Shapes Editor");

        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                System.exit(0);
            }
        });

        this.buildModel();

        this.mview = new ModelView(this.model);
        this.mview.setPreferredSize(new Dimension(300,300));
        this.getContentPane().add(this.mview, java.awt.BorderLayout.CENTER);

        this.add( new ShapeToolBar(this.mview).createToolBar(), BorderLayout.PAGE_END );

        this.add( new CalqueToolBar(this.mview).createToolBar(), BorderLayout.EAST );
    }


    private void buildModel()
    {
        this.model = new SModel();

        SImage i1 = new SImage(new Point(50,50), 250, 250,"mini.jpg");
        i1.addAttributes(new SelectionAttributes());
        this.model.add(i1);

        SImage i2 = new SImage(new Point(100,100),600, "galaxia.png");
        i2.addAttributes(new SelectionAttributes());
        this.model.add(i2);

        SRectangle r = new SRectangle(new Point(10,10),100,100);
        r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
        r.addAttributes(new SelectionAttributes());
        r.addAttributes(new RotationAttributes(0.2));
        this.model.add(r);

        SEllipsis c = new SEllipsis(new Point(100,100),10);
        c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
        c.addAttributes(new SelectionAttributes());
        this.model.add(c);

        SText t= new SText(new Point(100,100),"hello");
        t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
        t.addAttributes(new FontAttributes());
        t.addAttributes(new SelectionAttributes());
        this.model.add(t);

        SCollection sc = new SCollection();
        sc.addAttributes(new SelectionAttributes());
        r= new SRectangle(new Point(20,30),30,30);
        r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
        r.addAttributes(new SelectionAttributes());
        sc.add(r);
        c = new SEllipsis(new Point(150,100),20);
        c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
        c.addAttributes(new SelectionAttributes());
        sc.add(c);
        this.model.add(sc);

        Calque c3 = new Calque();

        SRectangle ajout2 = new SRectangle(new Point(200, 200), 100, 100);
        ajout2.addAttributes(new SelectionAttributes());
        ajout2.addAttributes(new ColorAttributes(true, true, Color.GREEN, Color.BLUE));
        c3.add(ajout2);

        this.model.addCalque(c3);

        GameCalque q = new GameCalque(Constantes.GAME_ID_MORT_PION);
        this.model.addCalque(q);
    }

    public static void main(String[] args)
    {
        Editor self = new Editor();
        self.pack();
        self.setVisible(true);
    }
}
