import java.util.Scanner;

public class DigitArrayLargestSecondLargestDynamic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = scanner.nextLong();
        number = Math.abs(number);

        int maxDigit = 10;
        int[] digits = new int[maxDigit];
        int index = 0;

        if (number == 0) {
            digits[index++] = 0;
        }

        while (number != 0) {
            if (index == digits.length) {
                maxDigit += 10;
                int[] temp = new int[maxDigit];
                System.arraycopy(digits, 0, temp, 0, digits.length);
                digits = temp;
            }
            digits[index++] = (int) (number % 10);
            number /= 10;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < index; i++) {
            int value = digits[i];
            if (value > largest) {
                secondLargest = largest;
                largest = value;
            } else if (value > secondLargest && value != largest) {
                secondLargest = value;
            }
        }

        System.out.println("Digits stored in the array:");
        for (int i = 0; i < index; i++) {
            System.out.print(digits[i] + " ");
        }
        System.out.println();
        System.out.println("Largest digit: " + largest);
        System.out.println("Second largest digit: " + (secondLargest == Integer.MIN_VALUE ? largest : secondLargest));
        scanner.close();
    }
}
