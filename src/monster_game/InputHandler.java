package monster_game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public int getDifficultyChoice() {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Wähle den Schwierigkeitsgrad: (1) Einfach, (2) Schwer, (3) Fast Unmöglich");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    throw new InputMismatchException("Ungültige Zahl. Bitte wähle eine Zahl zwischen 1 und 3.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte wähle eine Zahl zwischen 1 und 3.");
                scanner.next(); // Verbraucht die ungültige Eingabe
            }
        }
        return choice;
    }

    public int getAttackChoice() {
        int choice = 0;
        while (true) {
            try {
                System.out.println("\nUm einen Angriff zu starten, gib deine Angriffszahl ein (1-3): ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    throw new InputMismatchException("Ungültige Zahl. Bitte gib eine Zahl zwischen 1 und 3 ein.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte gib eine Zahl zwischen 1 und 3 ein.");
                scanner.next(); // Verbraucht die ungültige Eingabe
            }
        }
        return choice;
    }

    public void close() {
        scanner.close();
    }
}
