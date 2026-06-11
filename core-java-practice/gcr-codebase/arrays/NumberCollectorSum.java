import java.util.Scanner;

public class NumberCollectorSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[10];
        int index = 0;

        System.out.println("Enter up to 10 numbers (0 or negative to stop):");
        while (index < numbers.length) {
            System.out.print("Number " + (index + 1) + ": ");
            double value = scanner.nextDouble();
            if (value <= 0) {
                break;
            }
            numbers[index++] = value;
        }

        double total = 0.0;
        System.out.println("Numbers entered:");
        for (int i = 0; i < index; i++) {
            System.out.println(numbers[i]);
            total += numbers[i];
        }

        System.out.println("Sum of all numbers: " + total);
        scanner.close();
    }
}
