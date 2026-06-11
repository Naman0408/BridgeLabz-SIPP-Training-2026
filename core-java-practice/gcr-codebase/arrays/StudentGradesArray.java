import java.util.Scanner;

public class StudentGradesArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int number = scanner.nextInt();

        int[] physics = new int[number];
        int[] chemistry = new int[number];
        int[] maths = new int[number];
        double[] percentage = new double[number];
        String[] grade = new String[number];

        for (int i = 0; i < number; i++) {
            System.out.print("Student " + (i + 1) + " physics marks: ");
            int p = scanner.nextInt();
            System.out.print("Student " + (i + 1) + " chemistry marks: ");
            int c = scanner.nextInt();
            System.out.print("Student " + (i + 1) + " maths marks: ");
            int m = scanner.nextInt();
            if (p < 0 || c < 0 || m < 0) {
                System.out.println("Invalid marks. Please enter non-negative values.");
                i--;
                continue;
            }
            physics[i] = p;
            chemistry[i] = c;
            maths[i] = m;
            percentage[i] = (p + c + m) / 3.0;
            grade[i] = getGrade(percentage[i]);
        }

        System.out.println("\nStudent marks, percentage, and grades:");
        for (int i = 0; i < number; i++) {
            System.out.printf("Student %d: Physics=%d, Chemistry=%d, Maths=%d, Percentage=%.2f, Grade=%s%n",
                    i + 1, physics[i], chemistry[i], maths[i], percentage[i], grade[i]);
        }
        scanner.close();
    }

    private static String getGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
