import java.util.Scanner;

public class Matrix {
    private double[][] matrix;
    private int rows = 0;
    private int columns = 0;
    static Scanner scanner = new Scanner(System.in);

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

    Matrix() { // конструктор для класу Matrix
        System.out.print("Введіть кількість рядків матриці: ");
        setRows(inputSize());
        System.out.print("Введіть кількість стовпців матриці: ");
        setColumns(inputSize());
        matrix = new double[rows][columns]; // Створюємо двовимірний масив з введеною розмірністю

        // Заповнюємо матрицю елементами, введеними з клавіатури
        System.out.println("Введіть елементи матриці: ");
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                System.out.print("Введіть елемент матриці(" + (i + 1) + ":" + (j + 1) + "): ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        scanner = new Scanner(System.in);
        setMatrix(matrix);
    }

    Matrix(double[][] matrix) { // конструктор для класу Matrix з вхідним двовимірним масивом
        setRows(matrix.length);
        setColumns(matrix[0].length);
        setMatrix(matrix);
    }

    private int inputSize() { // отримує, перевіряє, і записує розмірність матриці при конструкторі
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

    public Matrix add(Matrix B) { // метод додавання матриць
        if ((getRows() == B.getRows()) && (getColumns() == B.getColumns())) {
            double[][] resultMatrix = new double[getRows()][getColumns()];
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] + B.getMatrix()[i][j];
                }
            }
            return new Matrix(resultMatrix);
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new double[0][0]);
        }
    }

    public Matrix subtract(Matrix B) { // метод віднімання матриць
        if ((getRows() == B.getRows()) && (getColumns() == B.getColumns())) {
            double[][] resultMatrix = new double[getRows()][getColumns()];
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    resultMatrix[i][j] = getMatrix()[i][j] - B.getMatrix()[i][j];
                }
            }
            return new Matrix(resultMatrix);
        } else {
            System.out.println("Матриці мають різні розмірності.");
            return new Matrix(new double[0][0]);
        }
    }

    public Matrix multiply(double k) { // метод для множення матриці на число
        double[][] resultMatrix = new double[rows][columns];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[i][j] += getMatrix()[i][j] * k;
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix multiply(Matrix B) { // метод для множення матриці на матрицю
        if (getColumns() == B.getRows()) {
            double[][] resultMatrix = new double[getRows()][B.getColumns()];
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
            return new Matrix(new double[0][0]);
        }
    }

    public Matrix transpose() { // метод для транспонування матриці
        double[][] resultMatrix = new double[getColumns()][getRows()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                resultMatrix[j][i] = getMatrix()[i][j];
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix findMinorMatrix(int row, int column) { // метод для знаходження мінору відповідного i,j
        double[][] minor = new double[getRows() - 1][getColumns() - 1];

        int minorRow = 0;
        for (int i = 0; i < getRows(); i++) {
            if (i == row) continue;

            int minorColumn = 0;
            for (int j = 0; j < getColumns(); j++) {
                if (j == column) continue;

                minor[minorRow][minorColumn] = getMatrix()[i][j];
                minorColumn++;
            }
            minorRow++;
        }
        return new Matrix(minor);
    }


    public double determinant() { // метод для знаходження детермінанту
        double determinant = 0;

        if (getRows() == 1) { // якщо розмірність матриці 1х1
            determinant = getMatrix()[0][0];
        } else if (getRows() == 2) { // якщо розмірність матриці 2х2
            determinant = (getMatrix()[0][0] * getMatrix()[1][1]) - (getMatrix()[0][1] * getMatrix()[1][0]);
        } else { // для будь-яких інших розмірностей
            for (int i = 0; i < getRows(); i++) {
                Matrix minor = findMinorMatrix(0, i);
                int sign = (i % 2 == 0) ? 1 : -1;
                determinant += sign * getMatrix()[0][i] * minor.determinant();
            }
        }
        return determinant;
    }

    public Matrix inverse() { // метод для знаходження оберненої матриці
        double determinant = determinant();

        if (determinant == 0) {
            return new Matrix(new double[1][1]); // матриця не має оберненої матриці
        }

        double[][] inverse = new double[getRows()][getColumns()];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                Matrix minor = findMinorMatrix(i, j);
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                inverse[j][i] = sign * minor.determinant() / determinant;
            }
        }
        return new Matrix(inverse);
    }

    public void print() { // Виводимо матрицю на екран
        System.out.println("Матриця:");
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                System.out.print(getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
    }
}
