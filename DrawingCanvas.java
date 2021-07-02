import java.util.Scanner;

public class DrawingCanvas {
    public static int canvasWidth;
    public static int canvasHeight;
    public static char backgroundCharacter;

    //Scanner class
    public static Scanner keyboard = new Scanner(System.in);

    //print current canvas settings
    public void printCanvasSettings() {
        System.out.println("Current drawing canvas settings:");
        System.out.printf("- Width: %d\n", canvasWidth);
        System.out.printf("- Height: %d\n", canvasHeight);
        System.out.printf("- Background character: %c\n", backgroundCharacter);
        System.out.println();
    }

    //update canvas settings
    private void updateSettings() {
        System.out.print("Canvas width: ");
        canvasWidth = keyboard.nextInt();

        System.out.print("Canvas height: ");
        canvasHeight = keyboard.nextInt();

        System.out.print("Background character: ");
        backgroundCharacter = keyboard.next().charAt(0);

        System.out.print("Drawing canvas has been updated!\n");
        System.out.println();
        printCanvasSettings();
    }

    //main options
    public static void selectOptions() {
        System.out.println("Please select an option. Type 4 to exit.");
        System.out.println("1. Draw triangles");
        System.out.println("2. Draw squares");
        System.out.println("3. Update drawing canvas settings");
        System.out.println("4. Exit");

        int option = keyboard.nextInt();

        switch (option) {
            case 1 -> {
                Triangle triangle = new Triangle();
                triangle.setParameters();
                triangle.drawTriangle(triangle.getSideWidth(),
                        triangle.getPrintingCharacter(),
                        triangle.getAlignment());
            }
            case 2 -> {
                Square square = new Square();
                square.setParameters();
                square.drawSquare(square.getSideWidth(),
                        square.getPrintingCharacter(),
                        square.getAlignment());
            }
            case 3 -> {
                DrawingCanvas canvas = new DrawingCanvas();
                canvas.updateSettings();
                selectOptions();
            }
            case 4 -> System.out.println("Goodbye!");
            default -> {
                System.out.println("Unsupported option. Please try again!");
                selectOptions();
            }
        }
    }
}