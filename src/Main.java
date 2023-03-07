import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int action;
        Matrix matrixA;
        Matrix matrixB;
        Matrix resultMatrix;

        while (true) {
            System.out.println("Дії:" +
                    "\n 0 - завершити роботу програми;" +
                    "\n 1 - додавання матриць(однакові розмірності A і B);" +
                    "\n 2 - множення матриць(к-сть стовпців(m) А = к-сті рядків(n) B);");
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
                    scanner = new Scanner(System.in);

                    System.out.println("Матриця B:");
                    matrixB = new Matrix();
                    matrixB.print();
                    scanner = new Scanner(System.in);

                    System.out.println("Результат додавання:");
                    resultMatrix = matrixA.add(matrixB);
                    if(!resultMatrix.equals(0)){
                        resultMatrix.print();
                    }
                    break;

                case (2):
                    System.out.println("Матриця А:");
                    matrixA = new Matrix();
                    matrixA.print();
                    scanner = new Scanner(System.in);

                    System.out.println("Матриця B:");
                    matrixB = new Matrix();
                    matrixB.print();
                    scanner = new Scanner(System.in);

                    System.out.println("Результат множення:");
                    resultMatrix = matrixA.multiply(matrixB);
                    if (!resultMatrix.equals(0)) {
                        resultMatrix.print();
                    }

                    break;
                default:
                    System.out.println("Така дія не може бути виконана!");
            }
        }
    }
}

