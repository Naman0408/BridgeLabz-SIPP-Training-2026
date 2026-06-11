import java.util.Scanner;

public class YoungestTallestFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = {"Amar", "Akbar", "Anthony"};
        int[] ages = new int[3];
        double[] heights = new double[3];

        System.out.println("Enter age and height for Amar, Akbar, and Anthony:");
        for (int i = 0; i < 3; i++) {
            System.out.print(names[i] + " age: ");
            ages[i] = scanner.nextInt();
            System.out.print(names[i] + " height: ");
            heights[i] = scanner.nextDouble();
        }

        int youngestIndex = 0;
        int tallestIndex = 0;
        for (int i = 1; i < 3; i++) {
            if (ages[i] < ages[youngestIndex]) {
                youngestIndex = i;
            }
            if (heights[i] > heights[tallestIndex]) {
                tallestIndex = i;
            }
        }

        System.out.println("\nYoungest friend: " + names[youngestIndex] + " (age " + ages[youngestIndex] + ")");
        System.out.println("Tallest friend: " + names[tallestIndex] + " (height " + heights[tallestIndex] + ")");
        scanner.close();
    }
}
