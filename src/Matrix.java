import java.util.Scanner;

public class Matrix {
    private double[][] matrix;
    private double[][] resultMatrix = new double[1][1];
    private int rows = 0;
    private int columns = 0;
    static Scanner scanner = new Scanner(System.in);

    public double[][] getResultMatrix() {
        return resultMatrix;
    }

    public void setResultMatrix(double[][] resultMatrix) {
        this.resultMatrix = resultMatrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
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
        setRows(inputSize());
        System.out.print("Введіть кількість стовпців матриці: ");
        setColumns(inputSize());

        // Створюємо двовимірний масив з введеною розмірністю
        matrix = new double[rows][columns];

        // Заповнюємо матрицю елементами, введеними з клавіатури
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Введіть елемент матриці (" + (i + 1) + ":" + (j + 1) + "):");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        scanner = new Scanner(System.in);
        setMatrix(matrix);
    }

    Matrix(double[][] matrix) {
        setRows(matrix.length);
        setColumns(matrix[0].length);
        setMatrix(matrix);
    }

    private int inputSize() {
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
            setResultMatrix(new double[getRows()][getColumns()]);
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] + B.getMatrix()[i][j];
                }
            }
            return new Matrix(getResultMatrix());
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new double[1][1]);
        }
    }

    public Matrix subtract(Matrix B) {
        if ((getRows() == B.getRows()) && (getColumns() == B.getColumns())) {
            setResultMatrix(new double[getRows()][getColumns()]);
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] - B.getMatrix()[i][j];
                }
            }
            return new Matrix(getResultMatrix());
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new double[1][1]);
        }
    }

    public Matrix multiply(double k) {
        resultMatrix = new double[rows][columns];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[i][j] += getMatrix()[i][j] * k;
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix multiply(Matrix B) {
        if (getColumns() == B.getRows()) {
            resultMatrix = new double[getRows()][B.getColumns()];
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < B.getColumns(); j++) {
                    for (int k = 0; k < getColumns(); k++) {
                        resultMatrix[i][j] += getMatrix()[i][k] * B.getMatrix()[k][j];
                    }
                }
            }
            return new Matrix(resultMatrix);
        } else {
            System.out.println("Матриці A(n,m) B(n,m) невідповідають умовам: A(m) != B(n).");
            return new Matrix(new double[1][1]);
        }
    }

    public Matrix transpose() {
        resultMatrix = new double[getColumns()][getRows()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[j][i] = matrix[i][j];
            }
        }
        return new Matrix(resultMatrix);
    }

    public double determinant() { // знаходження детермінанта методом Гаусса-Жордана
        double detMatrix = 1;
        for (int i = 0; i < getRows(); i++) {
            int maxRow = i;
            for (int j = i + 1; j < getRows(); j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = j;
                }
            }
            if (i != maxRow) {
                double[] temp = matrix[i];
                matrix[i] = matrix[maxRow];
                matrix[maxRow] = temp;
                detMatrix *= -1;
            }
            if (matrix[i][i] == 0) {
                return 0;
            }
            detMatrix *= matrix[i][i];
            for (int j = i + 1; j < getRows(); j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i + 1; k < getRows(); k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
        return detMatrix;
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
