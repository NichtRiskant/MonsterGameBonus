package monster_game;

import java.util.List;
import java.util.Random;
import static monster_game.GameDesign.clearAndRedrawWindow;
import static monster_game.Drawing.*;
import static jsTools.Graph.*;

public class GamePlay {
    public static void runGamePlay(Student student, List<Monster> monsters, int groundYPosition, int grassHeight, int windowWidth, int earthHeight, int choice, InputHandler inputHandler) {
        Random random = new Random();

        int consecutiveHits = 0;
        int consecutiveMisses = 0;

        System.out.println("--------------------------------------------------");
        System.out.println("Willkommen zum Monster-Kampfspiel!");
        System.out.println("Du spielst als Student und kämpfst gegen die Monster.");
        System.out.println("Um einen Angriff zu starten, gib eine Zahl zwischen 1 und 3 ein. Triffst du die richtige Zahl, greifst du das Monster an.");
        System.out.println("--------------------------------------------------");

        while (!student.isDefeated() && monsters.stream().anyMatch(monster -> !monster.isDefeated())) {
            int input = inputHandler.getAttackChoice();
            int randomNumber = random.nextInt(3) + 1;

            Monster targetMonster = monsters.stream().filter(monster -> !monster.isDefeated()).findFirst().orElse(null);

            if (input == randomNumber) {
                System.out.println("Deine Angriffszahl war korrekt, du greifst das Monster an!");
                consecutiveHits++;
                consecutiveMisses = 0;

                if (consecutiveHits == 2) {
                    System.out.println("Ein einmaliger Spezialangriff des Studenten nach zwei aufeinanderfolgenden Treffern!");
                    try {
                        targetMonster.reduceHealth(7);
                        System.out.println(targetMonster.name + " erleidet 7 Trefferpunkte - Restliches Leben: " + targetMonster.health);
                    } catch (HealthException e) {
                        System.out.println(e.getMessage());
                    }
                    consecutiveHits = 0; // Zähler zurücksetzen nach Spezialangriff
                } else {
                    try {
                        targetMonster.reduceHealth(student.getAttackDamage());
                    } catch (HealthException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(targetMonster.name + " erleidet " + student.getAttackDamage() + " Trefferpunkte - Restliches Leben: " + targetMonster.health);
                }
            } else {
                System.out.println("Deine Angriffszahl war falsch, Monster wehrt den Angriff ab und greift dich an!");
                consecutiveHits = 0;
                consecutiveMisses++;

                if (consecutiveMisses == 2) {
                    System.out.println("Spezialverteidigung des Monsters!");
                    try {
                        student.reduceHealth(7);
                    } catch (HealthException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Student erleidet 7 Trefferpunkte - Restliches Leben: " + student.health);
                    consecutiveMisses = 0; // Zähler zurücksetzen nach Spezialverteidigung
                } else {
                    targetMonster.attack(student);
                    System.out.println("Student erleidet " + targetMonster.getAttackDamage() + " Trefferpunkte - Restliches Leben: " + student.health);
                }
                System.out.println("Die richtige Zahl wäre gewesen: " + randomNumber);
                System.out.println("--------------------------------------------------");
            }

            // Zeichnen der verbleibenden Monster und des Studenten
            clearAndRedrawWindow(groundYPosition, grassHeight, windowWidth, earthHeight, student, monsters, choice);

            // Überprüfen, ob ein Monster besiegt wurde und übermalen
            if (targetMonster != null && targetMonster.isDefeated()) {
                System.out.println("--------------------------------------------------");

                System.out.println(targetMonster.name + " wurde besiegt!");
                if (choice == 3 && monsters.stream().anyMatch(monster -> !monster.isDefeated())) {
                    System.out.println("Kämpfe nun gegen das nächste Monster!");
                    System.out.println("--------------------------------------------------");

                }
                overpaintMonster(targetMonster);
            }

            // Berechnung der Gesamtschadenspunkte der verbleibenden Monster mit einer Lambda-Funktion
            int totalRemainingHealth = monsters.stream()
                    .filter(monster -> !monster.isDefeated())
                    .mapToInt(monster -> monster.health)
                    .sum();
            System.out.println("Gesamte verbleibende Lebenspunkte der Monster: " + totalRemainingHealth);
        }

        if (student.isDefeated()) {
            System.out.println("--------------------------------------------------");
            System.out.println("Das Monster war zu stark, du hast die Prüfung nicht bestanden!");
            System.out.println("--------------------------------------------------");
            overpaintStudent(student);
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("Du hast die Monster besiegt und deine Prüfung bestanden!");
            System.out.println("--------------------------------------------------");
        }
    }
}
