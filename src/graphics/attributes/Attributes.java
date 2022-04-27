package graphics.attributes;

import java.io.Serial;
import java.io.Serializable;

public abstract class Attributes implements Serializable {
    @Serial
    private static final long serialVersionUID = 2508283467690012434L;

    public abstract String getId();
}
