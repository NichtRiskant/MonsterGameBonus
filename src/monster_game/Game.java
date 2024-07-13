package monster_game;

import java.util.ArrayList;
import java.util.List;
import static monster_game.GameDesign.*;

public class Game {
    public static void main(String[] args) {
        int windowWidth = 1600;
        int grassHeight = 20;
        int earthHeight = 500;
        int groundYPosition = 500;
        int skyYPosition = 100;

        initializeWindow(windowWidth, groundYPosition, grassHeight, earthHeight, skyYPosition);

        InputHandler inputHandler = new InputHandler();

        // Schwierigkeitsgrad auswählen
        int choice = inputHandler.getDifficultyChoice();

        // Leven zeichnen am oberen Rand
        Drawing.drawLevel(choice);

        List<Monster> monsters = new ArrayList<>();
        Student student = new Student("Dominic", 15, 10, groundYPosition - 60 - grassHeight);

        if (choice == 1) {
            monsters.add(new SimpleMonster("Wiederholungsklausur", 15, 500, groundYPosition - 120 - grassHeight));
        } else if (choice == 2) {
            monsters.add(new AdvancedMonster("Prüfungsmonster", 15, 500, groundYPosition - 120 - grassHeight));
        } else if (choice == 3) {
            monsters.add(new SimpleMonster("Wiederholungsklausur", 15, 400, groundYPosition - 120 - grassHeight));
            monsters.add(new AdvancedMonster("Prüfungsmonster", 15, 700, groundYPosition - 120 - grassHeight));
        }

        // Zeichnen der Spielfiguren
        drawCharacters(student, monsters);

        // Spielschleife ausführen
        GamePlay.runGamePlay(student, monsters, groundYPosition, grassHeight, windowWidth, earthHeight, choice, inputHandler);

        // Stat. geschachteltee Klasse zur Anzeige des Punktestands
        StaticNestedClass.displayScore(student, monsters);

        System.out.println("\nAusrüstung des Studenten:");
        System.out.println("Standard-Schwert");

        inputHandler.close();
    }

    static class StaticNestedClass {
        public static void displayScore(Student student, List<Monster> monsters) {
            System.out.println("--------------------------------------------------");
            System.out.println("Aktuelle Lebenspunkte:");
            System.out.println("Student: " + student.health);
            for (Monster monster : monsters) {
                if (monster != null) {
                    System.out.println(monster.name + ": " + monster.health);
                }
            }
            System.out.println("--------------------------------------------------");
        }
    }
}
