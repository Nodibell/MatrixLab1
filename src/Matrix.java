import java.util.Scanner;

public class Matrix {
    private int[][] matrix;
    private int[][] resultMatrix = new int[1][1];
    private int rows = 0;
    private int columns = 0;
    static Scanner scanner = new Scanner(System.in);

    public int[][] getResultMatrix() {
        return resultMatrix;
    }

    public void setResultMatrix(int[][] resultMatrix) {
        this.resultMatrix = resultMatrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    Matrix() {
        System.out.print("Введіть кількість рядків матриці: ");
        setRows(checkSize());
        System.out.print("Введіть кількість стовпців матриці: ");
        setColumns(checkSize());

        // Створюємо двовимірний масив з введеною розмірністю
        matrix = new int[rows][columns];

        // Заповнюємо матрицю елементами, введеними з клавіатури
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Введіть елемент матриці (" + (i + 1) + ":" + (j + 1) + "):");
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner = new Scanner(System.in);
        setMatrix(matrix);
    }

    Matrix(int[][] matrix) {
        setRows(matrix.length);
        setColumns(matrix[0].length);
        setMatrix(matrix);
    }

    private int checkSize() {
        while (true) {
            int size = scanner.nextInt();
            if (size > 0) {
                scanner = new Scanner(System.in);
                return size;
            } else {
                System.out.println("Розмірність не може бути > 0.");
            }
        }
    }


    public Matrix add(Matrix B) {
        if ((getRows() == B.getRows()) && (getColumns() == B.getColumns())) {
            setResultMatrix(new int[getRows()][getColumns()]);
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] + B.getMatrix()[i][j];
                }
            }
            return new Matrix(getResultMatrix());
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new int[1][1]);
        }
    }

    public Matrix subtract(Matrix B) {
        if ((getRows() == B.getRows()) && (getColumns() == B.getColumns())) {
            setResultMatrix(new int[getRows()][getColumns()]);
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] - B.getMatrix()[i][j];
                }
            }
            return new Matrix(getResultMatrix());
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new int[1][1]);
        }
    }

    public Matrix multiply(double k) {
        resultMatrix = new int[rows][columns];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[i][j] += matrix[i][j] * k;
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix multiply(Matrix B) {
        if (getColumns() == B.getRows()) {
            resultMatrix = new int[rows][B.columns];
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < B.getColumns(); j++) {
                    for (int k = 0; k < getColumns(); k++) {
                        resultMatrix[i][j] += matrix[i][k] * B.matrix[k][j];
                    }
                }
            }
            return new Matrix(resultMatrix);
        } else {
            System.out.println("Матриці A(n,m) B(n,m) невідповідають умовам: A(m) != B(n).");
            return new Matrix(new int[1][1]);
        }
    }

    public Matrix transpose() {
        resultMatrix = new int[getColumns()][getRows()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[j][i] = matrix[i][j];
            }
        }
        return new Matrix(resultMatrix);
    }


    public void print() {
        // Виводимо матрицю на екран
        System.out.println("Матриця:");
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
