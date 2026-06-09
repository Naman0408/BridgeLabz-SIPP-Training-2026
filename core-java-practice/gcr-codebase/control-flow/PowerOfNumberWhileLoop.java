import java.util.Scanner;

public class PowerOfNumberWhileLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.print("Enter power: ");
        int power = scanner.nextInt();

        if (power < 0) {
            System.out.println("Please enter a non-negative power.");
        } else {
            long result = 1;
            int counter = 0;
            while (counter < power) {
                result *= number;
                counter++;
            }
            System.out.printf("%d raised to the power of %d is %d.%n", number, power, result);
        }

        scanner.close();
    }
}
