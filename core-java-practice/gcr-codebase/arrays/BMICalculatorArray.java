import java.util.Scanner;

public class BMICalculatorArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int number = scanner.nextInt();

        double[] weight = new double[number];
        double[] height = new double[number];
        double[] bmi = new double[number];
        String[] status = new String[number];

        for (int i = 0; i < number; i++) {
            System.out.print("Person " + (i + 1) + " weight (kg): ");
            double w = scanner.nextDouble();
            System.out.print("Person " + (i + 1) + " height (m): ");
            double h = scanner.nextDouble();
            if (w <= 0 || h <= 0) {
                System.out.println("Invalid values. Weight and height must be positive.");
                i--;
                continue;
            }
            weight[i] = w;
            height[i] = h;
        }

        System.out.println("\nBMI details:");
        for (int i = 0; i < number; i++) {
            bmi[i] = weight[i] / (height[i] * height[i]);
            status[i] = getWeightStatus(bmi[i]);
            System.out.printf("Person %d: Height = %.2f, Weight = %.2f, BMI = %.2f, Status = %s%n",
                    i + 1, height[i], weight[i], bmi[i], status[i]);
        }
        scanner.close();
    }

    private static String getWeightStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
