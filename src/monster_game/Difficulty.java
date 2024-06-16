package monster_game;

import java.util.Scanner;

public class Difficulty {
    public static int selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle den Schwierigkeitsgrad, (1).Einfach, (2).Schwer, (3).(Fast)Unmöglich: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 3) {
            System.out.println("Ungültige Wahl! Spiel wird beendet.");
            System.exit(0);
        }
        return choice;
    }
}
