import java.util.Scanner;

public class TriangleAreaConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base in cm: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height in cm: ");
        double height = scanner.nextDouble();

        double areaSqCm = 0.5 * base * height;
        double areaSqIn = areaSqCm / (2.54 * 2.54);

        System.out.printf("The Area of the triangle in sq in is %.2f and sq cm is %.2f%n", areaSqIn, areaSqCm);

        scanner.close();
    }
}
