package graphics.ui;

import java.io.*;

/**
 * Class for managing saves
 */
public class SaveHandler {
    /**
     * Save the given date in the specified file
     * @param object Data to save
     * @param file file in which data will be saved
     */
    public void saveObject(Object object, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load data form the specified file
     * @param file to extract data from
     * @return data to load
     */
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
