import Shapes.Shape;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class App
{
    public static JFrame frame;

    public static void main(String[] args)
    {
        // Create a new frame
        frame = new JFrame("Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 900);

        // Create a new drawing board
        DrawingBoard drawingBoard = new DrawingBoard();
        drawingBoard.setEditPanel(frame);


        //Create Shape Buttons
        ArrayList<Button> shapeButtonList;
        shapeButtonList = createShapeButtons(drawingBoard);
        drawingBoard.setShapeButtons(shapeButtonList);
        frame.add(drawingBoard, BorderLayout.CENTER);
        // Create the buttons
        Button undoButton = new Button("Undo");
        undoButton.addActionListener(e -> drawingBoard.undo());
        Button redoButton = new Button("Redo");
        redoButton.addActionListener(e -> drawingBoard.redo());

        Button restartButton = new Button("Restart");
        restartButton.addActionListener(e -> drawingBoard.clearAll());

        Button exitButton = new Button("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        Button refreshButton = new Button("Refresh");
        refreshButton.addActionListener(e -> drawingBoard.refresh(drawingBoard.getCanvas()));

        Button installShape = new Button("Install Shape");
        installShape.addActionListener(e ->
        {
            JDialog dialog = new JDialog(frame, "Install Shape", true);
            JPanel panel = new JPanel();
            JButton apply = new JButton("Apply");
            JButton cancel = new JButton("Cancel");
            JTextField shapeName = new JTextField(10);
            apply.addActionListener(b ->{
                drawingBoard.installPluginShape(Select_Class_From_Jar_file(shapeName.getText()));
                drawingBoard.setShapeButtons(createShapeButtons(drawingBoard));
            });
            cancel.addActionListener(b -> dialog.dispose());
            panel.add(new JLabel("Shape Name:"));
            panel.add(shapeName);
            panel.add(apply);
            panel.add(cancel);
            dialog.add(panel);
            dialog.setSize(300, 300);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
            frame.repaint();
        });


        // Create a new panel for the edit buttons
        Panel EditButtons = new Panel();
        EditButtons.setLayout(new GridLayout(0, 2));
        //Add buttons to panel
        EditButtons.add(undoButton);
        EditButtons.add(redoButton);
        EditButtons.add(refreshButton);
        EditButtons.add(restartButton);
        EditButtons.add(installShape);
        EditButtons.add(exitButton);
        // Add the edit buttons to the frame
        frame.add(EditButtons, BorderLayout.WEST);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static ArrayList<Button> createShapeButtons(DrawingBoard drawingBoard)
    {
        ArrayList<Class<? extends Shape>> supportedShapes = (ArrayList<Class<? extends Shape>>) drawingBoard.getSupportedShapes();
        ArrayList<Button> buttons = new ArrayList<>();
        for (Class<? extends Shape> shape : supportedShapes)
        {
            Button button = new Button(shape.getSimpleName());
            button.addActionListener(e ->
            {
                try
                {
                    Shape newShape = shape.getConstructor().newInstance();
                    newShape.setColor(drawingBoard.get_selected_color());
                    drawingBoard.ShapeForm(newShape, frame);
                    frame.update(frame.getGraphics());
                    drawingBoard.update(drawingBoard.getCanvas());

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e1)
                {
                    e1.printStackTrace();
                }
                drawingBoard.refresh(drawingBoard.getCanvas());
            });
            buttons.add(button);
        }
        return buttons;
    }

    public static Class<?extends Shape> LoadClassFromJar(String jarPath, String className)
    {
        try
        {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(jarPath).toURI().toURL()});
            Class<?> cls = Class.forName(className, true, classLoader);
            System.out.println(cls.getName());
            return cls.asSubclass(Shape.class);
        } catch (MalformedURLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static Class<? extends Shape> Select_Class_From_Jar_file(String classname)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Jar Files", "jar"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return LoadClassFromJar(selectedFile.getAbsolutePath(), classname);
        }
        return null;

    }
}









