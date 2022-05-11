package graphics.attributes;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe abstraite donnant le comportement général des attributs.
 */
public abstract class Attributes implements Serializable {
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 2508283467690012434L;

    /**
     * Retourne l'identifiant de l'attribut.
     * @return String -> retourne l'identifiant de l'attribut.
     */
    public abstract String getId();
}
