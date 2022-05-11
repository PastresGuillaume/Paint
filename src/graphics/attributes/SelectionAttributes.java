package graphics.attributes;

import graphics.Constantes;

import java.io.Serial;

/**
 * Définition des attributs de Sélection.
 */
public class SelectionAttributes extends Attributes{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = -8713699973156538423L;

    /**
     * Est-ce que la forme est sélectionné ?
     */
    private boolean selected = false;

    /**
     * Donne l'identifiant de la classe SelectionAttributes.
     *
     * @return L'identifiant de la classe SelectionAttributes.
     */
    @Override
    public String getId() {
        return Constantes.SELECTION_ATTRIBUTE;
    }

    /**
     * Est-ce que la forme est sélectionné ?
     *
     * @return Est-ce que la forme est sélectionné ?
     */
    public boolean isSelected(){
        return this.selected;
    }

    /**
     * Set le booléen de sélection à vrai.
     */
    public void select(){
        this.selected = true;
    }

    /**
     * Set le booléen de sélection à faux.
     */
    public void unselect(){
        this.selected = false;
    }

    /**
     * Inverse la sélection.
     */
    public void toggleSelection(){
        this.selected = !this.selected;
    }
}
