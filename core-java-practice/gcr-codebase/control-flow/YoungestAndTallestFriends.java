import java.util.Scanner;

public class YoungestAndTallestFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Amar's age: ");
        int amarAge = scanner.nextInt();
        System.out.print("Enter Amar's height in cm: ");
        double amarHeight = scanner.nextDouble();

        System.out.print("Enter Akbar's age: ");
        int akbarAge = scanner.nextInt();
        System.out.print("Enter Akbar's height in cm: ");
        double akbarHeight = scanner.nextDouble();

        System.out.print("Enter Anthony's age: ");
        int anthonyAge = scanner.nextInt();
        System.out.print("Enter Anthony's height in cm: ");
        double anthonyHeight = scanner.nextDouble();

        String youngestName = "Amar";
        int youngestAge = amarAge;
        if (akbarAge < youngestAge) {
            youngestName = "Akbar";
            youngestAge = akbarAge;
        }
        if (anthonyAge < youngestAge) {
            youngestName = "Anthony";
            youngestAge = anthonyAge;
        }

        String tallestName = "Amar";
        double tallestHeight = amarHeight;
        if (akbarHeight > tallestHeight) {
            tallestName = "Akbar";
            tallestHeight = akbarHeight;
        }
        if (anthonyHeight > tallestHeight) {
            tallestName = "Anthony";
            tallestHeight = anthonyHeight;
        }

        System.out.printf("The youngest friend is %s with age %d.%n", youngestName, youngestAge);
        System.out.printf("The tallest friend is %s with height %.2f cm.%n", tallestName, tallestHeight);

        scanner.close();
    }
}
