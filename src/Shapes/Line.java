package DrawingApp;

import Shapes.Shape;

import java.awt.*;
import java.util.Map;

public class Line implements Shape
{
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private Color fillColor;

    public Line()
    {
        position = new Point(0,0);
        properties = new java.util.HashMap<String, Double>();
        color = Color.BLACK;
        fillColor = Color.BLACK;
        properties.put("x1", 0.0);
        properties.put("y1", 0.0);
        properties.put("x2", 100.0);
        properties.put("y2", 100.0);
    }

    public Line(Color color, Color fillColor, int x, int y, int x2, int y2)
    {
        this.position = new Point(x,y);
        properties = new java.util.HashMap<String, Double>();
        properties.put("x1", (double) x);
        properties.put("y1", (double) y);
        properties.put("x2", (double) x2);
        properties.put("y2", (double) y2);
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

    public Color getColor()
    {
        return color;
    }

    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
    }

    public Color getFillColor()
    {
        return fillColor;
    }

    @Override
    public void draw(Graphics canvas)
    {
        canvas.setColor(color);
        double x1 = properties.get("x1");
        double y1 = properties.get("y1");
        double x2 = properties.get("x2");
        double y2 = properties.get("y2");
        canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return this;
    }
    //To String
    @Override
    public String toString()
    {
        return "Line";
    }
}
