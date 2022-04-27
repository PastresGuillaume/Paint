package graphics.ui;

import graphics.formes.SRectangle;

import java.io.*;

public class SaveHandler {
    private static final String file_path = "filename.txt";

    public void saveObject(Object object) {
        File file = new File(file_path);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadObject() {
        File file = new File(file_path);
        Object object;
        SRectangle rectangle;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            rectangle = (SRectangle) ois.readObject();
            System.out.println(rectangle.getBounds().width);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
