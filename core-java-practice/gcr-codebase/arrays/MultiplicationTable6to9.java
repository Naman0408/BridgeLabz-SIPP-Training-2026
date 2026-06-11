import java.util.Scanner;

public class MultiplicationTable6to9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to compute multiplication tables from 6 to 9: ");
        int number = scanner.nextInt();

        int[] results = new int[4];
        for (int i = 6; i <= 9; i++) {
            results[i - 6] = number * i;
        }

        System.out.println("Multiplication tables for " + number + " from 6 to 9:");
        for (int i = 6; i <= 9; i++) {
            System.out.println(number + " * " + i + " = " + results[i - 6]);
        }
        scanner.close();
    }
}
