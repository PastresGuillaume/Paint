package graphics.attributes;

import graphics.Constantes;

import java.io.Serial;

public class RotationAttributes extends Attributes{
    @Serial
    private static final long serialVersionUID = 7303747377918541891L;

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
