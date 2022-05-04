package graphics.ui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileExtensionFilter extends FileFilter {
    private final String[] validExtension = {".jpg", ".png", ".jpeg"};

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()){
            return true;
        }
        for (String extension: this.validExtension){
            if (f.getName().endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Images";
    }
}
