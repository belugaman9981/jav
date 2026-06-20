
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minScore = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE; 
        int rounds = 0;

        // Ask for target goal
        int targetGoal = 0;
        System.out.print("Set a target score goal (or 0 to skip): ");
        while (true) {
            try {
                targetGoal = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
        if (targetGoal > 0) System.out.println("Target set: " + targetGoal + "\n");
        else System.out.println();

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
                        int val = Integer.parseInt(line);
                        if (val < 0) {
                            System.out.print("Negative scores not allowed. Please enter a non-negative integer: ");
                        } else {
                            inputs[i] = val;
                            break;
                        }
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
            System.out.println("Result: " + getGrade(score));
            if (targetGoal > 0) {
                if (score >= targetGoal)
                    System.out.println("Goal reached! (" + score + " / " + targetGoal + ")");
                else
                    System.out.println("Goal not reached. (" + score + " / " + targetGoal + ", need " + (targetGoal - score) + " more)");
            }
            System.out.println();
        }

        if (rounds > 0) {
            String summary = buildSummary(rounds, minScore, maxScore, targetGoal);
            System.out.println(summary);
            saveSummary(summary);
        }

        scanner.close();
    }

    static String buildSummary(int rounds, int minScore, int maxScore, int targetGoal) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Session Summary ---\n");
        sb.append("Rounds played: ").append(rounds).append("\n");
        sb.append("Best score:    ").append(maxScore).append(" (").append(getGrade(maxScore)).append(")\n");
        sb.append("Worst score:   ").append(minScore).append(" (").append(getGrade(minScore)).append(")\n");
        if (targetGoal > 0)
            sb.append("Target goal:   ").append(targetGoal).append(" — Best was ")
              .append(maxScore >= targetGoal ? "above" : "below").append(" target\n");
        return sb.toString();
    }

    static void saveSummary(String summary) {
        String filename = "session_summary.txt"; 
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(summary);
            System.out.println("Summary saved to " + filename);
        } catch (IOException e) {
            System.out.println("Could not save summary: " + e.getMessage());
        }
    }

    static String getGrade(int score) {
        if (score >= 30) return "Excellent";
        if (score >= 20) return "Good";
        if (score >= 10) return "Happy";
        return "Sad";
    }
}
