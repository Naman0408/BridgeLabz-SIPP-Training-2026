import java.util.Scanner;

public class QuotientRemainder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number1: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter number2: ");
        int number2 = scanner.nextInt();

        if (number2 == 0) {
            System.out.println("Cannot divide by zero. Please enter a non-zero second number.");
        } else {
            int quotient = number1 / number2;
            int remainder = number1 % number2;
            System.out.printf("The Quotient is %d and Reminder is %d of two number %d and %d%n",
                    quotient, remainder, number1, number2);
        }

        scanner.close();
    }
}
