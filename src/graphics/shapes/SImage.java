package graphics.shapes;

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
        return new Point(this.rect.x, this.rect.y);
    }

    @Override
    public void setLoc(Point point) {
        this.rect = new Rectangle(point.x, point.y);
    }

    @Override
    public void translate(int x, int y) {
        this.rect.x += x;
        this.rect.y += y;
    }

    @Override
    public Rectangle getBounds() {
        return this.rect.getBounds();
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitImage(this);
    }

    public BufferedImage getImage(){
        return this.image;
    }
}
