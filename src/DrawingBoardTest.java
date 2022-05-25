import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawingBoardTest
{

    @Test
    void getShapes()
    {
        DrawingBoard drawingBoard = new DrawingBoard();
        assertEquals(0, drawingBoard.getShapes().length);
//        drawingBoard.addShape(new Line());
        assertEquals(1, drawingBoard.getShapes().length);
    }

    @Test
    void getSupportedShapes()
    {
        DrawingBoard drawingBoard = new DrawingBoard();
        assertEquals(4, drawingBoard.getSupportedShapes().size());
        System.out.println(drawingBoard.getSupportedShapes());
    }
    @Test
    void installShapes()
    {
        DrawingBoard drawingBoard = new DrawingBoard();
        assertEquals(4, drawingBoard.getSupportedShapes().size());
        assertEquals(6, drawingBoard.getSupportedShapes().size());
    }

    @Test
    void installPluginShape()
    {
    }
}