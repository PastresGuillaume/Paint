package graphics.formes;

import graphics.ui.Visitor.ShapeVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SImage extends Shape{
    private Rectangle rect;
    private BufferedImage image;

    public SImage(Point point, String path){
        try{
            this.image = ImageIO.read(new File("./images/"+path));
            this.rect = new Rectangle(point.x, point.y, this.image.getWidth(), this.image.getHeight());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SImage(Point point, int width, String path){
        try{
            this.image = ImageIO.read(new File("./images/"+path));
            this.rect = new Rectangle(point.x, point.y, width, this.image.getHeight()*width/image.getWidth());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SImage(Point point, int width, int height, String path){
        try{
            this.image = ImageIO.read(new File("./images/"+path));
            this.rect = new Rectangle(point.x, point.y, width, height);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Point getLoc() {
        return this.rect.getLocation();
    }

    @Override
    public void setLoc(Point point) {
        this.rect.setLocation(point);
    }

    @Override
    public void translate(int x, int y) {
        this.rect.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return this.rect.getBounds();
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitImage(this);
    }

    @Override
    public void resize(int width, int height) {
        this.rect.width = width;
        this.rect.height = height;
    }

    public BufferedImage getImage(){
        return this.image;
    }
    @Override
    public float perimetre() {
        Rectangle rect1 = getBounds() ;
        return(2*rect1.height+2*rect1.width);
    }
}
