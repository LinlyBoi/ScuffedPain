import Shapes.Shape;

public interface DrawingEngine
{
    /* manage shapes objects */
    void addShape(Shape shape);

    void removeShape(Shape shape);

    /* return the created shapes objects */
    Shape[] getShapes();

    /* redraw all shapes on the canvas */
    void refresh(java.awt.Graphics canvas);

    /* return the classes (types) of supported shapes that can
     * be dynamically loaded at runtime (see Part 3) */
    java.util.List<Class<? extends Shape>> getSupportedShapes();

    /* add to the supported shapes the new shape class (see Part 3) */
    void installPluginShape(Class<? extends Shape> shapeClass);
}
