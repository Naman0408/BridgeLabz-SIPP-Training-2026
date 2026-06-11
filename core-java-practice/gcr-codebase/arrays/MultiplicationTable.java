import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number for multiplication table: ");
        int number = scanner.nextInt();

        int[] table = new int[10];
        for (int i = 0; i < table.length; i++) {
            table[i] = number * (i + 1);
        }

        System.out.println("Multiplication table for " + number + ":");
        for (int i = 0; i < table.length; i++) {
            System.out.println(number + " * " + (i + 1) + " = " + table[i]);
        }
        scanner.close();
    }
}
