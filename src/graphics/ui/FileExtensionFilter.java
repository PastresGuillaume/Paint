package graphics.ui;

import graphics.Constantes;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Create a new FileFilter only for directory and specified extension files used for our project (here ".ser")
 */
public class FileExtensionFilter extends FileFilter {

    /**
     * Vérifie si un fichier correspond au bon type.
     *
     * @param f fichier en question.
     * @return Booléen.
     */
    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(Constantes.FILE_EXTENSION);
    }

    /**
     * Description.
     *
     * @return "Paint Files"
     */
    @Override
    public String getDescription() {
        return "Paint Files";
    }
}
