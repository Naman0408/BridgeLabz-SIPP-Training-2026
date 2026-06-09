import java.util.Scanner;

public class MultiplesBelow100ForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer less than 100: ");
        int number = scanner.nextInt();

        if (number <= 0 || number >= 100) {
            System.out.println("Please enter a positive integer less than 100.");
        } else {
            System.out.println("Numbers below 100 that divide " + number + ":");
            for (int i = 100; i >= 1; i--) {
                if (number % i == 0) {
                    System.out.println(i);
                }
            }
        }

        scanner.close();
    }
}
