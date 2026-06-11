import java.util.Scanner;

public class DigitFrequencyArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        number = Math.abs(number);

        int[] digits = new int[20];
        int count = 0;

        if (number == 0) {
            digits[count++] = 0;
        }

        while (number != 0) {
            digits[count++] = (int) (number % 10);
            number /= 10;
        }

        int[] frequency = new int[10];
        for (int i = 0; i < count; i++) {
            frequency[digits[i]]++;
        }

        System.out.println("Digit frequencies:");
        for (int digit = 0; digit < frequency.length; digit++) {
            System.out.println(digit + ": " + frequency[digit]);
        }
        scanner.close();
    }
}
