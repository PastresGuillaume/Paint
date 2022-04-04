package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes{
    private boolean selected = false;

    @Override
    public String getId() {
        return "SelectionAttributes";
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
        if (this.isSelected()){
            this.unselect();
        }
        else{
            this.select();
        }
    }
}
