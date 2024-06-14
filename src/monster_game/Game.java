package monster_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

import static jsTools.Graph.*;

public class Game {
    public static void main(String[] args) {
        initWindow();

        // Liste zur Verwaltung der Monster (Container)
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new SimpleMonster("Wiederholungsklausur", 15, 300, 100));
        monsters.add(new AdvancedMonster("Prüfungsmonster", 20, 500, 100));

        Student student = new Student("Student", 15, 100, 100);

        // Zeichnen der Spielfiguren
        student.paint();
        monsters.forEach(Monster::paint);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Lambda-Ausdruck für Angriffslogik
        Consumer<Monster> attackMonster = (Monster m) -> {
            try {
                m.reduceHealth(5);
            } catch (HealthException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(m.name + " erleidet 5 Trefferpunkte - Restliches Leben: " + m.health);
        };

        System.out.println("Willkommen zum Monster-Kampfspiel!");
        System.out.println("Du spielst als Student und kämpfst gegen zwei Monster: Wiederholungsklausur und Prüfungsmonster.");
        System.out.println("Um einen Angriff zu starten, gib eine Zahl zwischen 1 und 5 ein. Triffst du die richtige Zahl, greifst du das Monster an.");

        int consecutiveHits = 0; // Zähler für aufeinanderfolgende Treffer

        while (!student.isDefeated() && monsters.stream().anyMatch(m -> !m.isDefeated())) {
            System.out.println("\nUm einen Angriff zu starten, gib deine Angriffszahl ein (1-5): ");
            int input = scanner.nextInt();
            int randomNumber = random.nextInt(5) + 1;

            if (input == randomNumber) {
                System.out.println("Deine Angriffszahl war korrekt, du greifst das Monster an!");
                consecutiveHits++;

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
                    specialAttack.attack(monsters.stream().filter(m -> !m.isDefeated()).findFirst().orElse(null));
                    consecutiveHits = 0; // Zähler zurücksetzen nach Spezialangriff
                } else {
                    attackMonster.accept(monsters.stream().filter(m -> !m.isDefeated()).findFirst().orElse(null));
                }

                // Lokale Klasse für Spezialverteidigung
                class SpecialDefense {
                    void defend() {
                        System.out.println("Spezialverteidigung des Monsters!");
                        try {
                            student.reduceHealth(2);
                        } catch (HealthException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Student erleidet 2 Trefferpunkte - Restliches Leben: " + student.health);
                    }
                }

                // Benutzung der lokalen Klasse
                SpecialDefense defense = new SpecialDefense();
                defense.defend();

            } else {
                System.out.println("Deine Angriffszahl war falsch, Monster wehrt den Angriff ab und greift dich an!");
                try {
                    student.reduceHealth(1);
                } catch (HealthException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Student erleidet 1 Trefferpunkt - Restliches Leben: " + student.health);
                System.out.println("Die richtige Zahl wäre gewesen: " + randomNumber);
                consecutiveHits = 0; // Zähler zurücksetzen, wenn ein Angriff verfehlt
            }

            // Zeichnen der verbleibenden Monster und des Studenten
            clearWindow();
            student.paint();
            monsters.forEach(Monster::paint);
        }

        if (student.isDefeated()) {
            System.out.println("Das Monster war zu stark, du hast die Prüfung nicht bestanden!");
        } else {
            System.out.println("Du hast alle Monster besiegt und deine Prüfung bestanden!");
        }

        scanner.close();

        // Statische geschachtelte Klasse zur Anzeige des Punktestands
        StaticNestedClass.displayScore(student, monsters);
    }

    // Statische geschachtelte Klasse für Hilfsfunktionen
    static class StaticNestedClass {
        public static void displayScore(Student student, List<Monster> monsters) {
            System.out.println("Aktueller Punktestand:");
            System.out.println("Student: " + student.health);
            monsters.forEach(monster -> System.out.println(monster.name + ": " + monster.health));
        }
    }
}
