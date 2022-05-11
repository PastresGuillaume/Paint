package graphics.ui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Create a new FileFilter only for directory and files extensions for images
 */
public class ImageFileExtensionFilter extends FileFilter {
    private final String[] validExtension = {".jpg", ".png", ".jpeg"};

    /**
     *
     * @param f file to test
     * @return boolean, true meaning the file is to display
     */
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

    /**
     *
     * @return string, name for the filter
     */
    @Override
    public String getDescription() {
        return "Images";
    }
}
