
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minScore = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE;
        int rounds = 0;

        System.out.println("Enter 'q' at any prompt to quit.\n");

        while (true) {
            int[] inputs = new int[3];
            boolean quit = false;

            for (int i = 0; i < 3; i++) {
                System.out.print("Enter score " + (i + 1) + ": ");
                while (true) {
                    String line = scanner.nextLine().trim();
                    if (line.equalsIgnoreCase("q")) {
                        quit = true;
                        break;
                    }
                    try {
                        inputs[i] = Integer.parseInt(line);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter an integer: ");
                    }
                }
                if (quit) break;
            }

            if (quit) break;

            int score = inputs[0] + inputs[1] * 2 + inputs[2] * 3;
            rounds++;

            if (score < minScore) minScore = score;
            if (score > maxScore) maxScore = score;

            System.out.println("Total score: " + score);
            System.out.println("Result: " + getGrade(score) + "\n");
        }

        if (rounds > 0) {
            System.out.println("--- Session Summary ---");
            System.out.println("Rounds played: " + rounds);
            System.out.println("Best score:    " + maxScore + " (" + getGrade(maxScore) + ")");
            System.out.println("Worst score:   " + minScore + " (" + getGrade(minScore) + ")");
        }

        scanner.close();
    }

    static String getGrade(int score) {
        if (score >= 30) return "Excellent";
        if (score >= 20) return "Good";
        if (score >= 10) return "Happy";
        return "Sad";
    }
}
