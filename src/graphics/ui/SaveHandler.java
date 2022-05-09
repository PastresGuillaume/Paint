package graphics.ui;

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
            System.out.println("Selected File has the wrong extension or doesn't exist");
            e.printStackTrace();
        }
        return null;
    }
}
