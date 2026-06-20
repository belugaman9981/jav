
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input
        System.out.println("Enter number of small treats:");
        int smallTreats = scanner.nextInt();
        
        System.out.println("Enter number of medium treats:");
        int mediumTreats = scanner.nextInt();
        
        System.out.println("Enter number of large treats:");
        int largeTreats = scanner.nextInt();
        
        // Calculate happiness score
        // Common formula might be: happiness = small*1 + medium*2 + large*3
        // Update this formula based on the actual problem specification
        int happinessScore = calculateHappiness(smallTreats, mediumTreats, largeTreats);
        
        // Happiness threshold (commonly 10, update as needed)
        int happinessThreshold = 10;
        
        // Determine if Barley is happy or sad
        if (happinessScore >= happinessThreshold) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
        }
        
        // Optional: Show the calculation details
        System.out.println("\nDetails:");
        System.out.println("Small treats: " + smallTreats);
        System.out.println("Medium treats: " + mediumTreats);
        System.out.println("Large treats: " + largeTreats);
        System.out.println("Happiness score: " + happinessScore);
        System.out.println("Threshold: " + happinessThreshold);
        
        scanner.close();
    }
    
    // Update this method with the correct happiness formula
    public static int calculateHappiness(int small, int medium, int large) {
        // Example formula: happiness = small*1 + medium*2 + large*3
        // Replace this with the actual formula from the problem
        return small * 1 + medium * 2 + large * 3;
    }
}
