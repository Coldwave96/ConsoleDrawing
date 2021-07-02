public class Square {
    private int sideWidth;
    private char printingCharacter;
    private String alignment;

    //setters and getters
    public int getSideWidth() {
        return sideWidth;
    }

    public char getPrintingCharacter() {
        return printingCharacter;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setSideWidth(int sideWidth) {
        this.sideWidth = sideWidth;
    }

    public void setPrintingCharacter(char printingCharacter) {
        this.printingCharacter = printingCharacter;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    //set square parameters
    public void setParameters() {
        System.out.println("Side length:");
        int tmpLength = DrawingCanvas.keyboard.nextInt();

        if (tmpLength > Math.min(DrawingCanvas.canvasHeight, DrawingCanvas.canvasWidth)) {
            System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). " +
                            "Please try again.\n", DrawingCanvas.canvasWidth, DrawingCanvas.canvasHeight);
            setParameters();
        } else {
            setSideWidth(tmpLength);
            System.out.println("Printing character:");
            setPrintingCharacter(DrawingCanvas.keyboard.next().charAt(0));

            System.out.println("Alignment (left, middle, right):");
            setAlignment(DrawingCanvas.keyboard.next());
        }
    }

    //print square from the matrix
    private void printSquare(char[][] matrix) {
        for (int i = 0; i < DrawingCanvas.canvasHeight; i++) {
            for (int j = 0; j < DrawingCanvas.canvasWidth; j++) {
                System.out.printf("%c", matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    //deal with different choice after zoom function
    private void newSquare() {
        System.out.println("Draw another square (Y/N)?");
        String confirmation = DrawingCanvas.keyboard.next();
        if (confirmation.equals("Y")) {
            setParameters();
            drawSquare(sideWidth, printingCharacter, alignment);
        } else if (confirmation.equals("N")) {
            DrawingCanvas.selectOptions();
        } else {
            System.out.println("Unsupported option. Please try again!");
            newSquare();
        }
    }

    //zoom in/out function
    private void zoomFunc(int sideLength, String type) {
        System.out.println("Type I/O to zoom in/out. Use other keys to continue.");
        String key = DrawingCanvas.keyboard.next().toUpperCase();

        switch (key) {
            case "I":
                if (sideLength >= Math.min(DrawingCanvas.canvasWidth, DrawingCanvas.canvasHeight)) {
                    drawSquare(Math.min(DrawingCanvas.canvasWidth,
                            DrawingCanvas.canvasHeight),
                            printingCharacter, type);
                } else {
                    drawSquare(sideLength + 1, printingCharacter, type);
                }
                break;
            case "O":
                if (sideLength == 1) {
                    drawSquare(1, printingCharacter, type);
                } else {
                    drawSquare(sideLength - 1, printingCharacter, type);
                }
                break;
            default:
                newSquare();
                break;
        }
    }

    //draw square function
    public void drawSquare(int sideLength, char printCharacter, String alignment) {
        char[][] matrix = new char[DrawingCanvas.canvasHeight][DrawingCanvas.canvasWidth];
        switch (alignment) {
            case "middle" -> {
                for (int i = 0; i < DrawingCanvas.canvasHeight; i++) {
                    if (i < sideLength) {
                        for (int j = (DrawingCanvas.canvasWidth - sideLength) / 2;
                             j < (DrawingCanvas.canvasWidth + sideLength) / 2; j++) {
                            matrix[i][j] = printCharacter;
                        }
                        for (int k = 0; k < (DrawingCanvas.canvasWidth - sideLength) / 2; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                        for (int k = (DrawingCanvas.canvasWidth + sideLength) / 2;
                             k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 0; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printSquare(matrix);
                zoomFunc(sideLength, "middle");
            }
            case "right" -> {
                for (int i = 0; i < DrawingCanvas.canvasHeight; i++) {
                    if (i < sideLength) {
                        for (int j = DrawingCanvas.canvasWidth - sideLength;
                             j < DrawingCanvas.canvasWidth; j++) {
                            matrix[i][j] = printCharacter;
                        }
                        for (int k = 0; k < DrawingCanvas.canvasWidth - sideLength; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 0; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printSquare(matrix);
                zoomFunc(sideLength, "right");
            }
            default -> {
                for (int i = 0; i < DrawingCanvas.canvasHeight; i++) {
                    if (i < sideLength) {
                        for (int j = 0; j < sideLength; j++) {
                            matrix[i][j] = printCharacter;
                        }
                        for (int k = sideLength; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 0; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i][k] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printSquare(matrix);
                zoomFunc(sideLength, "left");
            }
        }
    }
}