public class ConsoleDrawing {
    public static void main(String[] args) {

        //initial canvas settings
        DrawingCanvas.canvasWidth = Integer.parseInt(args[0]);
        DrawingCanvas.canvasHeight = Integer.parseInt(args[1]);
        DrawingCanvas.backgroundCharacter = args[2].charAt(0);

        //app start
        System.out.println("----WELCOME TO MY CONSOLE DRAWING APP----");

        DrawingCanvas canvas = new DrawingCanvas();
        canvas.printCanvasSettings();
        DrawingCanvas.selectOptions();
    }
}