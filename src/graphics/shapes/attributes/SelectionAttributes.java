package graphics.shapes.attributes;

import graphics.Constantes;

public class SelectionAttributes extends Attributes{
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
