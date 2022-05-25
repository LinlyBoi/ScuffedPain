package DrawingApp;

import Shapes.Shape;

import java.awt.*;
import java.util.Map;

public class Circle implements Shape
{
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private Color fillColor;

    public Circle()
    {
        position = new Point(0,0);
        properties = new java.util.HashMap<String, Double>();
        color = Color.BLACK;
        fillColor = Color.BLACK;
        properties.put("x", 0.0);
        properties.put("y", 0.0);
        properties.put("radius", 100.0);
    }

    public Circle(Color color, Color fillColor, int x, int y, int radius)
    {
        this.position = new Point(x,y);
        properties = new java.util.HashMap<String, Double>();
        properties.put("x", (double) x);
        properties.put("y", (double) y);
        properties.put("radius", (double) radius);
        this.color = color;
        this.fillColor = fillColor;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setProperties(Map<String, Double> properties)
    {
        this.properties = properties;
    }

    public Map<String, Double> getProperties()
    {
        return properties;
    }

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
        double radius = properties.get("radius");
        canvas.drawOval((int) x, (int) y, (int) radius, (int) radius);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Circle clone = new Circle();
        clone.setPosition(position);
        clone.setProperties(properties);
        clone.setColor(color);
        clone.setFillColor(fillColor);
        return clone;
    }
    //To STring
    @Override
    public String toString()
    {
        return "Circle";
    }
}