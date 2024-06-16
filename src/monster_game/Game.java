package monster_game;

import static jsTools.Graph.*;
import static monster_game.Drawing.drawLevel;
import static monster_game.Window.initializeWindow;
import static monster_game.Difficulty.selectDifficulty;
import static monster_game.CharacterDrawing.drawCharacters;
import static monster_game.Gameplay.runGameplay;

public class Game {
    public static void main(String[] args) {
        // Initialisierung des Fensters
        int windowWidth = 1600;
        int grassHeight = 20;
        int earthHeight = 500;
        int groundYPosition = 500;
        initializeWindow(windowWidth, groundYPosition, grassHeight, earthHeight);

        // Schwierigkeitsgrad auswählen
        int choice = selectDifficulty();

        // Zeichne das Level am oberen Rand des Fensters
        drawLevel(choice);

        Monster simpleMonster = null;
        Monster advancedMonster = null;
        Student student = new Student("Student", 1, 10, groundYPosition - 60 - grassHeight);

        if (choice == 1) {
            simpleMonster = new SimpleMonster("Wiederholungsklausur", 15, 500, groundYPosition - 120 - grassHeight);
        } else if (choice == 2) {
            advancedMonster = new AdvancedMonster("Prüfungsmonster", 15, 500, groundYPosition - 120 - grassHeight);
        } else if (choice == 3) {
            simpleMonster = new SimpleMonster("Wiederholungsklausur", 15, 400, groundYPosition - 120 - grassHeight);
            advancedMonster = new AdvancedMonster("Prüfungsmonster", 15, 700, groundYPosition - 120 - grassHeight);
        } else {
            System.out.println("Ungültige Wahl! Spiel wird beendet.");
            System.exit(0);
        }

        // Zeichnen der Spielfiguren
        drawCharacters(student, simpleMonster, advancedMonster);

        // Spielschleife ausführen
        runGameplay(student, simpleMonster, advancedMonster, groundYPosition, grassHeight, windowWidth, earthHeight, choice);

        // Statische geschachtelte Klasse zur Anzeige des Punktestands
        StaticNestedClass.displayScore(student, simpleMonster, advancedMonster);
    }

    // Statische geschachtelte Klasse für Hilfsfunktionen
    static class StaticNestedClass {
        public static void displayScore(Student student, Monster... monsters) {
            System.out.println("Aktuelle Lebenspunkte2:");
            System.out.println("Student: " + student.health);
            for (Monster monster : monsters) {
                if (monster != null) {
                    System.out.println(monster.name + ": " + monster.health);
                }
            }
        }
    }
}
