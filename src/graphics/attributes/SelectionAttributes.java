package graphics.attributes;

import graphics.Constantes;

import java.io.Serial;

public class SelectionAttributes extends Attributes{
    @Serial
    private static final long serialVersionUID = -8713699973156538423L;

    private boolean selected = false;

    @Override
    public String getId() {
        return Constantes.SELECTION_ATTRIBUTE;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void select(){
        this.selected = true;
    }

    public void unselect(){
        this.selected = false;
    }

    public void toggleSelection(){
        this.selected = !this.selected;
    }
}
