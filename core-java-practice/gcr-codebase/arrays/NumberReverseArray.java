import java.util.Scanner;

public class NumberReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        long temp = Math.abs(number);

        int[] digits = new int[20];
        int count = 0;

        if (temp == 0) {
            digits[count++] = 0;
        }

        while (temp != 0) {
            digits[count++] = (int) (temp % 10);
            temp /= 10;
        }

        int[] reversed = new int[count];
        for (int i = 0; i < count; i++) {
            reversed[i] = digits[count - 1 - i];
        }

        System.out.println("Digits in reverse order:");
        for (int i = 0; i < count; i++) {
            System.out.print(reversed[i] + " ");
        }
        System.out.println();
        scanner.close();
    }
}
