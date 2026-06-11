import java.util.Scanner;

public class NumberSignAndCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];

        System.out.println("Enter 5 numbers:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        for (int value : numbers) {
            if (value > 0) {
                String parity = (value % 2 == 0) ? "even" : "odd";
                System.out.println(value + " is positive and " + parity + ".");
            } else if (value < 0) {
                System.out.println(value + " is negative.");
            } else {
                System.out.println(value + " is zero.");
            }
        }

        int first = numbers[0];
        int last = numbers[numbers.length - 1];
        if (first == last) {
            System.out.println("First and last elements are equal.");
        } else if (first > last) {
            System.out.println("First element is greater than last element.");
        } else {
            System.out.println("First element is less than last element.");
        }
        scanner.close();
    }
}
