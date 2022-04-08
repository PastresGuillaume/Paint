package graphics.ui;

import graphics.formes.SCollection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveHandler {

    public void createSave(){
        try{
            File save = new File("filename.txt");
            if (save.createNewFile()){
                System.out.println("Save successfully created");
            }
            else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating a file");
            e.printStackTrace();
        }
    }


    public void writeSave(SCollection model){
        try {
            FileWriter fileWriter = new FileWriter("filename.txt");
            fileWriter.write("test");
            fileWriter.close();
            System.out.println("Successfully saved");
        } catch (IOException e) {
            System.out.println("An error occurred while saving");
            e.printStackTrace();
        }
    }
}
