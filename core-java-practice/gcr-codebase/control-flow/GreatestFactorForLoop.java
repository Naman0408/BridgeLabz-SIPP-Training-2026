import java.util.Scanner;

public class GreatestFactorForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        int greatestFactor = 1;
        for (int i = number - 1; i >= 1; i--) {
            if (number % i == 0) {
                greatestFactor = i;
                break;
            }
        }

        System.out.printf("The greatest factor of %d besides itself is %d.%n", number, greatestFactor);
        scanner.close();
    }
}
