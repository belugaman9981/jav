
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt() + scanner.nextInt() * 2 + scanner.nextInt() * 3;
        System.out.println(score >= 10 ? "happy" : "sad");
        scanner.close();
    }
}
