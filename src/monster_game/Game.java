package monster_game;

import java.util.Random;
import java.util.Scanner;

import static jsTools.Graph.*;

public class Game {
    public static void main(String[] args) {
        initWindow(); // Fenster initialisieren

        // Breite des Fensters festlegen
        int windowWidth = 1600;
        int grassHeight = 20;
        int earthHeight = 500;
        int groundYPosition = 500;

        // Boden zeichnen, angepasst an das Fenster
        addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
        addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wähle den Schwierigkeitsgrad, (1).Einfach, (2).Schwer, (3).(Fast)Unmöglich: ");
        int choice = scanner.nextInt();

        // Zeichne das Level am oberen Rand des Fensters
        drawLevel(choice);

        Monster simpleMonster = null;
        Monster advancedMonster = null;
        Student student = new Student("Student", 100, 100, groundYPosition - 60 - grassHeight);

        if (choice == 1) {
            simpleMonster = new SimpleMonster("Wiederholungsklausur", 5, 500, groundYPosition - 120 - grassHeight);
        } else if (choice == 2) {
            advancedMonster = new AdvancedMonster("Prüfungsmonster", 1, 500, groundYPosition - 120 - grassHeight);
        } else if (choice == 3) {
            simpleMonster = new SimpleMonster("Wiederholungsklausur", 5, 400, groundYPosition - 120 - grassHeight);
            advancedMonster = new AdvancedMonster("Prüfungsmonster", 5, 700, groundYPosition - 120 - grassHeight);
        } else {
            System.out.println("Ungültige Wahl! Spiel wird beendet.");
            scanner.close();
            return;
        }

        // Zeichnen der Spielfiguren
        student.paint();
        if (simpleMonster != null) {
            simpleMonster.paint();
        }
        if (advancedMonster != null) {
            advancedMonster.paint();
        }

        Random random = new Random();

        int consecutiveHits = 0; // Zähler für aufeinanderfolgende Treffer
        int consecutiveMisses = 0; // Zähler für aufeinanderfolgende Fehlschläge

        System.out.println("Willkommen zum Monster-Kampfspiel!");
        System.out.println("Du spielst als Student und kämpfst gegen die Monster.");
        System.out.println("Um einen Angriff zu starten, gib eine Zahl zwischen 1 und 5 ein. Triffst du die richtige Zahl, greifst du das Monster an.");

        while (!student.isDefeated() && (simpleMonster == null || !simpleMonster.isDefeated()) && (advancedMonster == null || !advancedMonster.isDefeated())) {
            System.out.println("\nUm einen Angriff zu starten, gib deine Angriffszahl ein (1-3): ");
            int input = scanner.nextInt();
            int randomNumber = random.nextInt(3) + 1;

            Monster targetMonster = (simpleMonster != null && !simpleMonster.isDefeated()) ? simpleMonster : advancedMonster;

            if (input == randomNumber) {
                System.out.println("Deine Angriffszahl war korrekt, du greifst das Monster an!");
                consecutiveHits++;
                consecutiveMisses = 0;

                if (consecutiveHits == 2) {
                    // Anonyme Klasse für einen Spezialangriff nach zwei aufeinanderfolgenden Treffern
                    Fightable specialAttack = new Fightable() {
                        @Override
                        public void attack(Fightable opponent) {
                            System.out.println("Ein einmaliger Spezialangriff des Studenten nach zwei aufeinanderfolgenden Treffern!");
                            try {
                                ((Monster) opponent).reduceHealth(7);
                            } catch (HealthException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    };

                    // Spezialangriff ausführen
                    specialAttack.attack(targetMonster);
                    consecutiveHits = 0; // Zähler zurücksetzen nach Spezialangriff
                } else {
                    try {
                        targetMonster.reduceHealth(5);
                    } catch (HealthException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(targetMonster.name + " erleidet 5 Trefferpunkte - Restliches Leben: " + targetMonster.health);
                }
            } else {
                System.out.println("Deine Angriffszahl war falsch, Monster wehrt den Angriff ab und greift dich an!");
                consecutiveHits = 0;
                consecutiveMisses++;

                if (consecutiveMisses == 2) {
                    // Lokale Klasse für Spezialverteidigung
                    class SpecialDefense {
                        void defend() {
                            System.out.println("Spezialverteidigung des Monsters!");
                            try {
                                student.reduceHealth(3);
                            } catch (HealthException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Student erleidet 3 Trefferpunkte - Restliches Leben: " + student.health);
                        }
                    }

                    // Benutzung der lokalen Klasse
                    SpecialDefense defense = new SpecialDefense();
                    defense.defend();
                    consecutiveMisses = 0; // Zähler zurücksetzen nach Spezialverteidigung
                } else {
                    try {
                        student.reduceHealth(1);
                    } catch (HealthException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Student erleidet 1 Trefferpunkt - Restliches Leben: " + student.health);
                }
                System.out.println("Die richtige Zahl wäre gewesen: " + randomNumber);
                System.out.println("--------------------------------------------------");
            }

            // Zeichnen der verbleibenden Monster und des Studenten
            clearWindow();
            addRect(0, groundYPosition, windowWidth, grassHeight, green); // Gras
            addRect(0, groundYPosition + grassHeight, windowWidth, earthHeight, brown); // Erde
            student.paint();
            if (simpleMonster != null && !simpleMonster.isDefeated()) {
                simpleMonster.paint();
            }
            if (advancedMonster != null && !advancedMonster.isDefeated()) {
                advancedMonster.paint();
            }
            drawLevel(choice); // Zeichne das Level erneut
        }

        if (student.isDefeated()) {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("Das Monster war zu stark, du hast die Prüfung nicht bestanden!");
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("Du hast die Monster besiegt und deine Prüfung bestanden!");
            System.out.println("--------------------------------------------------");
            System.out.println("-------------------------------------------------- ");
        }

        scanner.close();

        // Statische geschachtelte Klasse zur Anzeige des Punktestands
        StaticNestedClass.displayScore(student, simpleMonster, advancedMonster);
    }

    // Methode zur Anzeige des Levels
    public static void drawLevel(int level) {
        String levelText = "Level " + level;
        int xStart = 10;
        int yStart = 10;
        int rectSize = 5;
        int spacing = 2;

        for (char c : levelText.toCharArray()) {
            drawCharacter(c, xStart, yStart, rectSize, spacing);
            xStart += (rectSize + spacing) * 6; // Anpassung der Startposition für das nächste Zeichen
        }
    }

    public static void drawCharacter(char c, int xStart, int yStart, int rectSize, int spacing) {
        // Muster für die Zeichen (einfaches Beispiel für L, e, v, l und Ziffern)
        int[][] L = {
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
        };
        int[][] E = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
        };
        int[][] V = {
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
        };
        int[][] L2 = L; // Wiederverwendung des L-Musters

        int[][] num1 = {
                {0, 1, 0},
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1},
        };
        int[][] num2 = {
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 1},
        };
        int[][] num3 = {
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 1},
                {1, 1, 1},
        };

        int[][] pattern;

        switch (c) {
            case 'L': pattern = L; break;
            case 'e': pattern = E; break;
            case 'v': pattern = V; break;
            case 'l': pattern = L2; break;
            case '1': pattern = num1; break;
            case '2': pattern = num2; break;
            case '3': pattern = num3; break;
            default: return;
        }

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] == 1) {
                    addRect(xStart + j * (rectSize + spacing), yStart + i * (rectSize + spacing), rectSize, rectSize, black);
                }
            }
        }
    }

    // Statische geschachtelte Klasse für Hilfsfunktionen
    static class StaticNestedClass {
        public static void displayScore(Student student, Monster... monsters) {
            System.out.println("Aktuelle Lebenspunkte:");
            System.out.println("Student: " + student.health);
            for (Monster monster : monsters) {
                if (monster != null) {
                    System.out.println(monster.name + ": " + monster.health);
                }
            }
        }
    }
}
