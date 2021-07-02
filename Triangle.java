public class Triangle {
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

    //set triangle parameters
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

    //print triangle from the matrix
    private void printTriangle(char[][] matrix) {
        for (int i = 0; i < DrawingCanvas.canvasHeight; i++) {
            for (int j = 0; j < DrawingCanvas.canvasWidth; j++) {
                System.out.printf("%c", matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    //deal with different choice after rotation function
    private void newTriangle() {
        System.out.println("Draw another triangle (Y/N)?");
        String confirmation = DrawingCanvas.keyboard.next();
        if (confirmation.equals("Y")) {
            setParameters();
            drawTriangle(sideWidth, printingCharacter, alignment);
        } else if (confirmation.equals("N")) {
            DrawingCanvas.selectOptions();
        } else {
            System.out.println("Unsupported option. Please try again!");
            newTriangle();
        }
    }

    //left type rotation function
    private void leftRotationFunc(char[][] matrix, int sideLength) {
        System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
        String key = DrawingCanvas.keyboard.next().toUpperCase();

        int des = sideLength - 1;
        char[][] temp = new char[sideLength][sideLength];

        switch (key) {
            case "R" -> {
                for (int i = 0; i < sideLength; i++, des--) {
                    for (int j = 0; j < sideLength; j++) {
                        temp[j][des] = matrix[i][j];
                    }
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], 0, sideLength);
                }
                printTriangle(matrix);
                leftRotationFunc(matrix, sideLength);
            }
            case "L" -> {
                for (int i = 0; i < sideLength; i++) {
                    for (int j = 0; j < sideLength; j++, des--) {
                        temp[des][i] = matrix[i][j];
                    }
                    des = sideLength - 1;
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], 0, sideLength);
                }
                printTriangle(matrix);
                leftRotationFunc(matrix, sideLength);
            }
            default -> newTriangle();
        }
    }

    //right type rotation function
    private void rightRotationFunc(char[][] matrix, int sideLength) {
        System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
        String key = DrawingCanvas.keyboard.next().toUpperCase();

        int des = sideLength - 1;
        char[][] temp = new char[sideLength][sideLength];

        switch (key) {
            case "R" -> {
                for (int i = 0; i < sideLength; i++, des--) {
                    for (int j = 0; j < sideLength; j++) {
                        temp[j][des] = matrix[i][DrawingCanvas.canvasWidth - sideLength + j];
                    }
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], DrawingCanvas.canvasWidth - sideLength, sideLength);
                }
                printTriangle(matrix);
                rightRotationFunc(matrix, sideLength);
            }
            case "L" -> {
                for (int i = 0; i < sideLength; i++) {
                    for (int j = 0; j < sideLength; j++, des--) {
                        temp[des][i] = matrix[i][DrawingCanvas.canvasWidth - sideLength + j];
                    }
                    des = sideLength - 1;
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], DrawingCanvas.canvasWidth - sideLength, sideLength);
                }
                printTriangle(matrix);
                rightRotationFunc(matrix, sideLength);
            }
            default -> newTriangle();
            }
        }

    //middle type rotation function
    private void middleRotationFunc(char[][] matrix, int sideLength) {
        System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to continue.");
        String key = DrawingCanvas.keyboard.next().toUpperCase();

        int des = sideLength - 1;
        char[][] temp = new char[sideLength][sideLength];

        switch (key) {
            case "R" -> {
                for (int i = 0; i < sideLength; i++, des--) {
                    for (int j = 0; j < sideLength; j++) {
                        temp[j][des] = matrix[i][(DrawingCanvas.canvasWidth - sideLength) / 2 + j];
                    }
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], (DrawingCanvas.canvasWidth - sideLength) / 2, sideLength);
                }
                printTriangle(matrix);
                middleRotationFunc(matrix, sideLength);
            }
            case "L" -> {
                for (int i = 0; i < sideLength; i++) {
                    for (int j = 0; j < sideLength; j++, des--) {
                        temp[des][i] = matrix[i][(DrawingCanvas.canvasWidth - sideLength) / 2 + j];
                    }
                    des = sideLength - 1;
                }
                for (int i = 0; i < sideLength; i++) {
                    System.arraycopy(temp[i], 0, matrix[i], (DrawingCanvas.canvasWidth - sideLength) / 2, sideLength);
                }
                printTriangle(matrix);
                middleRotationFunc(matrix, sideLength);
            }
            default -> newTriangle();
        }
    }

    //draw triangle function
    public void drawTriangle(int sideLength, char printCharacter, String alignment) {
        char[][] matrix = new char[DrawingCanvas.canvasHeight][DrawingCanvas.canvasWidth];
        switch (alignment) {
            case "middle" -> {
                for (int i = 1; i <= DrawingCanvas.canvasHeight; i++) {
                    if (i <= sideLength) {
                        for (int j = (DrawingCanvas.canvasWidth - sideLength) / 2 + 1;
                             j <= (DrawingCanvas.canvasWidth + sideLength) / 2 - i + 1; j++) {
                            matrix[i - 1][j - 1] = printCharacter;
                        }
                        for (int k = 0; k < (DrawingCanvas.canvasWidth - sideLength) / 2; k++) {
                            matrix[i - 1][k] = DrawingCanvas.backgroundCharacter;
                        }
                        for (int k = (DrawingCanvas.canvasWidth + sideLength) / 2 - i + 1;
                             k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 1; k <= DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k - 1] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printTriangle(matrix);
                middleRotationFunc(matrix, sideLength);
            }
            case "right" -> {
                for (int i = 1; i <= DrawingCanvas.canvasHeight; i++) {
                    if (i <= sideLength) {
                        for (int j = DrawingCanvas.canvasWidth - sideLength;
                             j < DrawingCanvas.canvasWidth - i + 1; j++) {
                            matrix[i - 1][j] = printCharacter;
                        }
                        for (int k = 0; k < DrawingCanvas.canvasWidth - sideLength; k++) {
                            matrix[i - 1][k] = DrawingCanvas.backgroundCharacter;
                        }
                        for (int k = DrawingCanvas.canvasWidth - i + 1; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 1; k <= DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k - 1] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printTriangle(matrix);
                rightRotationFunc(matrix, sideLength);
            }
            default -> {
                for (int i = 1; i <= DrawingCanvas.canvasHeight; i++) {
                    if (i <= sideLength) {
                        for (int j = 1; j <= sideLength - i + 1; j++) {
                            matrix[i - 1][j - 1] = printCharacter;
                        }
                        for (int k = sideLength - i + 1; k < DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k] = DrawingCanvas.backgroundCharacter;
                        }
                    } else {
                        for (int k = 1; k <= DrawingCanvas.canvasWidth; k++) {
                            matrix[i - 1][k - 1] = DrawingCanvas.backgroundCharacter;
                        }
                    }
                }
                printTriangle(matrix);
                leftRotationFunc(matrix, sideLength);
            }
        }
    }
}