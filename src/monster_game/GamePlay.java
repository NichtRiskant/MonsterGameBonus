package monster_game;

import java.util.Random;
import java.util.Scanner;

import static jsTools.Graph.*;
import static monster_game.Drawing.*;

public class GamePlay {
    public static void runGamePlay(Student student, Monster simpleMonster, Monster advancedMonster, int groundYPosition, int grassHeight, int windowWidth, int earthHeight, int choice) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int consecutiveHits = 0; // Zähler für aufeinanderfolgende Treffer
        int consecutiveMisses = 0; // Zähler für aufeinanderfolgende Fehlschläge

        System.out.println("Willkommen zum Monster-Kampfspiel!");
        System.out.println("Du spielst als Student und kämpfst gegen die Monster.");
        System.out.println("Um einen Angriff zu starten, gib eine Zahl zwischen 1 und 5 ein. Triffst du die richtige Zahl, greifst du das Monster an.");

        while (!student.isDefeated() && ((simpleMonster != null && !simpleMonster.isDefeated()) || (advancedMonster != null && !advancedMonster.isDefeated()))) {
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
                                System.out.println(targetMonster.name + " erleidet 7 Trefferpunkte - Restliches Leben: " + ((Monster) opponent).health);
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
                    // Lokale Klasse für Spezialverteidigung
                    class SpecialDefense {
                        void defend() {
                            System.out.println("Spezialverteidigung des Monsters!");
                            try {
                                student.reduceHealth(7);
                            } catch (HealthException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Student erleidet 7 Trefferpunkte - Restliches Leben: " + student.health);
                        }
                    }

                    // Benutzung der lokalen Klasse
                    SpecialDefense defense = new SpecialDefense();
                    defense.defend();
                    consecutiveMisses = 0; // Zähler zurücksetzen nach Spezialverteidigung
                } else {
                    targetMonster.attack(student); // Hier wird die attack Methode des Monsters aufgerufen
                    System.out.println("Student erleidet " + targetMonster.getAttackDamage() + " Trefferpunkte - Restliches Leben: " + student.health);
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

            // Überprüfen, ob ein Monster besiegt wurde und übermalen
            if (simpleMonster != null && simpleMonster.isDefeated()) {
                System.out.println("Das erste Monster wurde besiegt! Kämpfe weiter gegen das nächste Monster!");
                overpaintMonster(simpleMonster);
                simpleMonster = null;
            } else if (advancedMonster != null && advancedMonster.isDefeated()) {
                System.out.println("Das erste Monster wurde besiegt! Kämpfe weiter gegen das nächste Monster!");
                overpaintMonster(advancedMonster);
                advancedMonster = null; // Entfernen Sie das besiegte Monster
            }
        }

        if (student.isDefeated()) {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            System.out.println(" Das Monster war zu stark, du hast die Prüfung nicht bestanden!");
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            overpaintStudent(student);
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("Du hast die Monster besiegt und deine Prüfung bestanden!");
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
        }

        scanner.close();


    }
}
