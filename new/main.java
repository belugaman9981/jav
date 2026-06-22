
import java.util.Scanner; // for reading user input
import java.util.ArrayList; // for storing round history
import java.util.List; // list interface
import java.io.FileWriter; // for writing to files
import java.io.IOException; // for handling file errors

public class Main { // main program class
    public static void main(String[] args) { // program entry point
        Scanner scanner = new Scanner(System.in); // create input scanner
        int minScore = Integer.MAX_VALUE; // track lowest score seen
        int maxScore = Integer.MIN_VALUE; // track highest score seen
        int rounds = 0; // count completed rounds
        int totalScore = 0; // accumulate all scores for average
        List<Integer> history = new ArrayList<>(); // store each round's score

        // Ask for target goal
        int targetGoal = 0; // will store optional target score
        System.out.print("Set a target score goal (or 0 to skip): "); // prompt user
        while (true) { // keep asking until valid input
            try {
                targetGoal = Integer.parseInt(scanner.nextLine().trim()); // parse input as int
                break; // exit loop on success
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: "); // re-prompt on bad input
            }
        }
        if (targetGoal > 0) System.out.println("Target set: " + targetGoal + "\n"); // confirm goal
        else System.out.println(); // blank line if no goal

        System.out.println("Enter 'q' at any prompt to quit.\n"); // show quit instruction

        while (true) { // main game loop
            int[] inputs = new int[3]; // store 3 score inputs
            boolean quit = false; // flag to exit loop

            for (int i = 0; i < 3; i++) { // loop to get 3 scores
                System.out.print("Enter score " + (i + 1) + ": "); // prompt for each score
                while (true) { // keep asking until valid input
                    String line = scanner.nextLine().trim(); // read and trim input
                    if (line.equalsIgnoreCase("q")) { // check for quit command
                        quit = true; // set quit flag
                        break; // exit inner loop
                    }
                    try {
                        int val = Integer.parseInt(line); // parse input as int
                        if (val < 0) { // reject negative values
                            System.out.print("Negative scores not allowed. Please enter a non-negative integer: "); // re-prompt
                        } else {
                            inputs[i] = val; // store valid score
                            break; // exit inner loop
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter an integer: "); // re-prompt on bad input
                    }
                }
                if (quit) break; // propagate quit to outer loop
            }

            if (quit) break; // exit main loop if quit

            int score = inputs[0] + inputs[1] * 2 + inputs[2] * 3; // calculate weighted score
            rounds++; // increment round counter
            totalScore += score; // add to running total
            history.add(score); // record round score in history

            if (score < minScore) minScore = score; // update minimum score
            if (score > maxScore) maxScore = score; // update maximum score

            System.out.println("Total score: " + score); // display score
            System.out.println("Result: " + getGrade(score)); // display grade
            System.out.printf("Average score so far: %.1f%n", (double) totalScore / rounds); // display running average
            if (targetGoal > 0) { // check if goal was set
                if (score >= targetGoal) // compare score to goal
                    System.out.println("Goal reached! (" + score + " / " + targetGoal + ")"); // success message
                else
                    System.out.println("Goal not reached. (" + score + " / " + targetGoal + ", need " + (targetGoal - score) + " more)"); // shortfall message
            }
            System.out.println(); // blank line between rounds
        }

        if (rounds > 0) { // only show summary if any rounds played
            String summary = buildSummary(rounds, minScore, maxScore, targetGoal, totalScore, history); // build summary string
            System.out.println(summary); // display summary
            saveSummary(summary); // save summary to file
        }

        scanner.close(); // close scanner resource
    }

    static String buildSummary(int rounds, int minScore, int maxScore, int targetGoal, int totalScore, List<Integer> history) { // builds session summary text
        StringBuilder sb = new StringBuilder(); // use StringBuilder for efficiency
        sb.append("--- Session Summary ---\n"); // header line
        sb.append("Rounds played: ").append(rounds).append("\n"); // append round count
        sb.append(String.format("Average score: %.1f%n", (double) totalScore / rounds)); // append average score
        sb.append("Best score:    ").append(maxScore).append(" (").append(getGrade(maxScore)).append(")\n"); // append best score
        sb.append("Worst score:   ").append(minScore).append(" (").append(getGrade(minScore)).append(")\n"); // append worst score
        if (targetGoal > 0) // only include goal line if goal was set
            sb.append("Target goal:   ").append(targetGoal).append(" — Best was ")
              .append(maxScore >= targetGoal ? "above" : "below").append(" target\n"); // compare best to goal
        sb.append("\n--- Round History ---\n"); // history section header
        for (int i = 0; i < history.size(); i++) { // loop through all rounds
            int s = history.get(i); // get score for this round
            sb.append(String.format("  Round %2d: %3d (%s)%n", i + 1, s, getGrade(s))); // append round number, score, grade
        }
        return sb.toString(); // return finished summary
    }

    static void saveSummary(String summary) { // saves summary to a text file
        String filename = "session_summary.txt"; // output filename
        try (FileWriter fw = new FileWriter(filename)) { // open file for writing
            fw.write(summary); // write summary to file
            System.out.println("Summary saved to " + filename); // confirm save
        } catch (IOException e) {
            System.out.println("Could not save summary: " + e.getMessage()); // handle write errors
        }
    }

    static String getGrade(int score) { // returns grade label based on score
        if (score >= 40) return "Perfect"; // highest tier
        if (score >= 30) return "Great"; // second tier
        if (score >= 20) return "Good"; // third tier
        if (score >= 10) return "OK"; // fourth tier
        if (score > 0) return "Poor"; // fifth tier
        return "gtfu"; // zero score
    }
}

