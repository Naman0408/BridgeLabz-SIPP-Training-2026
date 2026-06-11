import java.util.Scanner;

public class EmployeeBonusCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] salary = new double[10];
        double[] years = new double[10];
        double[] bonus = new double[10];
        double[] newSalary = new double[10];
        double totalBonus = 0.0;
        double totalOldSalary = 0.0;
        double totalNewSalary = 0.0;

        System.out.println("Enter salary and years of service for 10 employees:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Employee " + (i + 1) + " salary: ");
            double enteredSalary = scanner.nextDouble();
            System.out.print("Employee " + (i + 1) + " years of service: ");
            double enteredYears = scanner.nextDouble();

            if (enteredSalary <= 0 || enteredYears < 0) {
                System.out.println("Invalid input. Salary must be positive and years of service cannot be negative. Please enter again.");
                i--;
                continue;
            }

            salary[i] = enteredSalary;
            years[i] = enteredYears;
        }

        for (int i = 0; i < 10; i++) {
            double rate = years[i] > 5 ? 0.05 : 0.02;
            bonus[i] = salary[i] * rate;
            newSalary[i] = salary[i] + bonus[i];
            totalBonus += bonus[i];
            totalOldSalary += salary[i];
            totalNewSalary += newSalary[i];
        }

        System.out.println("\nEmployee bonus details:");
        for (int i = 0; i < 10; i++) {
            System.out.printf("Employee %d: Old Salary = %.2f, Years = %.1f, Bonus = %.2f, New Salary = %.2f%n",
                    i + 1, salary[i], years[i], bonus[i], newSalary[i]);
        }

        System.out.printf("\nTotal bonus payout: %.2f%n", totalBonus);
        System.out.printf("Total old salary: %.2f%n", totalOldSalary);
        System.out.printf("Total new salary: %.2f%n", totalNewSalary);
        scanner.close();
    }
}
