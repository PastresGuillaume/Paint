package graphics.attributes;

import graphics.Constantes;

public class RotationAttributes extends Attributes{
   public Double angle;

   public RotationAttributes(Double angle){
        this.angle=angle;
    }

    @Override
    public String getId() {
        return Constantes.ROTATION_ATTRIBUTES;
    }

    @Override
    public String toString() {
        return "RotationAttributes{" +
                "angle=" + angle +
                '}';
    }
}
