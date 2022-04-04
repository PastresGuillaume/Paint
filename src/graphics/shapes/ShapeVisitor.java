package graphics.shapes;

public interface ShapeVisitor {
    public void visitRectangle(SRectangle rectangle);

    public void visitCircle(SCircle circle);

    public void visitText(SText text);

    public void visitCollection(SCollection collection);

    public void visitImage(SImage image);
}
