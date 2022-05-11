package graphics.attributes;

import graphics.Constantes;

import java.io.Serial;

/**
 * Définition des attributs de Rotations.
 */
public class RotationAttributes extends Attributes{
    /**
     * Identifiant servant à la sauvegarde.
     */
    @Serial
    private static final long serialVersionUID = 7303747377918541891L;

    /**
     * Angle de rotation.
     */
    public Double angle;

    /**
     * Constructeur.
     *
     * @param angle Angle de rotation souhaité. Cet angle est un angle en radian dans le sens trigonométrique.
     */
   public RotationAttributes(Double angle){
        this.angle=angle;
    }

    /**
     * Donne l'identifiant de la classe RotationAttributes.
     *
     * @return L'identifiant de la classe RotationAttributes.
     */
    @Override
    public String getId() {
        return Constantes.ROTATION_ATTRIBUTES;
    }

    /**
     * Transforme une instance en instance de String.
     *
     * @return Un String représentant l'instance de ColorAttributes considéré.
     */
    @Override
    public String toString() {
        return "RotationAttributes{" +
                "angle=" + angle +
                '}';
    }
}
