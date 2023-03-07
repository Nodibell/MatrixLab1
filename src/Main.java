import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int action;
        char op;
        double k;
        double detMatrix;
        Matrix matrixA;
        Matrix matrixB;
        Matrix resultMatrix;

        while (true) {
            System.out.println("""
                    Дії:
                     0 - завершити роботу програми;
                     1 - додавання/віднімання матриць(однакові розмірності A і B);
                     2 - множення матриці на коефіцієнт;
                     3 - множення матриць(к-сть стовпців(m) А = к-сті рядків(n) B);
                     4 - транспонування матриці;
                     5 - знаходження оберненої матриці(к-сті рядків(n) = к-сть стовпців(m), якщо det != 0).
                     6 - детермінант матриці(opt)""");

            System.out.print("Що будем робити? - ");
            action = scanner.nextInt();
            switch (action) {
                case (0):
                    System.out.println("Завершую роботу програми!");
                    return;

                case (1):
                    System.out.println("Матриця А:");
                    matrixA = new Matrix();
                    matrixA.print();

                    System.out.println("Матриця B:");
                    matrixB = new Matrix();
                    matrixB.print();

                    System.out.print("Яку операцію виконати(+ або -)? - ");
                    op = scanner.next().charAt(0);
                    while (true) {
                        if (op == '+') {
                            System.out.println("Результат додавання:");
                            resultMatrix = matrixA.add(matrixB);
                            resultMatrix.print();
                            break;
                        } else if (op == '-') {
                            System.out.println("Результат віднімання:");
                            resultMatrix = matrixA.subtract(matrixB);
                            resultMatrix.print();
                            break;
                        } else {
                            System.out.println("Операція не може бути виконана, або її не існує.");
                        }
                    }

                case (2):
                    System.out.println("Матриця:");
                    matrixA = new Matrix();
                    matrixA.print();
                    scanner = new Scanner(System.in);

                    System.out.print("Введіть коефіцієнт:");
                    k = scanner.nextDouble();
                    System.out.println("Результат множення:");
                    resultMatrix = matrixA.multiply(k);
                    resultMatrix.print();
                    break;

                case (3):
                    System.out.println("Матриця А:");
                    matrixA = new Matrix();
                    matrixA.print();

                    System.out.println("Матриця B:");
                    matrixB = new Matrix();
                    matrixB.print();

                    System.out.println("Результат множення:");
                    resultMatrix = matrixA.multiply(matrixB);
                    resultMatrix.print();
                    break;

                case (4):
                    System.out.println("Матриця:");
                    matrixA = new Matrix();
                    matrixA.print();

                    System.out.println("Транспонування:");
                    resultMatrix = matrixA.transpose();
                    resultMatrix.print();
                    break;
                /*

                case (5):
                    System.out.println("Матриця:");
                    matrixA = new Matrix();
                    matrixA.print();

                    if (matrixA.getRows() == matrixA.getColumns()) {
                        detMatrix = matrixA.determinant();
                        System.out.println("Детермінант: " + detMatrix);

                        if (detMatrix != 0) {
                            resultMatrix = matrixA.inverseMatrix(detMatrix);
                            System.out.println("Обернення:");
                            resultMatrix.print();
                        } else {
                            System.out.println("Матриця не має оберненої: det == 0");
                        }
                    } else {
                        System.out.println("Матриця не відповідає умовам: n == m.");
                    }
                    break;

                 */

                case (6):
                    System.out.println("Матриця:");
                    matrixA = new Matrix();
                    matrixA.print();

                    if (matrixA.getRows() == matrixA.getColumns()) {
                        detMatrix = matrixA.determinant();
                        System.out.println("Детермінант: " + detMatrix);
                    } else {
                        System.out.println("Матриця не відповідає умовам: n == m.");
                    }
                    break;

                default:
                    System.out.print("Така дія не може бути виконана!");
            }
        }
    }
}

