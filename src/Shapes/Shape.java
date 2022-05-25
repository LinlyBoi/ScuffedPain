package Shapes;
public interface Shape
{
    /* set position */

    void setPosition(java.awt.Point position);

    java.awt.Point getPosition();

    /* update shape specific properties (e.g., radius) */
    void setProperties(java.util.Map<String, Double> properties);

    java.util.Map<String, Double> getProperties();

    /* colorize */
    void setColor(java.awt.Color color);

    java.awt.Color getColor();

    void setFillColor(java.awt.Color color);

    java.awt.Color getFillColor();

    /* redraw the shape on the canvas */
    void draw(java.awt.Graphics canvas);

    /* create a deep clone of the shape */
    Object clone() throws CloneNotSupportedException;
}
