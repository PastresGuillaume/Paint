package graphics.attributes;

import graphics.Constantes;

import javax.management.Attribute;

public class RotationAttributes extends Attributes{
   public Double angle ;

    public RotationAttributes(Double angle2){
        this.angle=angle2;
    }

   public Double Angle(){
       return (this.angle);
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

   public void ModifAngle(Double angle){
       this.angle = angle;
   }
}
