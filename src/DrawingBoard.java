import Shapes.Shape;
import org.reflections.Reflections;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class DrawingBoard extends Panel implements DrawingEngine
{
    private final ArrayList<Shape> shapes;

    private final JComboBox<String> color_selection;
    public final JComboBox<Shape> shape_selection;
    private final ArrayList<Class<? extends Shape>> supportedShapes;
    private final Label canvas;
    private final Panel ShapeButtons;
    private final Panel EditPanel;
    private final Stack<Shape> undoStack;
    private final Stack<Shape> redoStack;

    DrawingBoard()
    {
        super();
        setLayout(new BorderLayout());
        setVisible(true);
        setBackground(Color.white);

        shapes = new ArrayList<>();
        canvas = new Label();
        canvas.setSize(500, 500);
        add(canvas, BorderLayout.CENTER);
        ShapeButtons = new Panel(new GridLayout(1, 0));
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        Reflections reflections = new Reflections("DrawingApp");
        Set<Class<? extends Shape>> ShapeSet = reflections.getSubTypesOf(Shape.class);
        supportedShapes = new ArrayList<>(ShapeSet);
        shape_selection = new JComboBox<>();
        color_selection = new JComboBox<>();
        color_selection.addItem("Black");
        color_selection.addItem("Blue");
        color_selection.addItem("Red");
        color_selection.addItem("Green");
        color_selection.addItem("Yellow");
        color_selection.addItem("Orange");
        color_selection.addItem("Pink");
        color_selection.addItem("White");
        EditPanel = new Panel(new GridLayout(0, 2));
    }

    public Graphics getCanvas()
    {
        return canvas.getGraphics();
    }

    //Create Label and TextField for each property of shape
    public void ShapeForm(Shape shape, Frame frame)
    {
        JDialog dialog = new JDialog(frame, "Create " + shape.getClass().getSimpleName(), true);
        Map<String, Double> properties = shape.getProperties();
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2));
        String[] keys = properties.keySet().toArray(new String[0]);
        for (String key : keys)
        {
            Label label = new Label(key);
            TextField textField = new TextField(properties.get(key).toString());
            panel.add(label);
            panel.add(textField);
        }
        Button apply = new Button("Apply");
        Button cancel = new Button("Cancel");
        cancel.addActionListener(e -> dialog.dispose());
        apply.addActionListener(e ->
        {
            for (int i = 1; i < panel.getComponentCount(); i += 2)
            {
                if (panel.getComponent(i) instanceof TextField textField)
                {
                    Label label = (Label) panel.getComponent(i - 1);
                    //pls edit this later ffs
                    shape.getProperties().put(label.getText(), Double.parseDouble(textField.getText()));

                }
            }
            addShape(shape);
            dialog.dispose();
            shape.draw(getCanvas());
        });

        panel.add(apply);
        panel.add(cancel);
        panel.setVisible(true);
        dialog.add(panel);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);

    }

    public void undo()
    {
        if (!undoStack.isEmpty())
        {
            Shape shape = undoStack.pop();
            shapes.remove(shape);
            refresh(getCanvas());
            redoStack.push(shape);
        }
    }

    public void redo()
    {
        if (!redoStack.isEmpty())
        {
            Shape shape = redoStack.pop();
            addShape(shape);
        }
    }

    public void clearAll()
    {
        shapes.clear();
        getCanvas().clearRect(0, 0, 50000, 50000);
        refresh(getCanvas());
        undoStack.clear();
        redoStack.clear();
    }

    public void setShapeButtons(ArrayList<Button> buttons)
    {
        ShapeButtons.removeAll();
        for (Button button : buttons)
        {
            ShapeButtons.add(button);
        }
        add(ShapeButtons, BorderLayout.NORTH);
    }
    public void setEditPanel(Frame frame)
    {
        Button Edit = new Button("Edit");
        Edit.addActionListener(e ->
        {
            if (shape_selection.getSelectedItem() != null)
            {
                Shape shape = (Shape) shape_selection.getSelectedItem();
                ShapeForm(shape, frame);
                refresh(getCanvas());
            }
        });
        Button Colorise = new Button("Colorise");
        Colorise.addActionListener(e ->
        {
            if (shape_selection.getSelectedItem() != null)
            {
                Shape shape = (Shape) shape_selection.getSelectedItem();
                shape.setColor(get_selected_color());
                refresh(getCanvas());
            }
        });
        Button Delete = new Button("Delete");
        Delete.addActionListener(e ->
        {
            if (shape_selection.getSelectedItem() != null)
            {
                Shape shape = (Shape) shape_selection.getSelectedItem();
                shapes.remove(shape);
                refresh(getCanvas());
            }
        });
        EditPanel.add(shape_selection);
        EditPanel.add(color_selection);
        EditPanel.add(Edit);
        EditPanel.add(Colorise);
        EditPanel.add(Delete);
        add(EditPanel, BorderLayout.SOUTH);
    }

    public Shape get_selected_shape()
    {
        return shape_selection.getItemAt(shape_selection.getSelectedIndex());
    }
    public Color get_selected_color()
    {
        String color = (String) color_selection.getSelectedItem();
        return switch (color)
                {
                    case "Blue" -> Color.BLUE;
                    case "Red" -> Color.RED;
                    case "Green" -> Color.GREEN;
                    case "Yellow" -> Color.YELLOW;
                    case "Orange" -> Color.ORANGE;
                    case "Pink" -> Color.PINK;
                    case "White" -> Color.WHITE;
                    default -> Color.BLACK;
                };
    }


    @Override
    public void addShape(Shape shape)
    {
        shapes.add(shape);
        shape.draw(getCanvas());
        shape_selection.addItem(shape);
        undoStack.push(shape);
        canvas.repaint();
    }

    @Override
    public void removeShape(Shape shape)
    {
        shapes.remove(shape);
        shape_selection.removeItem(shape);
        redoStack.push(shape);
        refresh(getCanvas());
    }

    public void colorShape(Shape shape, Color color)
    {
        shape.setColor(color);
        shape.draw(getCanvas());
        refresh(getCanvas());
    }

    @Override
    public Shape[] getShapes()
    {
        Shape[] shapes = new Shape[this.shapes.size()];
        for (int i = 0; i < this.shapes.size(); i++)
        {
            shapes[i] = this.shapes.get(i);
        }
        return shapes;
    }

    @Override
    public void refresh(Graphics canvas)
    {
        //clear canvas
        canvas.clearRect(0, 0, 500000, 50000);
        //draw all shapes
        for (Shape shape : shapes)
        {
            shape.draw(canvas);
            this.canvas.repaint();

        }

    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes()
    {
        return supportedShapes;
    }

    @Override
    public void installPluginShape(Class<? extends Shape> shapeClass)
    {
        supportedShapes.add(shapeClass);
    }
}




