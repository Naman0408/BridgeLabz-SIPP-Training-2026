import java.util.Scanner;

public class BMICalculator2DArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int number = scanner.nextInt();

        double[][] personData = new double[number][3];
        String[] weightStatus = new String[number];

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
            personData[i][0] = w;
            personData[i][1] = h;
            personData[i][2] = w / (h * h);
            weightStatus[i] = getWeightStatus(personData[i][2]);
        }

        System.out.println("\nBMI details using 2D array:");
        for (int i = 0; i < number; i++) {
            System.out.printf("Person %d: Height = %.2f, Weight = %.2f, BMI = %.2f, Status = %s%n",
                    i + 1, personData[i][1], personData[i][0], personData[i][2], weightStatus[i]);
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
