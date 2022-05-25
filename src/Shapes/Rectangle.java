package DrawingApp;

import Shapes.Shape;

import java.awt.*;
import java.util.Map;

public class Rectangle implements Shape
{
    protected Point position;
    private Map<String, Double> properties;
    protected Color color;
    protected Color fillColor;
    public Rectangle()
    {
        position = new Point(0,0);
        properties = new java.util.HashMap<String, Double>();
        color = Color.BLACK;
        fillColor = Color.BLACK;
        properties.put("x", 10.0);
        properties.put("y", 10.0);
        properties.put("width", 100.0);
        properties.put("height", 100.0);
        position = new Point(0,0);
    }
    public Rectangle(Color color, Color fillColor, int x, int y, int width, int height)
    {
        this.position = new Point(x,y);
        properties = new java.util.HashMap<String, Double>();
        properties.put("x", (double) x);
        properties.put("y", (double) y);
        properties.put("width", (double) width);
        properties.put("height", (double) height);
        this.color = color;
        this.fillColor = fillColor;
        position = new Point(x,y);
    }

    @Override
    public void setPosition(Point position)
    {
        this.position = position;
    }

    @Override
    public Point getPosition()
    {
        return position;
    }

    @Override
    public void setProperties(Map<String, Double> properties)
    {
        this.properties = properties;

    }

    @Override
    public Map<String, Double> getProperties()
    {
        return properties;
    }

    @Override
    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public void setFillColor(Color color)
    {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor()
    {
        return fillColor;
    }

    @Override
    public void draw(Graphics canvas)
    {
        canvas.setColor(color);
        double x = properties.get("x");
        double y = properties.get("y");
        double width = properties.get("width");
        double height = properties.get("height");
        canvas.drawRect((int) x, (int) y, (int) width, (int) height);

    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    //To string
    @Override
    public String toString()
    {
        return "Rectangle";
    }
}
