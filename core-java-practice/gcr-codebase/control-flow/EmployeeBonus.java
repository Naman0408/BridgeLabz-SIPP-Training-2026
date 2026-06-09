import java.util.Scanner;

public class EmployeeBonus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter years of service: ");
        int years = scanner.nextInt();

        if (years > 5) {
            double bonus = salary * 0.05;
            System.out.printf("The bonus amount is INR %.2f%n", bonus);
        } else {
            System.out.println("No bonus awarded for service of 5 years or less.");
        }

        scanner.close();
    }
}
