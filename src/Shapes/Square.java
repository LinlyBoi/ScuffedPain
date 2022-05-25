package DrawingApp;

import java.awt.*;
import java.util.Map;

public class Square extends DrawingApp.Rectangle
{
    private Map<String,Double> properties;
    public Square(Color color, Color fillColor, int x, int y, int Length) {
        properties = new java.util.HashMap<String, Double>();
        properties.put("x", (double) x);
        properties.put("y", (double) y);
        properties.put("Length", (double) Length);
        position = new Point(x,y);
        this.color = color;
        this.fillColor = fillColor;

    }

    public Square() {
        this(Color.BLACK, Color.BLACK, 0, 0, 100);
    }

    public void draw(Graphics canvas)
    {
        canvas.setColor(color);
        double x = properties.get("x");
        double y = properties.get("y");
        double Length = properties.get("Length");
        canvas.drawRect((int) x, (int) y, (int) Length, (int) Length);

    }

    @Override
    public void setProperties(Map<String, Double> properties)
    {
        this.properties = properties;
    }
    public Map<String,Double> getProperties()
    {
        return properties;
    }
    @Override
    public String toString()
    {
        return "Square";
    }
}
