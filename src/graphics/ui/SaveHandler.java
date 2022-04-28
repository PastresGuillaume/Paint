package graphics.ui;

import graphics.Constantes;
import graphics.attributes.ColorAttributes;
import graphics.formes.SRectangle;

import java.io.*;

public class SaveHandler {
    public void saveObject(Object object, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadObject(File file) {
        Object object;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            object = ois.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
