package graphics.formes;

import graphics.Constantes;
import graphics.ui.Visitor.ShapeVisitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class SImage extends Shape{
    //TODO Serialisation a redefinir a cause de la bufferedImage

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

    public SImage(Point point, File file){
        try {
            this.image = ImageIO.read(file);
            int width = this.image.getWidth();
            int height = this.image.getHeight();
            if (width> Constantes.MAXIMUM_IMAGE_SIZE && width>height){
                height = height*Constantes.MAXIMUM_IMAGE_SIZE/width;
                width = Constantes.MAXIMUM_IMAGE_SIZE;
            }
            else if(height>Constantes.MAXIMUM_IMAGE_SIZE && height>width){
                width = width*Constantes.MAXIMUM_IMAGE_SIZE/height;
                height = Constantes.MAXIMUM_IMAGE_SIZE;
            }
            this.rect = new Rectangle(point.x, point.y, width, height);
        } catch (IOException e) {
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
    public void zoomIn() {
        this.rect.height*=2;
        this.rect.width*=2;
        this.rect.x*=2;
        this.rect.y*=2;
    }

    @Override
    public void zoomOut() {
        this.rect.height/=2;
        this.rect.width/=2;
        this.rect.x/=2;
        this.rect.y/=2;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeObject(this.rect);
        ImageIO.write(this.image, "png", oos);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        this.rect = (Rectangle)ois.readObject();
        this.image = ImageIO.read(ois);
    }

}
