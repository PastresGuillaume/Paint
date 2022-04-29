package graphics.ui;

import graphics.Constantes;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileExtensionFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(Constantes.FILE_EXTENSION);
    }

    @Override
    public String getDescription() {
        return "Paint Files";
    }
}
